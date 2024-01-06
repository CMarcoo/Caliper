package me.thevipershow.caliper.commands.implementations;

import me.thevipershow.caliper.Caliper;
import me.thevipershow.caliper.commands.AbstractCaliperNameCommand;
import me.thevipershow.caliper.commands.measures.MeasurableDoubleDistance;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Main command class of this plugin.
 * Responsible for the "/caliper" command.
 */
public class CaliperCommand extends AbstractCaliperNameCommand {

    /**
     * The main constructor of this class.
     * @param caliper The initialised Caliper plugin object.
     */
    public CaliperCommand(@NotNull Caliper caliper) {
        super(caliper);
    }

    /**
     * Get the measures associated to a given Player (should be online!)
     * @param player The player.
     * @return A LinkedList of MeasurableDoubleDistance<?> associated with the player.
     */
    @NotNull
    public LinkedList<MeasurableDoubleDistance<Location>> getPlayerMeasuresOrAdd(@NotNull Player player) {
        UUID playerUUID = player.getUniqueId();
        Map<UUID, LinkedList<MeasurableDoubleDistance<Location>>> measureData = caliper.getPlayerCaliperData().getMeasuresMap();
        LinkedList<MeasurableDoubleDistance<Location>> measures = measureData.get(playerUUID);
        if (measures != null)
            return measures;

        measures = new LinkedList<>();
        measureData.put(playerUUID, measures);
        return measures;
    }

    /**
     * Tries to get a Player's last measure.
     * @param player The player.
     * @return The measure, may be null.
     */
    @Nullable
    public MeasurableDoubleDistance<Location> getPlayerLastMeasure(@NotNull Player player) {
        LinkedList<MeasurableDoubleDistance<Location>> playerMeasures = getPlayerMeasuresOrAdd(player);
        if (playerMeasures.isEmpty())
            return null;
        return playerMeasures.getLast();
    }

    /**
     * Method called when an unknown command is called.
     * @param player The player.
     */
    private void unknownCommandMessage(@NotNull Player player) {
        player.sendMessage(Caliper.CALIPER_PREFIX + "§cThis is an unknown command!");
    }

    /**
     * Enables caliper mode for this Player.
     * @param player The Player.
     */
    public void caliperModeEnableCommand(@NotNull Player player) {
        if (caliper.getPlayerCaliperData().getCaliperModePlayers().contains(player.getUniqueId())) {
            player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou are already into Caliper Mode!");
            return;
        }
        caliper.getPlayerCaliperData().getCaliperModePlayers().add(player.getUniqueId());
        player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou have successfully enable Caliper Mode!");
    }

    public void stopAllRenderings(@NotNull Player player) {
        UUID uuid = player.getUniqueId();
        if (!caliper.getPlayerCaliperData().getActiveRenderings().containsKey(uuid)) return;
        caliper.getPlayerCaliperData().getActiveRenderings().get(uuid).forEach(task -> {
            if (!task.isCancelled()) task.cancel();
        });
    }

    /**
     * Disables caliper mode for this Player.
     * @param player The Player.
     */
    public void caliperModeDisableCommand(@NotNull Player player) {
        if (caliper.getPlayerCaliperData().getCaliperModePlayers().contains(player.getUniqueId())) {
            player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou have disabled Caliper Mode!");
            caliper.getPlayerCaliperData().getCaliperModePlayers().remove(player.getUniqueId());
            stopAllRenderings(player);
            return;
        }
        player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou are not in Caliper Mode!");
    }

    /**
     * Logic for the /caliper [arg] command.
     * @param player The player who executed the command.
     * @param firstArg The first argument after "caliper".
     */
    private void singleArgsCaliperCommand(@NotNull Player player, @NotNull String firstArg) {
        switch (firstArg) {
            case "enable":
                caliperModeEnableCommand(player);
                break;
            case "disable":
                caliperModeDisableCommand(player);
                break;
            default:
                player.sendMessage("§cUnknown argument for caliper command!");
                break;
        }
    }

    /**
     * The help menu for the caliper command
     * @param sender The command sender.
     */
    private static void sendHelp(CommandSender sender) {
        sender.sendMessage(Caliper.CALIPER_PREFIX + "Help Menu");
        sender.sendMessage("§7Enables the measuring mode:");
        sender.sendMessage("§7Click on a first and second block to measure their distance.");
        sender.sendMessage("§e/caliper enable");
        sender.sendMessage("§7Disables measuring mode and animations.");
        sender.sendMessage("§e/caliper disable");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (strings.length == 0) {
            sendHelp(commandSender);
            return false;
        }

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(Caliper.CALIPER_PREFIX + "§cYou cannot use this command from the server console");
            return false;
        }

        if (!player.hasPermission("caliper.measure")) {
            player.sendMessage(Caliper.CALIPER_PREFIX + "§cYou are missing permission \"caliper.measure\"");
            return false;
        }

        if (strings.length == 1)
            singleArgsCaliperCommand(player, strings[0]);
        else
            unknownCommandMessage(player);

        return false;
    }

    /**
     * Get the command name corresponding to this class.
     *
     * @return The command name, same as the plugin.yml for this command logic.
     */
    @Override
    public @NotNull String getCommandName() {
        return "caliper";
    }
}
