package github.mori.litegui.api.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.internal.DevUtil;
import github.mori.litegui.api.menu.PageMenu;
import net.kyori.adventure.text.format.NamedTextColor;

public class BackPageButton extends ItemButton<PageMenu> {
    public BackPageButton() {
        this(ItemBuilder.ofSkull(
                "http://textures.minecraft.net/texture/de1dfc11a837111d22b001a14461f9a7fc093522f88c58faefd6adeffcd4e9ab")
                .name(DevUtil.mm("Previous Page", NamedTextColor.GREEN)).build());
    }

    public BackPageButton(@NotNull ItemStack icon) {
        this(icon, new ItemBuilder(CROSS_ICON)
                .name(DevUtil.mm("No previous pages", NamedTextColor.GRAY)).build());
    }

    public BackPageButton(@NotNull ItemStack defaultIcon, @NotNull ItemStack noMorePagesIcon) {
        super(defaultIcon);
        this.defaultIcon = defaultIcon;
        this.noMorePagesIcon = noMorePagesIcon;
    }

    private final ItemStack defaultIcon;
    private final ItemStack noMorePagesIcon;

    @Override
    public void onClick(@NotNull PageMenu holder, @NotNull InventoryClickEvent event) {
        int previousPage = holder.getCurrentPage() - 1;
        if (holder.hasPage(previousPage)) {
            holder.setCurrentPage(previousPage);
        }
    }

    @Override
    public boolean onAdd(PageMenu holder, int slot) {
        if (holder.hasPage(holder.getCurrentPage() - 1)) {
            setIcon(defaultIcon);
        } else {
            setIcon(noMorePagesIcon);
        }
        return super.onAdd(holder, slot);
    }
}
