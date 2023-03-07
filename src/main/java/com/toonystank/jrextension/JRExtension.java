package com.toonystank.jrextension;

import com.toonystank.jrextension.commands.Title;
import com.toonystank.jrextension.gui.GuiManager;
import com.toonystank.jrextension.listeners.JobsEvents;
import com.toonystank.jrextension.utils.MainConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class JRExtension extends JavaPlugin {

    @Getter
    private MainConfig mainConfig;

    @Override
    public void onEnable() {
        getCommand("jobstitle").setExecutor(new Title(this));
        try {
            mainConfig = new MainConfig(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new JobsEvents(this, mainConfig);
        new GuiManager(this, mainConfig);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
