package me.jucy.security.listener;

import me.jucy.security.Security;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Bukkit.getPluginManager().registerEvents(this, Security.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("security.bypass") || !(player.hasPermission("security.password"))) {
            return;
        }
        if (Security.getInstance().getSecurityConfig().getAllowedIPs(player).equalsIgnoreCase(player.getAddress().getAddress().toString())) {
            return;
        }
        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Your ip isn't whitelisted!");
    }
}
