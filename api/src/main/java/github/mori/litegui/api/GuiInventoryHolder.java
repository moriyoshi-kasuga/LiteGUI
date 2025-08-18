package github.mori.litegui.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public abstract class GuiInventoryHolder implements org.bukkit.inventory.InventoryHolder {
    private final Inventory inventory;

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public GuiInventoryHolder(@NotNull InventoryType type) {
        this.inventory = Bukkit.createInventory(this, type);
    }

    public GuiInventoryHolder(@NotNull InventoryType type, @NotNull Component title) {
        this.inventory = Bukkit.createInventory(this, type, title);
    }

    public GuiInventoryHolder(@NotNull int size) {
        this.inventory = Bukkit.createInventory(this, size);
    }

    public GuiInventoryHolder(@NotNull int size, @NotNull Component title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void onOpen(@NotNull InventoryOpenEvent event) {
    }

    public void onClose(@NotNull InventoryCloseEvent event) {
    }

    public void onClick(@NotNull InventoryClickEvent event) {
    }

    public void onDrag(@NotNull InventoryDragEvent event) {
    }

    public void openInv(@NotNull HumanEntity entity) {
        entity.openInventory(getInventory());
    }

    public void openInv(@NotNull Iterable<@NotNull HumanEntity> entities) {
        entities.forEach(this::openInv);
    }

    public void openInv(@NotNull HumanEntity... entities) {
        for (HumanEntity entity : entities) {
            openInv(entity);
        }
    }
}
