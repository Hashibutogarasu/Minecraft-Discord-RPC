package com.Hashibutogarasu.mcrpc.mixin;

import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import net.arikia.dev.drpc.DiscordRPC;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class ClientMixin {

    @Inject(at = @At("HEAD"), method = "run()V")
    private void onEnable(CallbackInfo info) {
        RunRPC.login("Loading");
    }

    @Inject(at = @At("HEAD"), method = "stop()V")
    private void onDisable(CallbackInfo info) {
        RunRPC.login("Loading");
        DiscordRPC.discordShutdown();
    }

    @Inject(at = @At("HEAD"), method = "isPaused")
    private void onEnable(CallbackInfoReturnable<Boolean> cir) {
        RunRPC.login(new TranslatableText("mcrpc.pausing").getString());
    }
}