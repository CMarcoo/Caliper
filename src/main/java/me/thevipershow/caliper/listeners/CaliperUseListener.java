package me.thevipershow.caliper.listeners;

import me.thevipershow.caliper.commands.implementations.CaliperCommand;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Class handling caliper usage in the world.
 */
public class CaliperUseListener implements Listener {

    private final CaliperCommand caliperCommand;

    /**
     * Main constructor for the CaliperUseListener class.
     * @param caliperCommand The CaliperCommand instance.
     */
    public CaliperUseListener(@NotNull CaliperCommand caliperCommand) {
        this.caliperCommand = caliperCommand;
    }

    /**
     * This function will manage the Caliper measure usage between two blocks.
     * If the player clicks two consecutive blocks, measure and effects will start.
     * @param event The block click event.
     */
    @EventHandler
    public void onEvent(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (!caliperCommand.getCaliperModePlayers().contains(player.getUniqueId())) return; // Only caliper ENABLED players
        if (clickedBlock == null) return; // Needs to be a valid block.
    }
}
