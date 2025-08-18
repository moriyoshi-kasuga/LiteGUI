package github.mori.litegui.api.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import github.mori.litegui.api.menu.MenuHolder;

public class CloseButton extends ItemButton<MenuHolder> {

    public CloseButton(ItemStack icon) {
        super(icon);
    }

    @Override
    public void onClick(@NotNull MenuHolder holder, @NotNull InventoryClickEvent event) {
        event.getWhoClicked().closeInventory();
    }

}
