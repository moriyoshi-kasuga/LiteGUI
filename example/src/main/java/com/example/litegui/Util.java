package com.example.litegui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class Util {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    private Util() {
        // Prevent instantiation
    }

    public static Component mm(Object message) {
        if (message instanceof Component) {
            return (Component) message;
        }

        return miniMessage
                .deserialize(String.valueOf(message))
                .decoration(TextDecoration.ITALIC, false);
    }
}
