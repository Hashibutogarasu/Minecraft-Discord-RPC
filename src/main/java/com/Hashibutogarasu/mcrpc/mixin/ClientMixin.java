package com.Hashibutogarasu.mcrpc.mixin;

import com.Hashibutogarasu.mcrpc.Config.Configs;
import com.Hashibutogarasu.mcrpc.MCRPCMod;
import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import com.Hashibutogarasu.mcrpc.gui.Screen.KeyConfigScreen;
import com.Hashibutogarasu.mcrpc.rpc.RPCMain;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;


@Mixin(MinecraftClient.class)
public class ClientMixin {

    @Inject(at = @At("HEAD"), method = "run()V")
    private void onEnable(CallbackInfo info) {
        RunRPC.init(new TranslatableText("mcrpc.loading").getString());
    }

    @Inject(at = @At("HEAD"), method = "stop()V")
    private void onDisable(CallbackInfo info) {
        RunRPC.init(new TranslatableText("mcrpc.closing").getString());
        DiscordRPC.discordShutdown();
    }

    @Inject(at = @At("HEAD"), method = "isPaused")
    private void onEnable(CallbackInfoReturnable<Boolean> cir) {
        RunRPC.init(new TranslatableText("mcrpc.pausing").getString());
    }
}