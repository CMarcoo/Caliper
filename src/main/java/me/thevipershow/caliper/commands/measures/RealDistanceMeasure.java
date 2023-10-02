package me.thevipershow.caliper.commands.measures;

import org.jetbrains.annotations.NotNull;

/**
 * Can represent distance between two measures
 */
public interface RealDistanceMeasure<T> {

    double distance(@NotNull T first, @NotNull T second);
}
