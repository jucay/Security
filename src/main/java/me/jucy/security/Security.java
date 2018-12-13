package me.jucy.security;

import lombok.Getter;
import me.jucy.security.command.IPWhitelistCommand;
import me.jucy.security.config.SecurityConfig;
import me.jucy.security.listener.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Security extends JavaPlugin {

    @Getter
    private static Security instance;
    @Getter
    private SecurityConfig securityConfig;

    @Override
    public void onEnable() {
        instance = this;
        securityConfig = new SecurityConfig();
        new PlayerJoinListener();
        getCommand("ipwhitelist").setExecutor(new IPWhitelistCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
