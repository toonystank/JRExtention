package com.toonystank.jrextension.sections;

import org.jetbrains.annotations.Nullable;
public class SectionOptions extends SectionItemOptions {
    public @Nullable Integer priority;
    public @Nullable Boolean update;
    private BaseSection baseSection;

    public SectionOptions(BaseSection baseSection) {
        super(baseSection);
        this.baseSection = baseSection;
        if (!baseSection.runTimeSection) {
            this.priority = baseSection.configManager.getInt("sections." + baseSection.sectionName + ".priority");
            this.update = baseSection.configManager.getBoolean("sections." + baseSection.sectionName + ".update");
        }
    }
    public SectionOptions() {
        this.baseSection = null;
    }

    public SectionOptions setBaseSection(BaseSection baseSection) {
        this.baseSection = baseSection;
        return this;
    }

    public SectionOptions setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }
    public SectionOptions setUpdate(Boolean update) {
        this.update = update;
        return this;
    }
    public SectionOptions Construct() {
        baseSection.configManager.set("sections." + baseSection.sectionName + ".priority", priority);
        baseSection.configManager.set("sections." + baseSection.sectionName + ".update", update);
        baseSection.configManager.save();
        return this;
    }
}
