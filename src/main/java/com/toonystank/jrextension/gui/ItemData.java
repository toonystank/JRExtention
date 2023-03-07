package com.toonystank.jrextension.gui;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.toonystank.jrextension.sections.BaseSection;
import com.toonystank.jrextension.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemData {

    private String material;
    private int amount;
    private int durability;
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private List<Integer> slots = new ArrayList<>();
    private ItemBuilder itemBuilder;
    private BaseSection baseSection;

}
