package me.thevipershow.caliper.commands.measures;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationDoubleMeasure extends MeasurableDoubleDistance<Location>  {


    /**
     * Main constructor for this implementation.
     * NOTE: the two locations may coincide.
     * @param first The first location.
     * @param second The second location.
     *
     */
    public LocationDoubleMeasure(@Nullable Location first, @Nullable Location second) {
        super(first, second);
    }

    /**
     * Calculates the distance im space between two Location objects.
     * @param first The first location.
     * @param second The second location.
     * @return The distance.
     */
    @Override
    public double distance(@NotNull Location first, @NotNull Location second) {
        return first.distance(second);
    }
}
