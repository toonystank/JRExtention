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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JobLevelReward {

    private final JRExtension plugin;
    private final BaseGui baseGui;
    private final Player player;
    private PaginatedGui inventory;

    public JobLevelReward(JRExtension plugin, BaseGui baseGui, Player player) {
        this.plugin = plugin;
        this.baseGui = baseGui;
        this.player = player;
    }

    public void setGUI() {
        inventory = new PaginatedGui(6, 10, baseGui.formatText(player, baseGui.getTitle(), null), InteractionModifier.VALUES);
    }
    public void addSpecialItem(List<String> args, Player player) {
        if (args.size() < baseGui.getCommandArguments().size()) return;
    }
}
