package me.thevipershow.caliper.commands.measures;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data()
public abstract class MeasurableDoubleDistance<T> implements DoubleMeasure<T>, RealDistanceMeasure<T> {

    protected T first, second;

    public MeasurableDoubleDistance(@NotNull T first, @NotNull T second) {
        this.first = first;
        this.second = second;
    }
}
