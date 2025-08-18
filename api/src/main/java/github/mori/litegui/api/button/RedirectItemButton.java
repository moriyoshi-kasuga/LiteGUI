package github.mori.litegui.api.button;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import github.mori.litegui.api.menu.MenuHolder;

public class RedirectItemButton<MH extends MenuHolder> extends ItemButton<MH>
        implements RedirectButton<MH> {

    private final BiFunction<MH, InventoryClickEvent, Inventory> redirectFunction;

    public RedirectItemButton(@NotNull ItemStack icon,
            @NotNull Supplier<Inventory> redirectSupplier) {
        this(icon, (holder, event) -> redirectSupplier.get());
    }

    public RedirectItemButton(@NotNull ItemStack icon,
            @NotNull BiFunction<MH, InventoryClickEvent, Inventory> redirectFunction) {
        super(icon);
        this.redirectFunction = redirectFunction;
    }

    @Override
    public void onClick(MH holder, InventoryClickEvent event) {
        RedirectButton.super.onClick(holder, event);
    }

    @Override
    public @Nullable Inventory to(MH MenuHolder, InventoryClickEvent event) {
        return redirectFunction.apply(MenuHolder, event);
    }
}
