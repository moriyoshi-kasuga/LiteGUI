package github.mori.litegui;

import org.bukkit.plugin.java.JavaPlugin;

import github.mori.litegui.api.GuiListener;

public class LiteGUIPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(GuiListener.getInstance(), this);
    }
}
