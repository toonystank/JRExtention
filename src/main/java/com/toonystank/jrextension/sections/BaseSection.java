package com.toonystank.jrextension.sections;

import com.toonystank.jrextension.JRExtension;
import com.toonystank.jrextension.utils.config.ConfigManager;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseSection {

    private final JRExtension plugin;
    public ConfigManager configManager;
    public String sectionName;
    public String material;
    public @Nullable List<String> potionEffects = new ArrayList<>();
    public String displayName;
    public List<String> lore = new ArrayList<>();
    public List<Integer> slots = new ArrayList<>();
    public SectionOptions sectionOptions;
    public BaseCommandSection baseCommandSection;
    public boolean runTimeSection = false;

    public BaseSection(JRExtension plugin, ConfigManager configManager, String sectionName) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.sectionName = sectionName;
        this.sectionOptions = new SectionOptions(this);
        this.baseCommandSection = new BaseCommandSection(this);
        loadConfig();
    }
    public BaseSection(JRExtension plugin, ConfigManager configManager, boolean runTimeSection, SectionOptions sectionOptions, BaseCommandSection baseCommandSection) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.runTimeSection = runTimeSection;
        this.sectionOptions = sectionOptions.setBaseSection(this);
        this.baseCommandSection = baseCommandSection.setBaseSection(this);
    }

    public BaseSection setSectionName(String sectionName) {
        this.sectionName = sectionName;
        return this;
    }
    public BaseSection setMaterial(String material) {
        this.material = material;
        return this;
    }
    public BaseSection setPotionEffects(List<String> potionEffects) {
        this.potionEffects = potionEffects;
        return this;
    }
    public BaseSection setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }
    public BaseSection setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
    public BaseSection setSlots(List<Integer> slots) {
        this.slots = slots;
        return this;
    }
    public BaseSection setSectionOptions(SectionOptions sectionOptions) {
        this.sectionOptions = sectionOptions;
        return this;
    }
    public BaseSection setBaseCommandSection(BaseCommandSection baseCommandSection) {
        this.baseCommandSection = baseCommandSection;
        return this;
    }

    public void save() {
        sectionOptions.save();
        baseCommandSection.save();
        saveConfig();
    }
    
    public void loadConfig() {
        this.material = configManager.getString("sections." + sectionName + ".material");
        this.potionEffects = configManager.getStringList("sections." + sectionName + ".potionEffects");
        this.displayName = configManager.getString("sections." + sectionName + ".display_name");
        this.lore = configManager.getStringList("sections." + sectionName + ".lore");
        this.slots = configManager.getConfig().getIntegerList("sections." + sectionName + ".slots");
    }

    public void saveConfig() {
        configManager.set("sections." + sectionName + ".material", material);
        configManager.set("sections." + sectionName + ".potionEffects", potionEffects);
        configManager.set("sections." + sectionName + ".display_name", displayName);
        configManager.set("sections." + sectionName + ".lore", lore);
        configManager.set("sections." + sectionName + ".slots", slots);
        configManager.save();
    }
}
