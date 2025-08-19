package github.mori.litegui.api.menu;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import github.mori.litegui.api.button.BackPageButton;
import github.mori.litegui.api.button.MenuButton;
import github.mori.litegui.api.button.NextPageButton;
import net.kyori.adventure.text.Component;

public abstract class ListMenu extends PageMenu {

    /**
     * Creates a new ListMenu with the specified size.
     *
     * @param size minimum size of the inventory, must be at least 18
     */
    public ListMenu(@NotNull int size) {
        super(size);
    }


    /**
     * Creates a new ListMenu with the specified size and title.
     *
     * @param size minimum size of the inventory, must be at least 18
     * @param title the title of the menu, must not be null
     */
    public ListMenu(@NotNull int size, @NotNull Component title) {
        super(size, title);
    }

    @Override
    public void updatePage(int page, @NotNull Inventory inventory) {
        clearButtons();

        setButton(inventory.getSize() - 8, new BackPageButton());
        setButton(inventory.getSize() - 2, new NextPageButton());

        int startIndex = page * (inventory.getSize() - 9);
        int endIndex = Math.min(startIndex + (inventory.getSize() - 9), getTotalButtons());
        for (int i = startIndex; i < endIndex; i++) {
            var button = getListButton(i);
            if (button != null) {
                setButton(i - startIndex, button);
            }
        }
    }

    @Override
    public boolean hasPage(int page) {
        int buttonsPerPage = getInventory().getSize() - 9; // Assuming last row is reserved for
        return page >= 0 && page < Math.ceil((double) getTotalButtons() / buttonsPerPage);
    }

    public abstract int getTotalButtons();

    public abstract @Nullable MenuButton<?> getListButton(int index);
}
