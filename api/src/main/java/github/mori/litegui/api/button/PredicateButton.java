package github.mori.litegui.api.button;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import github.mori.litegui.api.menu.MenuHolder;

public class PredicateButton<MH extends MenuHolder> implements MenuButton<MH> {
    private final MenuButton<MH> delegate;
    private final BiPredicate<MH, InventoryClickEvent> predicate;
    private final BiConsumer<MH, InventoryClickEvent> fallback;

    public PredicateButton(@NotNull MenuButton<MH> delegate,
            @NotNull BiPredicate<MH, InventoryClickEvent> predicate) {
        this(delegate, predicate, null);
    }

    public PredicateButton(@NotNull MenuButton<MH> delegate,
            @NotNull BiPredicate<MH, InventoryClickEvent> predicate,
            @Nullable BiConsumer<MH, InventoryClickEvent> fallback) {
        this.delegate = Objects.requireNonNull(delegate, "Delegate cannot be null");
        this.predicate = Objects.requireNonNull(predicate, "Predicate cannot be null");
        this.fallback = fallback;
    }

    @Override
    public void onClick(@NotNull MH holder, @NotNull InventoryClickEvent event) {
        if (predicate.test(holder, event)) {
            delegate.onClick(holder, event);
        } else if (fallback != null) {
            fallback.accept(holder, event);
        }
    }

    @Override
    public @Nullable ItemStack getIcon() {
        return this.delegate.getIcon();
    }

    @Override
    public boolean onAdd(MH holder, int slot) {
        return this.delegate.onAdd(holder, slot);
    }

    @Override
    public boolean onRemove(MH holder, int slot) {
        return this.delegate.onRemove(holder, slot);
    }
}
