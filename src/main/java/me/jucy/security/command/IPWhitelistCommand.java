package me.jucy.security.command;

import me.jucy.security.Security;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class IPWhitelistCommand implements CommandExecutor {

    private static final String IP_REGEX =
            "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])([.,])){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("ipwhitelist")) {
            return false;
        }

        if (!sender.hasPermission("security.command")) {
            return false;
        }

        if (args.length != 1 && args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /ipwhitelist <player> [ip]");
            return false;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "There isn't any player with that name.");
                return false;
            }
            Security.getInstance().getSecurityConfig().addAllowedIP(target);
            sender.sendMessage(ChatColor.GREEN + "Sucessfully whitelisted IP.");
            return true;
        } else {
            OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);
            if (offlineTarget == null) {
                sender.sendMessage(ChatColor.RED + "There isn't any player with that name.");
                return false;
            }
            if (!args[1].matches(IP_REGEX)) {
                sender.sendMessage(ChatColor.RED + "Invalid ip.\nExample: 127.0.0.1");
                return false;
            }
            sender.sendMessage(ChatColor.GREEN + "Sucessfully whitelisted IP.");
            Security.getInstance().getSecurityConfig().addAllowedIP(offlineTarget.getUniqueId(), args[1]);
            return true;
        }
    }
}
