package me.thevipershow.caliper.commands.implementations;

import lombok.Getter;
import me.thevipershow.caliper.Caliper;
import me.thevipershow.caliper.commands.AbstractCaliperNameCommand;
import me.thevipershow.caliper.commands.measures.MeasurableDoubleDistance;
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
@Getter
public class CaliperCommand extends AbstractCaliperNameCommand {

    /**
     * The main constructor of this class.
     * @param caliper The initialised Caliper plugin object.
     */
    public CaliperCommand(@NotNull Caliper caliper) {
        super(caliper);
    }

    private final Map<UUID, LinkedList<MeasurableDoubleDistance<?>>> measuresMap = new HashMap<>();
    private final Set<UUID> caliperModePlayers = new HashSet<>();

    /**
     * Get the measures associated to a given Player (should be online!)
     * @param player The player.
     * @return A LinkedList of MeasurableDoubleDistance<?> associated with the player.
     */
    @NotNull
    private LinkedList<MeasurableDoubleDistance<?>> getPlayerMeasuresOrAdd(@NotNull Player player) {
        UUID playerUUID = player.getUniqueId();
        LinkedList<MeasurableDoubleDistance<?>> measures = this.measuresMap.get(playerUUID);
        if (measures != null)
            return measures;
        this.measuresMap.put(playerUUID, measures = new LinkedList<>());
        return measures;
    }

    /**
     * Tries to get a Player's last measure.
     * @param player The player.
     * @return The measure, may be null.
     */
    @Nullable
    private MeasurableDoubleDistance<?> getPlayerLastMeasure(@NotNull Player player) {
        return getPlayerMeasuresOrAdd(player).getLast();
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
    private void caliperModeEnableCommand(@NotNull Player player) {
        if (caliperModePlayers.contains(player.getUniqueId())) {
            player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou are already into Caliper Mode!");
            return;
        }
        caliperModePlayers.add(player.getUniqueId());
        player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou have successfully enable Caliper Mode!");
    }

    /**
     * Disables caliper mode for this Player.
     * @param player The Player.
     */
    private void caliperModeDisableCommand(@NotNull Player player) {
        if (caliperModePlayers.contains(player.getUniqueId())) {
            player.sendMessage(Caliper.CALIPER_PREFIX + "§aYou have disabled Caliper Mode!");
            caliperModePlayers.remove(player.getUniqueId());
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
                break;
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
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
