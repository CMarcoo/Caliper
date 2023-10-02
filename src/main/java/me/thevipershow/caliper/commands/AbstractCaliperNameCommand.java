package me.thevipershow.caliper.commands;

import lombok.AccessLevel;
import lombok.Getter;
import me.thevipershow.caliper.Caliper;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;

/**
 * Used for commands of this plugin, child classes will be implementing command logic.
 */
public abstract class AbstractCaliperNameCommand implements CommandName, CommandExecutor, SelfRegisteringCommand {

    @Getter(AccessLevel.PROTECTED) private final Caliper caliper;


    /**
     * Main constructor for this abstract class.
     * @param caliper The plugin instance after initialisation.
     */
    protected AbstractCaliperNameCommand(@NotNull Caliper caliper) {
        this.caliper = caliper;
    }

    /**
     * Implementation for registering.
     */
    @Override
    public final void register() {
        caliper.getCommand(getCommandName()).setExecutor(this);
    }
}
