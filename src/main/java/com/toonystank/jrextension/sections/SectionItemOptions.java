package com.toonystank.jrextension.sections;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SectionItemOptions {

    public BaseSection baseSection;
    public Integer amount = 1;
    public Integer modelData = 0;
    public List<String> nbtTags = new ArrayList<>();
    public List<String> nbtInt = new ArrayList<>();
    public @Nullable List<String> bannerMeta;
    public @Nullable List<String> itemFlags;
    public @Nullable String rgb;
    public @Nullable List<String> enchantments;
    public @Nullable Boolean hideEnchantments;
    public @Nullable Boolean hideAttributes;
    public @Nullable Boolean hideEffects;
    public @Nullable Boolean hideUnbreakable;
    public @Nullable Boolean unbreakable;
    public @Nullable String entityType;

    public SectionItemOptions(BaseSection baseSection) {
        this.baseSection = baseSection;
        this.amount = baseSection.configManager.getInt("sections." + baseSection.sectionName + ".amount");
        this.modelData = baseSection.configManager.getInt("sections." + baseSection.sectionName + ".model_data");
        this.nbtTags = baseSection.configManager.getStringList("sections." + baseSection.sectionName + ".nbt_string");
        this.nbtInt = baseSection.configManager.getStringList("sections." + baseSection.sectionName + ".nbt_int");
        this.bannerMeta = baseSection.configManager.getStringList("sections." + baseSection.sectionName + ".banner_meta");
        this.itemFlags = baseSection.configManager.getStringList("sections." + baseSection.sectionName + ".item_flags");
        this.rgb = baseSection.configManager.getString("sections." + baseSection.sectionName + ".rgb");
        this.enchantments = baseSection.configManager.getStringList("sections." + baseSection.sectionName + ".enchantments");
        this.hideEnchantments = baseSection.configManager.getBoolean("sections." + baseSection.sectionName + ".hide_enchantments");
        this.hideAttributes = baseSection.configManager.getBoolean("sections." + baseSection.sectionName + ".hide_attributes");
        this.hideEffects = baseSection.configManager.getBoolean("sections." + baseSection.sectionName + ".hide_effects");
        this.hideUnbreakable = baseSection.configManager.getBoolean("sections." + baseSection.sectionName + ".hide_unbreakable");
        this.unbreakable = baseSection.configManager.getBoolean("sections." + baseSection.sectionName + ".unbreakable");
        this.entityType = baseSection.configManager.getString("sections." + baseSection.sectionName + ".entity_type");
    }
    public SectionItemOptions() {
        this.baseSection = null;
    }

    public SectionItemOptions setBaseSection(BaseSection baseSection) {
        this.baseSection = baseSection;
        return this;
    }

    public SectionItemOptions setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }
    public SectionItemOptions setModelData(Integer modelData) {
        this.modelData = modelData;
        return this;
    }
    public SectionItemOptions setNbtTags(List<String> nbtTags) {
        this.nbtTags = nbtTags;
        return this;
    }
    public SectionItemOptions setNbtInt(List<String> nbtInt) {
        this.nbtInt = nbtInt;
        return this;
    }
    public SectionItemOptions setBannerMeta(List<String> bannerMeta) {
        this.bannerMeta = bannerMeta;
        return this;
    }
    public SectionItemOptions setItemFlags(List<String> itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }
    public SectionItemOptions setRgb(String rgb) {
        this.rgb = rgb;
        return this;
    }
    public SectionItemOptions setEnchantments(List<String> enchantments) {
        this.enchantments = enchantments;
        return this;
    }
    public SectionItemOptions setHideEnchantments(Boolean hideEnchantments) {
        this.hideEnchantments = hideEnchantments;
        return this;
    }
    public SectionItemOptions setHideAttributes(Boolean hideAttributes) {
        this.hideAttributes = hideAttributes;
        return this;
    }
    public SectionItemOptions setHideEffects(Boolean hideEffects) {
        this.hideEffects = hideEffects;
        return this;
    }
    public SectionItemOptions setHideUnbreakable(Boolean hideUnbreakable) {
        this.hideUnbreakable = hideUnbreakable;
        return this;
    }
    public SectionItemOptions setUnbreakable(Boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }
    public SectionItemOptions setEntityType(String entityType) {
        this.entityType = entityType;
        return this;
    }
    public void save() {
        if (amount != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".amount", amount);
        if (modelData != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".model_data", modelData);
        if (nbtTags != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".nbt_string", nbtTags);
        if (nbtInt != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".nbt_int", nbtInt);
        if (bannerMeta != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".banner_meta", bannerMeta);
        if (itemFlags != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".item_flags", itemFlags);
        if (rgb != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".rgb", rgb);
        if (enchantments != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".enchantments", enchantments);
        if (hideEnchantments != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".hide_enchantments", hideEnchantments);
        if (hideAttributes != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".hide_attributes", hideAttributes);
        if (hideEffects != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".hide_effects", hideEffects);
        if (hideUnbreakable != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".hide_unbreakable", hideUnbreakable);
        if (unbreakable != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".unbreakable", unbreakable);
        if (entityType != null) baseSection.configManager.set("sections." + baseSection.sectionName + ".entity_type", entityType);
        baseSection.configManager.save();
    }
}
