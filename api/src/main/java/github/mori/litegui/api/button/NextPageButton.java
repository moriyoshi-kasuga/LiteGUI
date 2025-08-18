package github.mori.litegui.api.button;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.menu.PageMenu;
import net.kyori.adventure.text.Component;

public class NextPageButton extends ItemButton<PageMenu> {
    public static final ItemStack NEXT_PAGE_ICON = new ItemBuilder(Material.TERRACOTTA)
            .name(Component.text("Next Page")).build();

    private final Component noMorePagesMessage;

    public NextPageButton() {
        this(NEXT_PAGE_ICON, Component.text("No more pages available."));
    }

    public NextPageButton(@Nullable Component noMorePagesMessage) {
        this(NEXT_PAGE_ICON, noMorePagesMessage);
    }

    public NextPageButton(@NotNull ItemStack icon) {
        this(icon, Component.text("No more pages available."));
    }

    public NextPageButton(@NotNull ItemStack icon, @Nullable Component noMorePagesMessage) {
        super(icon);
        this.noMorePagesMessage = noMorePagesMessage;
    }

    @Override
    public void onClick(@NotNull PageMenu holder, @NotNull InventoryClickEvent event) {
        int nextPage = holder.getCurrentPage() + 1;
        if (holder.hasPage(nextPage)) {
            holder.setCurrentPage(nextPage);
        } else if (this.noMorePagesMessage != null) {
            event.getWhoClicked().sendMessage(this.noMorePagesMessage);
        }
    }

}
