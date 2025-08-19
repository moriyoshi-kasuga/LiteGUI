package com.example.litegui.menu;

import java.util.stream.Stream;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import com.example.litegui.Util;
import github.mori.litegui.api.button.ItemButton;
import github.mori.litegui.api.button.MenuButton;
import github.mori.litegui.api.menu.ListMenu;

public class ExampleMaterialMenu extends ListMenu {

    public ExampleMaterialMenu() {
        super(54, Util.mm("<red>Materials</red>"));
        this.materials = Stream.of(Material.values())
                .filter(material -> material.isItem() && !material.isLegacy())
                .toArray(Material[]::new);
    }

    private final Material[] materials;

    @Override
    public int getTotalButtons() {
        return materials.length;
    }

    @Override
    public @Nullable MenuButton<?> getListButton(int index) {
        if (index < 0 || index >= materials.length) {
            return null; // Out of bounds
        }
        var material = materials[index];
        if (material.isAir()) {
            return new AirButton();
        }
        return new ItemButton<>(new ItemStack(material));
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
