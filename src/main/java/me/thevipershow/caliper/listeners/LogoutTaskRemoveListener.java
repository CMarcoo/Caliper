package me.thevipershow.caliper.listeners;

import lombok.RequiredArgsConstructor;
import me.thevipershow.caliper.Caliper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public final class LogoutTaskRemoveListener implements Listener {

    private final Caliper caliper;

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        caliper.getCaliperCommand().caliperModeDisableCommand(player);
    }
}
