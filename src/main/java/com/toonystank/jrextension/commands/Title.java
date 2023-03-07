package com.toonystank.jrextension.commands;

import com.toonystank.jrextension.JRExtension;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Title implements CommandExecutor {

    private JRExtension plugin;
    private final String delimiter = "/nl";

    public Title(JRExtension plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("jobpicker.title") || !sender.hasPermission("jobpicker.admin")) return false;
        if (args.length > 0) {
            if (args[0] == null) return false;
            if (plugin.getServer().getPlayer(args[0]) == null) return false;
            Player playerToSend = plugin.getServer().getPlayer(args[0]);
            String titleArgument = String.join(" ", args).replace(args[0] + " ", "");
            String title = titleArgument.split(delimiter)[0];
            String subTitle = titleArgument.split(delimiter)[1];
            playerToSend.sendTitle(translate(playerToSend, title),translate(playerToSend, subTitle),5,60,5);
            sender.sendMessage("sent title to " + playerToSend.getName());
            return true;
        }
        return false;
    }

    public String translate( String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public String translate(final Player player, String text) {
        return PlaceholderAPI.setPlaceholders(player,translate(text));
    }
}
