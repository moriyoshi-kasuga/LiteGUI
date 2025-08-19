package com.example.litegui.menu;

import java.util.stream.Stream;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.example.litegui.Util;
import github.mori.litegui.api.button.ItemButton;
import github.mori.litegui.api.button.MenuButton;
import github.mori.litegui.api.menu.ListMenu;

public class ExampleMaterialMenu extends ListMenu {

    public ExampleMaterialMenu() {
        super(54, Util.mm("<red>Materials</red>"), getButtons());
    }

    private static MenuButton<?>[] getButtons() {
        var buttons = Stream.of(Material.values()).filter(Material::isItem).map(v -> {
            if (v.isAir()) {
                return new AirButton();
            }
            return new ItemButton<>(new ItemStack(v));
        }).toArray(ItemButton[]::new);

        return buttons;
    }

    private static class AirButton extends ItemButton<ExampleMaterialMenu> {
        public AirButton() {
            super(new ItemStack(Material.AIR));
        }

        @Override
        public void onClick(ExampleMaterialMenu holder,
                org.bukkit.event.inventory.InventoryClickEvent event) {
            event.getWhoClicked().sendMessage(Util.mm("<green>This slot is AIR!"));
        }
    }

}
