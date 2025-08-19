package github.mori.litegui.api.menu;

import java.util.List;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import github.mori.litegui.api.button.BackPageButton;
import github.mori.litegui.api.button.MenuButton;
import github.mori.litegui.api.button.NextPageButton;
import net.kyori.adventure.text.Component;

public class ListMenu extends PageMenu {

    private final MenuButton<?>[] listButtons;

    public ListMenu(@NotNull int size, MenuButton<?> @Nullable [] listButtons) {
        super(size);
        this.listButtons = listButtons;
    }

    public ListMenu(@NotNull int size, @NotNull List<@Nullable MenuButton<?>> listButtons) {
        super(size);
        this.listButtons = listButtons.toArray(new MenuButton[0]);
    }

    public ListMenu(@NotNull int size, @NotNull Component title,
            @NotNull MenuButton<?> @Nullable [] listButtons) {
        super(size, title);
        this.listButtons = listButtons;
    }

    public ListMenu(@NotNull int size, @NotNull Component title,
            @NotNull List<@Nullable MenuButton<?>> listButtons) {
        super(size, title);
        this.listButtons = listButtons.toArray(new MenuButton[0]);
    }


    @Override
    public void updatePage(int page, @NotNull Inventory inventory) {
        clearButtons();

        setButton(inventory.getSize() - 8, new BackPageButton());
        setButton(inventory.getSize() - 2, new NextPageButton());

        int startIndex = page * (inventory.getSize() - 9);
        int endIndex = Math.min(startIndex + (inventory.getSize() - 9), listButtons.length);
        for (int i = startIndex; i < endIndex; i++) {
            var button = listButtons[i];
            if (button != null) {
                setButton(i - startIndex, button);
            }
        }
    }

    @Override
    public boolean hasPage(int page) {
        int totalButtons = listButtons.length;
        int buttonsPerPage = getInventory().getSize() - 9; // Assuming last row is reserved for
        return page >= 0 && page < Math.ceil((double) totalButtons / buttonsPerPage);
    }
}
