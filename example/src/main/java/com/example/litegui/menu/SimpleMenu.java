package com.example.litegui.menu;

import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.button.ItemButton;
import github.mori.litegui.api.menu.MenuHolder;
import net.kyori.adventure.text.Component;

import static com.example.litegui.Util.mm;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SimpleMenu extends MenuHolder {

    public SimpleMenu() {
        super(27, mm("<green>Simple Menu"));
        setButton(12, new ItemButton<SimpleMenu>(new ItemBuilder(Material.DIAMOND)
                .name(mm("<aqua>Click Me!"))
                .lore(mm("<gray>Click to perform an action"))
                .build()) {
            @Override
            public void onClick(SimpleMenu holder, InventoryClickEvent event) {
                event.getWhoClicked().sendMessage(mm("<green>You clicked the diamond!"));
            }
        });
        setButton(14, new CountUpButton());
    }

    private static class CountUpButton extends ItemButton<SimpleMenu> {
        private int count = 0;

        private static Component title(int count) {
            return mm("<aqua>Current Count: " + count);
        }

        public CountUpButton() {
            super(new ItemBuilder(Material.EMERALD)
                    .name(title(0))
                    .lore(mm("<gray>Click to count up"))
                    .build());
        }

        @Override
        public void onClick(SimpleMenu holder, InventoryClickEvent event) {
            count++;
            setIcon(new ItemBuilder(getIcon()).name(title(count)).build());
        }
    }

}
