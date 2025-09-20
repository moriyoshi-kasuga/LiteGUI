# LiteGUI

A simple GUI library to make PaperMC plugin development easier and more intuitive.

## Overview

LiteGUI provides a simple API for creating and managing GUIs (inventory screens) with modern development practices.
It is designed with a focus on a low learning curve and API simplicity, allowing developers to concentrate on the essential logic of their GUIs.

## Usage

To create a GUI, extend the `MenuHolder` class. Button actions can be written concisely within the same file using anonymous classes.

```java
// SimpleMenu.java
import github.mori.litegui.api.ItemBuilder;
import github.mori.litegui.api.button.ItemButton;
import github.mori.litegui.api.menu.MenuHolder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

// Statically importing MiniMessage is convenient
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class SimpleMenu extends MenuHolder {

    public SimpleMenu() {
        // Create an inventory with 27 slots (3 rows)
        super(27, miniMessage().deserialize("<green>Simple Menu"));

        // Implement and place a button using an anonymous class
        setButton(13, new ItemButton<SimpleMenu>(new ItemBuilder(Material.DIAMOND)
                .name(miniMessage().deserialize("<aqua>Click Me!"))
                .lore(miniMessage().deserialize("<gray>Click to perform an action."))
                .build()) {
            @Override
            public void onClick(SimpleMenu holder, InventoryClickEvent event) {
                event.getWhoClicked().sendMessage("You clicked the diamond!");
                event.getWhoClicked().closeInventory();
            }
        });
    }
}
```

To open the created GUI:

```java
// From a player command or similar
Player player = ...;
new SimpleMenu().openInv(player);
```

For more specific and varied examples, please refer to the files under [example/src/main/java/com/example/litegui/menu](example/src/main/java/com/example/litegui/menu).

## Development Setup

To develop a plugin using LiteGUI, you need to set up your environment as follows:

1. **Add LiteGUI to your server:**
    - Download the latest `LiteGUI.jar` from the [GitHub Releases](https://github.com/moriyoshi-kasuga/LiteGUI/releases) page.
    - Place the downloaded `.jar` file into your development server's `plugins` folder.

2. **Add the API to your project's dependencies:**
    - Add JitPack to your `repositories` block in `build.gradle.kts`:

        ```kotlin
        repositories {
            mavenCentral()
            maven { url = uri("https://jitpack.io") }
        }
        ```

    - Add the `LiteGUI` dependency to your `dependencies` block.

        ```kotlin
        dependencies {
            compileOnly("com.github.moriyoshi-kasuga:LiteGUI:v0.5.1")
            // ... other dependencies
        }
        ```

This setup allows your plugin to compile against the `LiteGUI` while relying on the `LiteGUI.jar` installed on the server for runtime.

## Acknowledgments

LiteGUI is strongly inspired by [Jannyboy11/GuiLib](https://github.com/Jannyboy11/GuiLib). I would like to thank Jannyboy11 for creating such a wonderful library.

While respecting GuiLib's excellent design philosophy,
I have reimplemented it from scratch to optimize for the modern PaperMC development environment.
To avoid the impression that this is merely a copy, I have listed the main changes below.

- **API Simplification**: I aimed for a simpler and more intuitive API by removing the mandatory `Plugin` generics in classes like `GuiInventoryHolder`.
- **Full PaperMC Compatibility**: It supports Paper's standard `Adventure Component` for GUI titles and more, enabling flexible text formatting through MiniMessage.
- **Build System Change**: The entire project has been migrated from Maven to Gradle.

Due to these fundamental changes (especially the migration to the Paper API and the build system overhaul),
I chose to develop this library from scratch rather than forking it. LiteGUI inherits the concepts of GuiLib and aims to be a more user-friendly library for modern developers.
