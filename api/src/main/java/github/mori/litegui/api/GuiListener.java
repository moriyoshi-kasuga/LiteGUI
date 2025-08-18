package github.mori.litegui.api;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GuiListener implements Listener {
    private static GuiListener instance = new GuiListener();

    public static GuiListener getInstance() {
        return instance;
    }

    private GuiInventoryHolder getHolder(@Nullable Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        if (inventory.getHolder() instanceof GuiInventoryHolder holder) {
            return holder;
        }
        return null;
    }

    private GuiInventoryHolder getHolder(@NotNull InventoryEvent event) {
        return getHolder(event.getInventory());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryOpen(InventoryOpenEvent event) {
        var holder = getHolder(event);
        if (holder != null) {
            holder.onOpen(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {
        var holder = getHolder(event);
        if (holder != null) {
            holder.onClose(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        var holder = getHolder(event);
        if (holder != null) {
            event.setCancelled(true); // Prevent default behavior
            holder.onClick(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryDrag(InventoryDragEvent event) {
        var holder = getHolder(event);
        if (holder != null) {
            event.setCancelled(true); // Prevent default behavior
            holder.onDrag(event);
        }
    }
}
