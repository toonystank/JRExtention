package com.toonystank.jrextension.utils;

import com.toonystank.jrextension.JRExtension;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MainConfig extends ConfigManager{

    private final JRExtension plugin;

    private boolean islevelup;
    private boolean isleave;
    private boolean isjoin;

    private String levelUpTitle;
    private String levelUpSubTitle;

    private String leaveTitle;
    private String leaveSubTitle;

    private String joinTitle;
    private String joinSubTitle;
    private boolean translate_to_tiny_text;
    private List<String> guiFiles = new ArrayList<>();
    public MainConfig(JRExtension plugin) throws IOException {
        super(plugin, "config.yml", false, true);
        this.plugin = plugin;
        loadConfig();
    }
    public void loadConfig() {
        islevelup = this.getBoolean("services.title.levelup.enabled");
        isleave = this.getBoolean("services.title.leave.enabled");
        isjoin = this.getBoolean("services.title.join.enabled");
        levelUpTitle = this.getString("services.title.levelup.title");
        levelUpSubTitle = this.getString("services.title.levelup.subtitle");
        leaveTitle = this.getString("services.title.leave.title");
        leaveSubTitle = this.getString("services.title.leave.subtitle");
        joinTitle = this.getString("services.title.join.title");
        joinSubTitle = this.getString("services.title.join.subtitle");
        guiFiles = this.getStringList("services.gui");
        translate_to_tiny_text = this.getBoolean("services.translate_to_tiny_text");
        formatColor();
    }
    public void formatColor() {
        levelUpTitle = formatColor(levelUpTitle);
        levelUpSubTitle = formatColor(levelUpSubTitle);
        leaveTitle = formatColor(leaveTitle);
        leaveSubTitle = formatColor(leaveSubTitle);
        joinTitle = formatColor(joinTitle);
        joinSubTitle = formatColor(joinSubTitle);
    }
    public String formatColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
