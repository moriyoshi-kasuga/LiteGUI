package github.mori.litegui.api.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import github.mori.litegui.api.menu.MenuHolder;

public interface MenuButton<MH extends MenuHolder> {
    default void onClick(@NotNull MH holder, @NotNull InventoryClickEvent event) {
    }

    default @Nullable ItemStack getIcon() {
        return null;
    }

    default boolean onAdd(MH holder, int slot) {
        return true;
    }

    default boolean onRemove(MH holder, int slot) {
        return true;
    }
}
