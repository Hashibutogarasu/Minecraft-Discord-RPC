package com.Hashibutogarasu.mcrpc.mixin;

import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.profiling.jfr.event.WorldLoadFinishedEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class LoadingScreen {
    @Inject(at = @At("HEAD"), method = "isClient")
    private void init(CallbackInfoReturnable<Boolean> cir) {
        RunRPC.init(new TranslatableText("mcrpc.inworld").getString());
    }
}
