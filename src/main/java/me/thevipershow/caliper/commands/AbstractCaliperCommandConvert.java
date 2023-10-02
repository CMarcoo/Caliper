package me.thevipershow.caliper.commands;

import me.thevipershow.caliper.Caliper;
import org.jetbrains.annotations.NotNull;

/**
 * For classes that can be converted into a CommandExecutor
 */
public interface AbstractCaliperCommandConvert {

    /**
     * Create a new instance of the child abstract caliper command convert
     * @param caliper The initialised plugin.
     * @return The caliper command class object instance.
     * @param <T> child class.
     */
    @NotNull
    <T extends AbstractCaliperNameCommand> T newCommand(@NotNull Caliper caliper);
}
