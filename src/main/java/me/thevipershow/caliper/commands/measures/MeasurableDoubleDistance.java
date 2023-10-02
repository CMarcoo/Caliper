package me.thevipershow.caliper.commands.measures;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data()
public abstract class MeasurableDoubleDistance<T> implements DoubleMeasure<T>, RealDistanceMeasure<T> {

    protected T first, second;

    public MeasurableDoubleDistance(@Nullable T first, @Nullable T second) {
        this.first = first;
        this.second = second;
    }
}
