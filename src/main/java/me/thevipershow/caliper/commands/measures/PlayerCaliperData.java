package me.thevipershow.caliper.commands.measures;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

@Getter
public class PlayerCaliperData {

    private final Map<UUID, LinkedList<MeasurableDoubleDistance<Location>>> measuresMap = new HashMap<>();
    private final Set<UUID> caliperModePlayers = new HashSet<>();
    private final Map<UUID, Collection<BukkitTask>> activeRenderings = new HashMap<>();
}
