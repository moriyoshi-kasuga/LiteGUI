package com.example.litegui;

import java.util.function.Supplier;

import org.bukkit.plugin.java.JavaPlugin;

import com.example.litegui.menu.SimpleMenu;
import com.google.common.collect.HashBiMap;

import github.mori.litegui.api.menu.MenuHolder;

public class ExamplePlugin extends JavaPlugin {

    public static final HashBiMap<String, Supplier<MenuHolder>> INVENTORIES = HashBiMap.create();

    static {
        INVENTORIES.put("simple", SimpleMenu::new);
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
