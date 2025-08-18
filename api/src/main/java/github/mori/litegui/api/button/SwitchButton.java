package github.mori.litegui.api.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import github.mori.litegui.api.menu.MenuHolder;

public class SwitchButton<MH extends MenuHolder> extends ItemButton<MH> {

    private boolean state;

    private final ItemStack defaultItem;
    private final ItemStack toggledItem;

    public SwitchButton(@NotNull ItemStack defaultItem, @NotNull ItemStack toggledItem) {
        this(defaultItem, toggledItem, false);
    }

    public SwitchButton(@NotNull ItemStack defaultItem, @NotNull ItemStack toggledItem,
            boolean initialState) {
        super(defaultItem);
        this.state = initialState;
        this.defaultItem = defaultItem;
        this.toggledItem = toggledItem;
    }

    public boolean isOn() {
        return state;
    }

    public void toggle() {
        this.state = !this.state;
        setIcon(state ? toggledItem : defaultItem);
    }

    @Override
    public void onClick(@NotNull MH holder, @NotNull InventoryClickEvent event) {
        toggle();
    }


}
