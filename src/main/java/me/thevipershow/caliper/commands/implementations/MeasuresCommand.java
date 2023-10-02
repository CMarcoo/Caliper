package me.thevipershow.caliper.commands.implementations;

import me.thevipershow.caliper.Caliper;
import me.thevipershow.caliper.commands.AbstractCaliperNameCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * The measures command class. Used to browse at obtained measures.
 */
public class MeasuresCommand extends AbstractCaliperNameCommand {
    /**
     * Main constructor for this abstract class.
     *
     * @param caliper The plugin instance after initialisation.
     */
    protected MeasuresCommand(@NotNull Caliper caliper) {
        super(caliper);
    }

    /**
     * Get the command name corresponding to this class.
     *
     * @return The command name, same as the plugin.yml for this command logic.
     */
    @Override
    public @NotNull String getCommandName() {
        return "measures";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}
