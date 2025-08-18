package github.mori.litegui.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public final class ItemBuilder {
    private final ItemStack itemStack;

    public ItemBuilder(@NotNull Material material) {
        this.itemStack = new ItemStack(Objects.requireNonNull(material, "Material cannot be null"));
    }

    public ItemBuilder(@NotNull ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "ItemStack cannot be null");
    }

    public ItemBuilder change(final Consumer<? super ItemStack> consumer) {
        consumer.accept(this.itemStack);
        return this;
    }

    public ItemBuilder changeItemMeta(final Consumer<? super ItemMeta> consumer) {
        return change(
                i -> {
                    final ItemMeta meta = i.getItemMeta();
                    consumer.accept(meta);
                    i.setItemMeta(meta);
                });
    }

    public ItemBuilder amount(final int amount) {
        return change(i -> i.setAmount(amount));
    }

    public ItemBuilder enchant(final Enchantment enchantment, final int level) {
        return change(i -> i.addUnsafeEnchantment(enchantment, level));
    }

    public ItemBuilder unEnchant(final Enchantment enchantment) {
        return change(i -> i.removeEnchantment(enchantment));
    }

    public ItemBuilder damage(final int damage) {
        return changeItemMeta(meta -> ((Damageable) meta).setDamage(damage));
    }

    public ItemBuilder durability(final int durability) {
        return changeItemMeta(
                meta -> ((Damageable) meta)
                        .setDamage(Math.max(0, this.itemStack.getType().getMaxDurability() - durability)));
    }

    public ItemBuilder name(final Component displayName) {
        return changeItemMeta(meta -> meta.displayName(displayName));
    }

    public List<Component> getLore() {
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        if (itemMeta.hasLore()) {
            return itemMeta.lore();
        }
        final List<Component> lore = new ArrayList<>();
        itemMeta.lore(lore);
        return lore;
    }

    public ItemBuilder lore(@NotNull final List<Component> lore) {
        return changeItemMeta(meta -> meta.lore(lore));
    }

    public ItemBuilder lore(@NotNull final Component... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemBuilder addLore(final Component... addLore) {
        return addLore(Arrays.asList(addLore));
    }

    public ItemBuilder addLore(final List<Component> addLore) {
        var lore = getLore();
        lore.addAll(addLore);
        return lore(lore);
    }

    public ItemBuilder insertLore(final int line, final Component str) {
        var lore = getLore();
        lore.add(line, str);
        return lore(lore);
    }

    public ItemBuilder setLore(final int line, final Component str) {
        var lore = getLore();
        while (lore.size() <= line) {
            lore.add(Component.empty());
        }
        lore.set(line, str);
        return lore(lore);
    }

    public ItemBuilder removeLore(final int line) {
        var lore = getLore();
        lore.remove(line);
        return lore(lore);
    }

    public ItemBuilder unbreakable(final boolean unbreakable) {
        return changeItemMeta(meta -> meta.setUnbreakable(unbreakable));
    }

    public ItemBuilder flags(final ItemFlag... flags) {
        return changeItemMeta(meta -> meta.addItemFlags(flags));
    }

    public ItemBuilder unFlags(final ItemFlag... flags) {
        return changeItemMeta(meta -> meta.removeItemFlags(flags));
    }

    public ItemBuilder glow() {
        return enchant(Enchantment.LURE, 1).flags(ItemFlag.HIDE_ENCHANTS);
    }

    public ItemBuilder unGlow() {
        return unEnchant(Enchantment.LURE).unFlags(ItemFlag.HIDE_ENCHANTS);
    }

    public ItemBuilder customModelData(
            final Consumer<CustomModelDataComponent> consumer) {
        return changeItemMeta(meta -> {
            var data = meta.getCustomModelDataComponent();
            consumer.accept(data);
            meta.setCustomModelDataComponent(data);
        });
    }

    public ItemStack build() {
        return this.itemStack;
    }
}
