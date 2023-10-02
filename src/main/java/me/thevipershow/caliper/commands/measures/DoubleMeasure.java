package me.thevipershow.caliper.commands.measures;

import org.jetbrains.annotations.Nullable;

/**
 * An interface representing a measure.
 */
public interface DoubleMeasure<T> {

    /**
     * The first reference of this measure.
     * @return the first reference.
     */
    @Nullable
    T getFirst();

    /**
     * The second reference of this measure.
     * @return the second reference.
     */
    @Nullable
    T getSecond();
}
