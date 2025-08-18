package github.mori.litegui.api.button;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.menu.PageMenu;
import net.kyori.adventure.text.Component;

public class BackPageButton extends ItemButton<PageMenu> {
    public static final ItemStack BACK_PAGE_ICON =
            new ItemBuilder(Material.TERRACOTTA).name(Component.text("Back Page")).build();

    private final Component noBackPageMessage;

    public BackPageButton() {
        this(BACK_PAGE_ICON, Component.text("No back pages available."));
    }

    public BackPageButton(@Nullable Component noBackPageMessage) {
        this(BACK_PAGE_ICON, noBackPageMessage);
    }

    public BackPageButton(@NotNull ItemStack icon) {
        this(icon, Component.text("No back pages available."));
    }

    public BackPageButton(@NotNull ItemStack icon, @Nullable Component noBackPageMessage) {
        super(icon);
        this.noBackPageMessage = noBackPageMessage;
    }

    @Override
    public void onClick(@NotNull PageMenu holder, @NotNull InventoryClickEvent event) {
        int previousPage = holder.getCurrentPage() - 1;
        if (holder.hasPage(previousPage)) {
            holder.setCurrentPage(previousPage);
        } else if (this.noBackPageMessage != null) {
            event.getWhoClicked().sendMessage(this.noBackPageMessage);
        }
    }

}
