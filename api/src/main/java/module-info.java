@SuppressWarnings({ "requires-automatic", "requires-transitive-automatic" }) module github.mori.litegui.api {

    requires net.kyori.examination.api;
    requires transitive org.bukkit;
    requires transitive net.kyori.adventure;
    requires transitive org.jetbrains.annotations;

    exports github.mori.litegui.api;

    opens github.mori.litegui.api;

}
