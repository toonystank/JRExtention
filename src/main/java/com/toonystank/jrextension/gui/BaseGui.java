package com.toonystank.jrextension.gui;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.CurrencyType;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobInfo;
import com.toonystank.jrextension.JRExtension;
import com.toonystank.jrextension.sections.BaseCommandSection;
import com.toonystank.jrextension.sections.BaseSection;
import com.toonystank.jrextension.sections.HeadSection;
import com.toonystank.jrextension.utils.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemFlag;

import java.io.IOException;
import java.util.*;

public class BaseGui extends HeadSection {

    private final JRExtension plugin;
    private HeadSection headSection;

    private Map<Player, JobItemInfo> playerGUIs = new HashMap<>();
    public BaseGui(JRExtension plugin, String fileName) throws IOException {
        super(plugin, fileName);
        this.plugin = plugin;
    }

    public void openGUI(PlayerCommandPreprocessEvent event) {
        List<String> args = Arrays.asList(event.getMessage().replace("/", "").split(" "));
        if (!getOpenCommands().contains(args.get(0))) return;
        JobItemInfo playerGUI;
        if (playerGUIs.containsKey(event.getPlayer())) {
            playerGUI = playerGUIs.get(event.getPlayer());
        }
        else {
            playerGUI = new JobItemInfo(plugin,this,event.getPlayer());
            playerGUIs.put(event.getPlayer(), playerGUI);
        }
        playerGUI.setGUI();
        addGuiItem(args, event.getPlayer(), event.getPlayer());
    }
    public void addGuiItem(List<String> args, Player player, Player target) {
        if (args.size() < this.getCommandArguments().size()) return;
        String jobName = args.get(1);
        Job job = Jobs.getJob(jobName);
        if (job == null) return;
        this.getBaseSections().forEach(section -> {
            if (section.sectionName.equals("item")) return;
            List<Integer> slot = section.slots;
            List<String> lore = section.lore;
            String displayName = section.displayName;
            String material = section.material;
            ItemBuilder itemBuilder = new ItemBuilder(section.material);
            lore = this.formatJobsText(job, null, lore, player);
            displayName = this.formatJobsText(job, null, displayName, player);
            itemBuilder.setDisplayName(displayName);
            itemBuilder.setLore(lore);
            itemBuilder.hideAttributes(ItemFlag.HIDE_ATTRIBUTES);
            ItemData itemData = new ItemData(material, 0, 0, displayName, lore, slot, itemBuilder, section);
            playerGUIs.get(player).addItems(player, target, args, itemData);
        });
        playerGUIs.get(player).addSpecialItem(args,player);
    }
    public String formatJobsText(Job job, JobInfo jobInfo, String text, Player player) {
        if (text == null) return text;
        if (jobInfo != null) {
            if (text.contains("{item}")) text = text.replace("{item}", jobInfo.getName());
            if (text.contains("{action}")) text = text.replace("{action}", jobInfo.getActionType().getName());
            if (text.contains("{points}")) text = text.replace("{points}", String.valueOf(jobInfo.getBaseXp()));
            if (text.contains("{income}")) text = text.replace("{income}", String.valueOf(jobInfo.getBaseIncome()));
            if (text.contains("{exp}")) text = text.replace("{exp}", String.valueOf(jobInfo.getBaseXp()));
        }
        if (text.contains("{job}")) text = text.replace("{job}", job.getName());
        if (text.contains("{income_boost}")) text = text.replace("{income_boost}", String.valueOf(job.getBoost().get(CurrencyType.MONEY)));
        if (text.contains("{exp_boost}")) text = text.replace("{exp_boost}", String.valueOf(job.getBoost().get(CurrencyType.EXP)));
        if (text.contains("{points_boost}")) text = text.replace("{points_boost}", String.valueOf(job.getBoost().get(CurrencyType.POINTS)));
        text = PlaceholderAPI.setPlaceholders(player, text);
        text = ChatColor.translateAlternateColorCodes('&', text);
        if (plugin.getMainConfig().isTranslate_to_tiny_text()) {
            plugin.getLogger().info("before transelate " + text);
            text = translate(text);
            plugin.getLogger().info("after transelate "+ text);
        }
        if (text.contains("_")) text = text.replace("_", " ");
        return text;
    }
    public List<String> formatJobsText(Job job, JobInfo jobInfo, List<String> text, Player player) {
        text.replaceAll(s -> formatJobsText(job, jobInfo, s, player));
        return text;
    }
    public void processCommand(Player operator, Player target, BaseSection baseSection, List<String> args, PaginatedGui inventory) {
        for (BaseCommandSection.ClickType type : BaseCommandSection.ClickType.values()) {
            baseSection.baseCommandSection.clickSection.get(type).commands.forEach(command -> {
                List<String> arguments = new ArrayList<>();
                if (args == null) {
                    arguments.add("");
                } else {
                    arguments.addAll(args);
                }
                if (command.contains("[sound]")) {
                    String sound = command.replace("[sound] ", "");
                    operator.playSound(operator.getLocation(), Sound.valueOf(sound), 1, 1);
                } else if (command.contains("[message]")) {
                    String message = command.replace("[message] ", "");
                    message = formatText(target, message, arguments);
                    operator.sendMessage(message);
                } else if (command.contains("[player]")) {
                    String runTimeCommand = command.replace("[player] ", "");
                    runTimeCommand = formatText(target, runTimeCommand, arguments);
                    operator.performCommand(runTimeCommand);
                } else if (command.contains("[close]")) {
                    inventory.close(operator);
                }
            });
        }
    }
    public String formatText(Player player, String text, List<String> providerArgs) {
        text = PlaceholderAPI.setPlaceholders(player, text);
        text = ChatColor.translateAlternateColorCodes('&', text);
        if (providerArgs == null || providerArgs.size() == 0) return text;
        for (int i = 0; i < providerArgs.size(); i++) {
            if (!text.contains("{" + this.getCommandArguments().get(i) + "}")) continue;
            text = text.replace("{" + this.getCommandArguments().get(i) + "}", providerArgs.get(i));
        }
        return text;
    }
    public List<String> formatText(Player player, List<String> text, List<String> providerArgs) {
        text.replaceAll(s -> formatText(player, s, providerArgs));
        return text;
    }
    private static final char[] SMALL_CAPS_ALPHABET = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘqʀꜱᴛᴜᴠᴡxʏᴢ".toCharArray();

    private static String translate(String text) {
        if (text == null) {
            return null;
        }
        int length = text.length();
        StringBuilder smallCaps = new StringBuilder(length);
        boolean inColorCode = false;
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (c == '§') {
                inColorCode = true;
                smallCaps.append(c);
            } else if (inColorCode) {
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F') || c == 'r') {
                    smallCaps.append(c);
                } else {
                    inColorCode = false;
                    smallCaps.append(translateChar(c));
                }
            } else {
                smallCaps.append(translateChar(c));
            }
        }
        return smallCaps.toString();
    }

    private static char translateChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return SMALL_CAPS_ALPHABET[c - 'a'];
        } else if (c >= 'A' && c <= 'Z') {
            return SMALL_CAPS_ALPHABET[c - 'A'];
        } else {
            return c;
        }
    }

}
