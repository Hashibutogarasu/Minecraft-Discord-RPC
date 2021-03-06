package com.Hashibutogarasu.mcrpc.mixin;

import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		RunRPC.login(new TranslatableText("mcrpc.intitlescreen").getString());
	}
}
