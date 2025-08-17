@SuppressWarnings({ "requires-automatic", "requires-transitive-automatic" }) module github.mori.litegui.api {

    requires transitive org.bukkit;
    requires org.jetbrains.annotations;

    exports github.mori.litegui.api;

    opens github.mori.litegui.api;

}
