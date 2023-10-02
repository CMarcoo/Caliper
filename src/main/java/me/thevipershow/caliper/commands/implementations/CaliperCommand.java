package me.thevipershow.caliper.commands;

import me.thevipershow.caliper.Caliper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Main command class of this plugin.
 * Responsible for the "/caliper" command.
 */
public final class CaliperCommand extends AbstractCaliperNameCommand {

    /**
     * The main constructor of this class.
     * @param caliper The initialised Caliper plugin object.
     */
    public CaliperCommand(@NotNull Caliper caliper) {
        super(caliper);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
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
