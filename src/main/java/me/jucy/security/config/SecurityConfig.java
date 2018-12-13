package me.jucy.security.config;

import me.jucy.security.Security;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SecurityConfig {


    public SecurityConfig() {
        Security.getInstance().saveDefaultConfig();
    }

    public void addAllowedIP(Player player) {
        Security.getInstance().getConfig().set("players." + player.getUniqueId().toString(), player.getAddress().getAddress().toString().replace("/", ""));
        Security.getInstance().saveConfig();
    }

    public void addAllowedIP(UUID uuid, String ip) {
        Security.getInstance().getConfig().set("players." + uuid.toString(), ip);
        Security.getInstance().saveConfig();
    }

    public String getAllowedIPs(Player player) {
        return Security.getInstance().getConfig().getString("players." + player.getUniqueId().toString());
    }
}
