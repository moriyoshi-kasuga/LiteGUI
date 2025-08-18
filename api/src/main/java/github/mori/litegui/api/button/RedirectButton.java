package github.mori.litegui.api.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;
import github.mori.litegui.api.menu.MenuHolder;

public interface RedirectButton<MH extends MenuHolder> extends MenuButton<MH> {
    @Override
    public default void onClick(MH holder, InventoryClickEvent event) {
        event.getView().close();

        var player = event.getWhoClicked();
        var to = to(holder, event);
        if (to == null) {
            return; // If the redirect inventory is null, do nothing
        }
        player.openInventory(to);
    }

    public @Nullable Inventory to(MH MenuHolder, InventoryClickEvent event);
}
