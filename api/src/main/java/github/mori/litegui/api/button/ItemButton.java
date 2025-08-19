package github.mori.litegui.api.button;

import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.inventory.ItemStack;
import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.menu.MenuHolder;

public class ItemButton<MH extends MenuHolder> implements MenuButton<MH> {
    public static final ItemStack CROSS_ICON = ItemBuilder.ofSkull(
            "http://textures.minecraft.net/texture/5b30507783c37db3a3092cad043e57951aa8b4c6ea9acc47d604b7eb5aea028")
            .build();

    private final WeakHashMap<MH, Set<Integer>> holders = new WeakHashMap<>();

    protected ItemStack icon;

    public ItemButton() {
        this.icon = null;
    }

    public ItemButton(ItemStack icon) {
        this.icon = icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
        holders.forEach((holder, slots) -> {
            slots.forEach(slot -> {
                holder.getInventory().setItem(slot, icon);
            });
        });
    }

    @Override
    public boolean onAdd(MH holder, int slot) {
        return holders.computeIfAbsent(holder, k -> new HashSet<>()).add(slot);
    }

    @Override
    public boolean onRemove(MH holder, int slot) {
        var slots = holders.get(holder);
        if (slots == null) {
            return true;
        }
        boolean removed = slots.remove(slot);
        if (slots.isEmpty()) {
            holders.remove(holder);
        }
        return removed;
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

}
