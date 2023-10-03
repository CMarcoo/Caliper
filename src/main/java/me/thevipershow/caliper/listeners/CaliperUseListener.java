package me.thevipershow.caliper.listeners;

import me.thevipershow.caliper.Caliper;
import me.thevipershow.caliper.commands.implementations.CaliperCommand;
import me.thevipershow.caliper.commands.measures.GraphicUtilities;
import me.thevipershow.caliper.commands.measures.LocationDoubleMeasure;
import me.thevipershow.caliper.commands.measures.MeasurableDoubleDistance;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Class handling caliper usage in the world.
 */
public class CaliperUseListener implements Listener {

    private final CaliperCommand caliperCommand;
    private final Caliper caliper;

    /**
     * Main constructor for the CaliperUseListener class.
     * @param caliperCommand The CaliperCommand instance.
     */
    public CaliperUseListener(@NotNull CaliperCommand caliperCommand) {
        this.caliperCommand = caliperCommand;
        this.caliper = caliperCommand.getCaliper();
    }

    private void addPlayerRendering(@NotNull Player player, @NotNull BukkitTask bukkitTask) {
        UUID uuid = player.getUniqueId();

        if (!caliper.getPlayerCaliperData().getActiveRenderings().containsKey(uuid)) {
            final Collection<BukkitTask> tasks = new HashSet<>();
            tasks.add(bukkitTask);
            caliper.getPlayerCaliperData().getActiveRenderings().put(uuid, tasks);
            return;
        }
        caliper.getPlayerCaliperData().getActiveRenderings().get(uuid).add(bukkitTask);
    }

    private void startMeasuringAnimation(@NotNull Player player, @NotNull MeasurableDoubleDistance<Location> measure) {
        assert measure.getFirst() != null;
        assert measure.getSecond() != null;
        final Set<Location> locations = GraphicUtilities.generateLineAB(measure.getFirst(), measure.getSecond(), (short) 25);
        BukkitTask task = caliperCommand.getCaliper().getServer().getScheduler()
                .runTaskTimerAsynchronously(caliperCommand.getCaliper(), () -> GraphicUtilities.drawLineSetPlayer(player, Particle.END_ROD, locations), 5L, 5L);
        addPlayerRendering(player, task);
    }

    /**
     * This function will manage the Caliper measure usage between two blocks.
     * If the player clicks two consecutive blocks, measure and effects will start.
     * @param event The block click event.
     */
    @EventHandler
    public void onEvent(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Block clickedBlock = event.getClickedBlock();
        if (!caliperCommand.getCaliper().getPlayerCaliperData().getCaliperModePlayers().contains(playerUUID)) return; // Only caliper ENABLED players
        if (clickedBlock == null) return; // Needs to be a valid block.
        Location loc = clickedBlock.getLocation().clone().add(0x0.8p+0d,0x0.8p+0d,0x0.8p+0d);
        MeasurableDoubleDistance<Location> lastMeasure = caliperCommand.getPlayerLastMeasure(player);
        if (lastMeasure == null || lastMeasure.getSecond() != null) {
            caliperCommand.getPlayerMeasuresOrAdd(player).addLast(new LocationDoubleMeasure(loc, null));
            player.sendMessage(Caliper.CALIPER_PREFIX + "§7You have set your first measure!");
            player.sendMessage(String.format("§7The 1st location is now set to: §7[§e%.1f§f, §e%.1f§f, §e%.1f§7]", loc.getX(), loc.getY(), loc.getZ()));
        } else if (lastMeasure.getFirst() != null) {
            lastMeasure.setSecond(loc);
            player.sendMessage(Caliper.CALIPER_PREFIX + "§7You have set your second measure!");
            player.sendMessage(String.format("§7The 2nd location is now set to: §7[§e%.1f§f, §e%.1f§f, §e%.1f§7]", loc.getX(), loc.getY(), loc.getZ()));
            player.sendMessage(Caliper.CALIPER_PREFIX + String.format("§aThis distance is approximately equal to §c%.2f §ablocks.",
                    lastMeasure.distance(lastMeasure.getFirst(), lastMeasure.getSecond())));
            startMeasuringAnimation(player, lastMeasure);
        }
        event.setCancelled(true);
    }
}
