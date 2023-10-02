package me.thevipershow.caliper.commands;

import org.jetbrains.annotations.NotNull;

/**
 * For classes providing a command name.
 * They shall be registered under such name.
 */
public interface CommandName {

    /**
     * Get the command name corresponding to this class.
     * @return The command name, same as the plugin.yml for this command logic.
     */
    @NotNull
    String getCommandName();
}
