package com.toonystank.jrextension.sections;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandSection {

    public BaseSection baseSection;
    public List<String> commands = new ArrayList<>();
    public BaseCommandSection.ClickType clickType;

    public CommandSection(BaseSection baseSection, @Nullable BaseCommandSection baseCommandSection, BaseCommandSection.ClickType clickType) {
        this.baseSection = baseSection;
        this.clickType = clickType;
        if (baseCommandSection != null) {
            commands = baseSection.configManager.getStringList("sections." + baseSection.sectionName + "." + clickType.clickCommands);
        }
    }
    public CommandSection addCommand(BaseCommandSection.ClickType clickType, List<String> commands) {
        this.commands = commands;
        return this;
    }
    public void save() {
        baseSection.configManager.set("sections." + baseSection.sectionName + "." + clickType.clickCommands, commands);
        baseSection.save();
    }



}
