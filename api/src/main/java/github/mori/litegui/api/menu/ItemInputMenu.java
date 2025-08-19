package github.mori.litegui.api.menu;

import github.mori.litegui.api.internal.DevUtil;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.button.ItemButton;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public abstract class ItemInputMenu extends MenuHolder {

    protected final Plugin plugin;

    public ItemInputMenu(@NotNull Plugin plugin, @NotNull Component title) {
        this(plugin, title, new ItemBuilder(Material.END_CRYSTAL)
                .name(DevUtil.mm("Click with an item to set it")).build());
    }

    public ItemInputMenu(@NotNull Plugin plugin, @NotNull Component title,
            @NotNull ItemStack defaultItem) {
        super(InventoryType.HOPPER, title);
        this.plugin = plugin;
        this.defaultItem = defaultItem;

        setButton(0, new ItemButton<>(getYesItemStack()) {

            @Override
            public void onClick(@NotNull MenuHolder holder, @NotNull InventoryClickEvent event) {
                if (inputItem != null) {
                    onYesClick(event, inputItem);
                } else {
                    onYesClickWithoutInput(event);
                }
            }

        });

        setButton(4, new ItemButton<>(getNoItemStack()) {

            @Override
            public void onClick(@NotNull MenuHolder holder, @NotNull InventoryClickEvent event) {
                onNoClick(event);
            }

        });

        setButton(2, inputItemButton = new ItemButton<>(defaultItem) {

            @Override
            public void onClick(@NotNull ItemInputMenu holder, @NotNull InventoryClickEvent event) {
                ItemStack cursor = event.getCursor();
                setInputItem(cursor);
            }

        });
    }

    private final @NotNull ItemButton<ItemInputMenu> inputItemButton;

    private final @NotNull ItemStack defaultItem;

    private @Nullable ItemStack inputItem;

    protected @Nullable ItemStack getInputItem() {
        return inputItem;
    }

    protected void setInputItem(@Nullable ItemStack inputItem) {
        var isEmpty = inputItem == null || inputItem.isEmpty();
        this.inputItem = isEmpty ? null : inputItem.clone();
        inputItemButton.setIcon(isEmpty ? defaultItem : this.inputItem);
    }

    protected @NotNull ItemStack getYesItemStack() {
        return new ItemBuilder(Material.LIME_CONCRETE)
                .name(DevUtil.mm("Confirm", NamedTextColor.GREEN)).build();
    }

    protected @NotNull ItemStack getNoItemStack() {
        return new ItemBuilder(Material.RED_CONCRETE).name(DevUtil.mm("Cancel", NamedTextColor.RED))
                .build();
    }

    protected void onYesClickWithoutInput(@NotNull InventoryClickEvent event) {
        event.getWhoClicked().sendMessage(DevUtil.mm("No item selected", NamedTextColor.RED));
    }

    protected abstract void onYesClick(@NotNull InventoryClickEvent event,
            @NotNull ItemStack inputItem);

    protected abstract void onNoClick(@NotNull InventoryClickEvent event);

    @Override
    public void onClick(@NotNull InventoryClickEvent event) {
        super.onClick(event);
        if (event.getClickedInventory() != null
                && event.getClickedInventory() == event.getView().getBottomInventory()) {
            event.setCancelled(false);
        }
    }

    @Override
    public void onClose(@NotNull InventoryCloseEvent event) {
        if (event.getReason() == InventoryCloseEvent.Reason.PLAYER) {
            HumanEntity player = event.getPlayer();
            onCloseByPlayer(player);
            plugin.getServer().getScheduler().runTask(plugin, () -> {
                player.openInventory(getInventory());
            });
        }
    }

    protected void onCloseByPlayer(@NotNull HumanEntity player) {
        player.sendMessage(DevUtil.mm("You not closed the input menu. ", NamedTextColor.RED)
                .append(DevUtil.mm("(Use the cancel button to close it)", NamedTextColor.GRAY)));
    }
}
