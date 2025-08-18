package com.example.litegui;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.example.litegui.ExamplePlugin.INVENTORIES;

import github.mori.litegui.api.menu.MenuHolder;

public class ExampleCommand implements TabExecutor {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return INVENTORIES.keySet().stream()
                    .filter(name -> name.startsWith(args[0].toLowerCase()))
                    .toList();
        }
        return List.of(); // No further suggestions
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /example <inventory_name>");
            INVENTORIES.keySet().forEach(name -> sender.sendMessage("- " + name));
            return true;
        }
        String inventoryName = args[0];
        Supplier<MenuHolder> inventorySupplier = INVENTORIES.get(inventoryName);
        if (inventorySupplier == null) {
            sender.sendMessage("Unknown inventory: " + inventoryName);
            return true;
        }
        MenuHolder inventory = inventorySupplier.get();
        if (inventory == null) {
            sender.sendMessage("Failed to create inventory: " + inventoryName);
            return true;
        }
        if (sender instanceof org.bukkit.entity.Player player) {
            inventory.openInv(player);
            return true;
        } else {
            sender.sendMessage("This command can only be used by players.");
            return false;
        }
    }

}
