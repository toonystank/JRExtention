package com.toonystank.jrextension;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.ActionType;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobInfo;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

public class JobsPlaceholder extends PlaceholderExpansion {

    private final JRExtension plugin;
    private final List<Job> jobList;

    public JobsPlaceholder(JRExtension plugin) {
        this.plugin = plugin;
        this.jobList = Jobs.getJobs();
    }

    /**
     * The placeholder identifier of this expansion. May not contain {@literal %},
     * {@literal {}} or _
     *
     * @return placeholder identifier that is associated with this expansion
     */
    @Override
    public @NotNull String getIdentifier() {
        return "Skiesmc";
    }

    /**
     * The author of this expansion
     *
     * @return name of the author for this expansion
     */
    @Override
    public @NotNull String getAuthor() {
        return "Edward";
    }

    /**
     * The version of this expansion
     *
     * @return current version of this expansion
     */
    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }
// %skiesmc_{jobname}_{meterial}_{action}_points%
    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        String[] args = params.split("_");
        return getValue(args[0], args[1].toUpperCase(Locale.ROOT), ActionType.valueOf(args[2].toUpperCase(Locale.ROOT)), args[3]);
    }

    private String getValue(String jobName, String material, ActionType type,String getValue) {
        for (Job job : jobList) {
            if (!job.getName().equalsIgnoreCase(jobName)) continue;
            for (JobInfo jobInfo : job.getJobInfoList().get(type)) {
                if (!jobInfo.getName().equalsIgnoreCase(material)) continue;
               switch (getValue.toLowerCase(Locale.ROOT)) {
                   case "points": {
                        return String.valueOf(jobInfo.getBasePoints());
                    }
                   case "exp": {
                        return String.valueOf(jobInfo.getBaseXp());
                    }
                   case "money": {
                        return String.valueOf(jobInfo.getBaseIncome());
                    }
                   default: {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
