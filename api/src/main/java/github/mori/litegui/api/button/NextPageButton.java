package github.mori.litegui.api.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import github.mori.litegui.api.internal.DevUtil;
import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.menu.PageMenu;
import net.kyori.adventure.text.format.NamedTextColor;

public class NextPageButton extends ItemButton<PageMenu> {
    public NextPageButton() {
        this(ItemBuilder.ofSkull(
                "http://textures.minecraft.net/texture/7c69d41076a8dea4f06d3f1a9ac47cc996988b74a0913ab2ac1a74caf7081918")
                .name(DevUtil.mm("Next Page", NamedTextColor.GREEN)).build());
    }

    public NextPageButton(@NotNull ItemStack icon) {
        this(icon, new ItemBuilder(CROSS_ICON)
                .name(DevUtil.mm("No more pages", NamedTextColor.GRAY)).build());
    }

    public NextPageButton(@NotNull ItemStack defaultIcon, ItemStack noMorePagesIcon) {
        super(defaultIcon);
        this.defaultIcon = defaultIcon;
        this.noMorePagesIcon = noMorePagesIcon;
    }

    private final ItemStack defaultIcon;
    private final ItemStack noMorePagesIcon;

    @Override
    public void onClick(@NotNull PageMenu holder, @NotNull InventoryClickEvent event) {
        int nextPage = holder.getCurrentPage() + 1;
        if (holder.hasPage(nextPage)) {
            holder.setCurrentPage(nextPage);
        }
    }

    @Override
    public boolean onAdd(PageMenu holder, int slot) {
        if (holder.hasPage(holder.getCurrentPage() + 1)) {
            setIcon(defaultIcon);
        } else {
            setIcon(noMorePagesIcon);
        }
        return super.onAdd(holder, slot);
    }

}
