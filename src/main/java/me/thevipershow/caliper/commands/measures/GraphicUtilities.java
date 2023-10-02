package me.thevipershow.caliper.commands.measures;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class GraphicUtilities {

    /**
     * Calculate the points following a line between two points in space
     * @param A the first location
     * @param B the second location
     * @param points amount of points between AB
     * @return a collection of points
     */
    @NotNull
    public static Set<Location> generateLineAB(final @NotNull Location A, final @NotNull Location B, final short points) {
        final Vector aVec = A.toVector();
        final Vector bVec = B.toVector();
        final World world = A.getWorld();
        final Set<Location> locations = new HashSet<>();
        final Vector differenceVec = bVec.clone().subtract(aVec);
        for (short i = 1; i < points; i++) { // a short value is used, any bigger value does not make any sense
                                             // as Minecraft wouldn't be able to render more than (2^15)-1 particles.
                                             // might be further reduced to byte variable in the future.
            locations.add(aVec.clone().add(differenceVec.clone().multiply((double) i / points)).toLocation(world));
        }
        return locations;
    }

    public static void drawLineSetPlayer(@NotNull Player player, @NotNull Particle particle, @NotNull Set<Location> locations) {
        for (final Location loc : locations)
            player.spawnParticle(particle, loc, 0,0,0,0,0);
    }

    /**
     * Draw a line between two points in space.
     * @param p the player who will see this line
     * @param a point A
     * @param b point B
     * @param particle the particle chosen
     * @param points amount of points in AB
     */
    public static void drawLineABPlayer(@NotNull Player p, @NotNull Location a, @NotNull Location b, @NotNull Particle particle, short points) {
        drawLineSetPlayer(p, particle, generateLineAB(a,b,points));
    }

    /**
     * Draw a line between two points in space.
     * @param a point A
     * @param b point B
     * @param particle the particle chosen
     * @param points amount of points in AB
     */
    public static void drawLineABWorld(@NotNull Location a, @NotNull Location b, @NotNull Particle particle, short points) {
        World world = a.getWorld();
        for (final Location location : generateLineAB(a, b, points)) {
            world.spawnParticle(particle, location,0,0,0,0);
        }
    }
}
