package com.toonystank.jrextension.sections;

import com.toonystank.jrextension.JRExtension;
import com.toonystank.jrextension.utils.ConfigManager;
import lombok.Getter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class HeadSection extends ConfigManager{

    private final JRExtension plugin;
    private String title;
    private List<String> openCommands = new ArrayList<>();
    private List<String> commandArguments = new ArrayList<>();
    private final Map<String,String> remappedItems = new HashMap<>();
    private List<BaseSection> baseSections = new ArrayList<>();

    public HeadSection(JRExtension plugin, String fileName) throws IOException {
        super(plugin, fileName,  false, true);
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        this.title = this.getConfig().getString("head.title");
        this.openCommands = this.getConfig().getStringList("head.open_commands");
        this.commandArguments = this.getConfig().getStringList("head.command_arguments");
        getConfig()
                .getConfigurationSection("head.item_remap")
                .getKeys(false)
                .forEach(key -> remappedItems.put(key, getConfig().getString("head.item_remap." + key)));
        this.baseSections = this.getConfig()
                .getConfigurationSection("sections")
                .getKeys(false)
                .stream()
                .map(sectionName -> new BaseSection(this.plugin, this, sectionName))
                .collect(Collectors.toList());
    }

}
