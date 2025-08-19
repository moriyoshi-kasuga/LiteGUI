package github.mori.litegui.api.internal;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class DevUtil {

    private DevUtil() {
        // Utility class, prevent instantiation
    }

    public static Component mm(String message) {
        return Component.text(message).decoration(TextDecoration.ITALIC, false);
    }

    public static Component mm(String message, TextColor color) {
        return Component.text(message).color(color).decoration(TextDecoration.ITALIC, false);
    }

}
