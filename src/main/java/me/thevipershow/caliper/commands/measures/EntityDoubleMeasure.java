package me.thevipershow.caliper.commands.measures;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityDoubleMeasure extends MeasurableDoubleDistance<Entity> {
    /**
     * Main constructor.
     * @param first first entity.
     * @param second second entity.
     */
    public EntityDoubleMeasure(@NotNull Entity first, @NotNull Entity second) {
        super(first, second);
    }

    /**
     * Calculates the distance im space between two Entity objects.
     * @param first The first location.
     * @param second The second location.
     * @return The distance.
     */
    @Override
    public double distance(@NotNull Entity first, @NotNull Entity second) {
        return first.getLocation().distance(second.getLocation());
    }
}
