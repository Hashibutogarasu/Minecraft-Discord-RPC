package com.Hashibutogarasu.mcrpc;

import com.Hashibutogarasu.mcrpc.Config.Configs;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCRPCTweetModClient implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("MCRPC");

    @Override
    public void onInitializeClient() {
        var keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mcrpc.showsetting", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_N, // The keycode of the key
                "category.mcrpc.keybinds" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                Configs configs = new Configs();
                MinecraftClient.getInstance().setScreen(configs.screen);
                //MinecraftClient.getInstance().setScreen(new Tweetscreen(new TweetScreenGUI()));
                //client.player.sendSystemMessage(new LiteralText("Key 1 was pressed!"), UUID.randomUUID());
            }
        });
    }
}


