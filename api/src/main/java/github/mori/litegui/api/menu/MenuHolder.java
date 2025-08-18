package github.mori.litegui.api.menu;

import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import github.mori.litegui.api.GuiInventoryHolder;
import github.mori.litegui.api.button.MenuButton;
import net.kyori.adventure.text.Component;

public class MenuHolder extends GuiInventoryHolder {

    private final MenuButton<?>[] menuButtons;

    public MenuHolder(@NotNull InventoryType type) {
        super(type);
        this.menuButtons = new MenuButton<?>[type.getDefaultSize()];
    }

    public MenuHolder(@NotNull InventoryType type, @NotNull Component title) {
        super(type, title);
        this.menuButtons = new MenuButton<?>[type.getDefaultSize()];
    }

    public MenuHolder(@NotNull int size) {
        super(size);
        this.menuButtons = new MenuButton<?>[size];
    }

    public MenuHolder(@NotNull int size, @NotNull Component title) {
        super(size, title);
        this.menuButtons = new MenuButton<?>[size];
    }

    public boolean setButton(int slot, @Nullable MenuButton<MenuHolder> button) {
        if (!unsetButton(slot)) {
            return false; // Unable to remove existing button
        }
        if (button == null) {
            return true; // No button to set
        }

        var added = button.onAdd(this, slot);
        if (added) {
            getInventory().setItem(slot, button.getIcon());
            menuButtons[slot] = button;
            return true;
        } else {
            return false; // Button could not be added
        }
    }

    public boolean unsetButton(int slot) {
        if (slot < 0 || slot >= menuButtons.length) {
            return false; // Invalid slot
        }
        @SuppressWarnings("unchecked")
        MenuButton<MenuHolder> button = (MenuButton<MenuHolder>) menuButtons[slot];

        if (button == null) {
            return true;
        }

        boolean removed = button.onRemove(this, slot);
        if (removed) {
            menuButtons[slot] = null;
            getInventory().setItem(slot, null);
            return true;
        } else {
            return false;
        }
    }

    public void clearButtons() {
        for (int i = 0; i < menuButtons.length; i++) {
            unsetButton(i);
        }
    }

}
