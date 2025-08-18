package com.example.litegui;

import java.util.function.Supplier;
import java.util.stream.Stream;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.example.litegui.menu.SimpleMenu;
import com.google.common.collect.HashBiMap;
import github.mori.litegui.api.menu.ListMenu;
import github.mori.litegui.api.menu.MenuHolder;

public class ExamplePlugin extends JavaPlugin {

    public static final HashBiMap<String, Supplier<MenuHolder>> INVENTORIES = HashBiMap.create();

    static {
        INVENTORIES.put("simple", SimpleMenu::new);
        INVENTORIES.put("materials", () -> {
            ItemStack[] items = Stream.of(Material.values()).filter(Material::isItem)
                    .map(ItemStack::new).toArray(ItemStack[]::new);
            return new ListMenu(54, Util.mm("<red>Materials</red>"), items);
        });
    }

    @Override
    public void onEnable() {
        var exampleTabExecutor = new ExampleCommand();
        var exampleCommand = getCommand("example");
        if (exampleCommand != null) {
            exampleCommand.setExecutor(exampleTabExecutor);
            exampleCommand.setTabCompleter(exampleTabExecutor);
        }
    }

}
