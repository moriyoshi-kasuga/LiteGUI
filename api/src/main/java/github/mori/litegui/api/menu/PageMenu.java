package github.mori.litegui.api.menu;

import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public abstract class PageMenu extends MenuHolder {
    private int currentPage;

    public PageMenu(@NotNull InventoryType type) {
        super(type);
    }

    public PageMenu(@NotNull InventoryType type, @NotNull Component title) {
        super(type, title);
    }

    public PageMenu(@NotNull int size) {
        super(size);
    }

    public PageMenu(@NotNull int size, @NotNull Component title) {
        super(size, title);
    }

    @Override
    public void onOpen(@NotNull InventoryOpenEvent event) {
        updatePage(currentPage, event.getInventory());
    }

    public void setCurrentPage(int page) {
        if (page < 0 || !hasPage(page)) {
            throw new IllegalArgumentException("Invalid page number: " + page);
        }
        this.currentPage = page;
        updatePage(page, getInventory());
    }

    public abstract void updatePage(int page, @NotNull Inventory inventory);

    public abstract boolean hasPage(int page);

    public abstract int getCurrentPage();

}
