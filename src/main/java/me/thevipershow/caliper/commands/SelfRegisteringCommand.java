package me.thevipershow.caliper.commands;

/**
 * Implemented for commands that can self register.
 */
public interface SelfRegisteringCommand {

    /**
     * Register this command.
     */
    void register();
}
