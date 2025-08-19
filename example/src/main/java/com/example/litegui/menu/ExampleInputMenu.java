package com.example.litegui.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import com.example.litegui.ExamplePlugin;
import com.example.litegui.Util;
import github.mori.litegui.api.menu.ItemInputMenu;

public class ExampleInputMenu extends ItemInputMenu {

    public ExampleInputMenu() {
        super(ExamplePlugin.getInstance(), Util.mm("<green>Example Input Menu"));
    }

    @Override
    protected void onYesClick(@NotNull InventoryClickEvent event, @NotNull ItemStack inputItem) {
        event.getWhoClicked().sendMessage(Util.mm("<green>Selected item: " + inputItem.getType()));
        event.getWhoClicked().closeInventory();
    }

    @Override
    protected void onNoClick(@NotNull InventoryClickEvent event) {
        event.getWhoClicked().sendMessage(Util.mm("<red>Input cancelled"));
        event.getWhoClicked().closeInventory();
    }
}
