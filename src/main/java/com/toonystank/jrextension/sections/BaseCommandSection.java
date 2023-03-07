package com.toonystank.jrextension.sections;


import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class BaseCommandSection {
    public Map<ClickType,CommandSection> clickSection = new HashMap<>();
    public BaseSection baseSection;

    public BaseCommandSection(BaseSection baseSection) {
        this.baseSection = baseSection;
        Arrays.stream(ClickType.values()).forEach(click -> {
            if(baseSection.configManager.getConfig().contains("sections." + baseSection.sectionName + "." + click.clickCommands)) {
                clickSection.put(click, new CommandSection(baseSection, this, click));
            }
        });
    }
    public BaseCommandSection() {
        this.baseSection = null;
    }
    public BaseCommandSection setBaseSection(BaseSection baseSection) {
        this.baseSection = baseSection;
        return this;
    }

    public BaseCommandSection addClickSection(ClickType clickType, CommandSection commandSection) {
        clickSection.put(clickType, commandSection);
        return this;
    }
    public void save() {
        clickSection.forEach((clickType, commandSection) -> commandSection.save());
    }


    public enum ClickType {
        CLICK("click_commands"),
        LEFT_CLICK("left_click_commands"),
        RIGHT_CLICK("right_click_commands");


        public final String clickCommands;

        ClickType(String clickCommands) {
            this.clickCommands = clickCommands;
        }
    }
}
