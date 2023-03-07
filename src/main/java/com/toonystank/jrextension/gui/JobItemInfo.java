package com.toonystank.jrextension.gui;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.ActionType;
import com.gamingmesh.jobs.container.Job;
import com.toonystank.jrextension.JRExtension;
import com.toonystank.jrextension.utils.ItemBuilder;
import dev.triumphteam.gui.components.InteractionModifier;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JobItemInfo {

    private final JRExtension plugin;
    private final BaseGui baseGui;
    private final Player player;
    private PaginatedGui inventory;

    public JobItemInfo(JRExtension plugin, BaseGui baseGui, Player player) {
        this.plugin = plugin;
        this.baseGui = baseGui;
        this.player = player;
    }
    public void setGUI() {
        inventory = new PaginatedGui(6, 10, baseGui.formatText(player, baseGui.getTitle(), null), InteractionModifier.VALUES);
    }
    public void addGuiItem(List<String> args, Player player, Player target) {
        if (args.size() < baseGui.getCommandArguments().size()) return;
        String jobName = args.get(1);
        Job job = Jobs.getJob(jobName);
        if (job == null) return;
        baseGui.getBaseSections().forEach(section -> {
            if (section.sectionName.equals("item")) return;
            List<Integer> slot = section.slots;
            List<String> lore = section.lore;
            String displayName = section.displayName;
            String material = section.material;
            ItemBuilder itemBuilder = new ItemBuilder(section.material);
            lore = baseGui.formatJobsText(job, null, lore, player);
            displayName = baseGui.formatJobsText(job, null, displayName, player);
            itemBuilder.setDisplayName(displayName);
            itemBuilder.setLore(lore);
            itemBuilder.hideAttributes(ItemFlag.HIDE_ATTRIBUTES);
            ItemData itemData = new ItemData(material, 0, 0, displayName, lore, slot, itemBuilder, section);
            addItems(player, target, args, itemData);
        });

        for (ActionType type : ActionType.values()) {
            job.getJobInfo(type).forEach(jobInfo -> {
                String itemName = baseGui.getString("special.display_name");
                itemName = baseGui.formatJobsText(job, jobInfo, itemName, player);
                List<String> itemLore = baseGui.getStringList("special.lore");
                itemLore = baseGui.formatJobsText(job, jobInfo, itemLore, player);
                AtomicReference<String> material = new AtomicReference<>(jobInfo.getName());
                plugin.getLogger().info("Job Info Name: " + jobInfo.getName());
                if (Material.getMaterial(material.get()) == null) {
                    baseGui.getRemappedItems().forEach((key, value) -> {
                        String jobInfoName = jobInfo.getName();
                        if (jobInfo.getName().contains("-")) {
                            jobInfoName = jobInfo.getName().substring(0, jobInfo.getName().indexOf('-'));
                        }
                        if (key.equals(jobInfoName)) {
                            material.set(value);
                        }
                    });
                }
                plugin.getLogger().info("Material: " + material);
                GuiItem itemBuilder = new ItemBuilder(material.get()).setLore(itemLore).setDisplayName(itemName).hideAttributes(ItemFlag.HIDE_ATTRIBUTES).getAsGuiItem();
                itemBuilder.setAction(event -> event.setCancelled(true));
                inventory.addItem(true, itemBuilder);
            });
        }
        inventory.update();
        inventory.open(player);
    }
    public void addItems(Player operator, Player target, List<String> args,ItemData itemData) {
        GuiItem guiItem = itemData.getItemBuilder().getAsGuiItem();
        itemData.getSlots().forEach(slot -> {
            guiItem.setAction(event -> {
                event.setCancelled(true);
                if (itemData.getBaseSection().sectionName.equals("Previous")) {
                    inventory.previous();
                    return;
                } else if (itemData.getBaseSection().sectionName.equals("Next")) {
                    inventory.next();
                    return;
                }
                this.baseGui.processCommand(operator, target, itemData.getBaseSection(), args, inventory);
            });
            inventory.setItem(slot, guiItem);
        });
    }


}
