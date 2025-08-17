package github.mori.litegui.api;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ItemBuilder {
    private final ItemStack itemStack;

    public ItemBuilder(@NotNull Material material) {
        this.itemStack = new ItemStack(Objects.requireNonNull(material, "Material cannot be null"));
    }

    public ItemBuilder(@NotNull ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "ItemStack cannot be null");
    }
}
