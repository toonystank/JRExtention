package com.toonystank.jrextension.utils;


import dev.triumphteam.gui.guis.GuiItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder extends ItemStack {

    public ItemStack itemStack = this;

    private Material material;
    public int amount;
    public int durability;
    public String displayName;
    public List<String> lore = new ArrayList<>();

    public ItemBuilder(Material material) {
        super(material);
        this.material = material;
    }
    public ItemBuilder(String material) {
        super(Material.valueOf(material));
        this.material = Material.valueOf(material);
    }
    
    public ItemBuilder(Material material, int amount) {
        super(material,amount);
        this.material = material;
        this.amount = amount;
    }
    public ItemBuilder(Material material, int amount, short durability) {
        super(material, amount, durability);
        this.material = material;
        this.amount = amount;
        this.durability = durability;
    }
    public ItemBuilder setDisplayName(String displayName) {
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(displayName);
        this.setItemMeta(meta);
        this.displayName = displayName;
        return this;
    }
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = this.getItemMeta();
        meta.setLore(lore);
        this.setItemMeta(meta);
        this.lore = lore;
        return this;
    }
    public ItemBuilder setQuantity(int amount) {
        itemStack.setAmount(amount);
        this.amount = amount;
        return this;
    }
    public ItemBuilder setDurable(short durability) {
        itemStack.setDurability(durability);
        this.durability = durability;
        return this;
    }
    public ItemBuilder formatColor() {
        ItemMeta meta = this.getItemMeta();
        displayName = ChatColor.translateAlternateColorCodes('&', displayName);
        meta.setDisplayName(displayName);
        this.lore.forEach(loreLine -> {
            lore.remove(loreLine);
            lore.add(ChatColor.translateAlternateColorCodes('&', loreLine));
        });
        meta.setLore(lore);
        this.setItemMeta(meta);
        return this;
    }
    public ItemBuilder hideAttributes(ItemFlag flag) {
        ItemMeta meta = this.getItemMeta();
        meta.addItemFlags(flag);
        this.setItemMeta(meta);
        return this;
    }
    public ItemStack getItemStack() {
        return this;
    }
    public GuiItem getAsGuiItem() {
        return new GuiItem(this);
    }
}
