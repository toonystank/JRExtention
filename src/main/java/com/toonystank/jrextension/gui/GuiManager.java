package com.toonystank.jrextension.gui;

import com.toonystank.jrextension.JRExtension;
import com.toonystank.jrextension.utils.ConfigManager;
import com.toonystank.jrextension.utils.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.IOException;
import java.util.*;

public class GuiManager implements Listener {

    private JRExtension plugin;
    private MainConfig mainConfig;

    private Map<String, BaseGui> guiMap = new HashMap<>();
    private List<BaseGui> baseGuis = new ArrayList<>();

    public GuiManager(JRExtension plugin, MainConfig mainConfig) {
        this.plugin = plugin;
        this.mainConfig = mainConfig;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getScheduler().runTaskLater(plugin, this::loadGuis, 20L);
    }

    public void loadGuis() {
        mainConfig.getGuiFiles().forEach(fileName -> {
            try {
                guiMap.put(fileName, new BaseGui(plugin, fileName));
                baseGuis.add(guiMap.get(fileName));
                plugin.getLogger().info("Loaded gui: " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        baseGuis.forEach(baseGui -> {
            if (event.getMessage().contains("jrextensionreload")) {
                try {
                    baseGui.reload();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else baseGui.openGUI(event);
        });
    }

}
