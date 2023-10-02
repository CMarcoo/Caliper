package me.thevipershow.caliper.commands.measures;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityDoubleMeasure extends LocationDoubleMeasure {
    /**
     * Main constructor.
     * @param first first entity.
     * @param second second entity.
     */
    public EntityDoubleMeasure(@NotNull Entity first, @NotNull Entity second) {
        super(first.getLocation(), second.getLocation());
    }
}
