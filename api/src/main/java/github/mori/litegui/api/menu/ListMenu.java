package github.mori.litegui.api.menu;

import java.util.List;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import github.mori.litegui.api.button.BackPageButton;
import github.mori.litegui.api.button.ItemButton;
import github.mori.litegui.api.button.NextPageButton;
import net.kyori.adventure.text.Component;

public class ListMenu extends PageMenu {

    private final ItemStack[] items;

    public ListMenu(@NotNull int size, ItemStack[] items) {
        super(size);
        this.items = items;
    }

    public ListMenu(@NotNull int size, @NotNull List<@Nullable ItemStack> items) {
        super(size);
        this.items = items.toArray(new ItemStack[0]);
    }

    public ListMenu(@NotNull int size, @NotNull Component title, ItemStack[] items) {
        super(size, title);
        this.items = items;
    }

    public ListMenu(@NotNull int size, @NotNull Component title,
            @NotNull List<@Nullable ItemStack> items) {
        super(size, title);
        this.items = items.toArray(new ItemStack[0]);
    }


    @Override
    public void updatePage(int page, @NotNull Inventory inventory) {
        clearButtons();

        setButton(inventory.getSize() - 8, new BackPageButton());
        setButton(inventory.getSize() - 2, new NextPageButton());

        int startIndex = page * (inventory.getSize() - 9);
        int endIndex = Math.min(startIndex + (inventory.getSize() - 9), items.length);
        for (int i = startIndex; i < endIndex; i++) {
            ItemStack item = items[i];
            if (item != null) {
                setButton(i - startIndex, new ItemButton<>(item));
            }
        }
    }

    @Override
    public boolean hasPage(int page) {
        int totalItems = items.length;
        int itemsPerPage = getInventory().getSize() - 9; // Assuming last row is reserved for
        return page >= 0 && page < Math.ceil((double) totalItems / itemsPerPage);
    }
}
