module github.mori.litegui {

    requires org.bukkit;
    requires transitive github.mori.litegui.api;

    exports github.mori.litegui;
    opens github.mori.litegui to org.bukkit;
}
