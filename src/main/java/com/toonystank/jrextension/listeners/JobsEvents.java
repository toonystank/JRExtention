package com.toonystank.jrextension.listeners;

import com.gamingmesh.jobs.api.JobsJoinEvent;
import com.gamingmesh.jobs.api.JobsLeaveEvent;
import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import com.toonystank.jrextension.JRExtension;
import com.toonystank.jrextension.utils.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class JobsEvents implements Listener {

    private final MainConfig config;
    public JobsEvents(JRExtension plugin, MainConfig config) {
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJobLevelUpEvent(JobsLevelUpEvent event) {
        if (config.isIsleave()) {
            event.getPlayer().getPlayer().sendTitle(config.getLevelUpTitle(), config.getLevelUpSubTitle(), 5, 60, 5);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJobLeaveEvent(JobsLeaveEvent event) {
        if (config.isIsleave()) {
            event.getPlayer().getPlayer().sendTitle(config.getLeaveTitle(), config.getLeaveSubTitle(), 5, 60, 5);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJobJoinEvent(JobsJoinEvent event) {
        if (config.isIsjoin()) {
            event.getPlayer().getPlayer().sendTitle(config.getJoinTitle(), config.getJoinSubTitle(), 5, 60, 5);
        }
    }
}
