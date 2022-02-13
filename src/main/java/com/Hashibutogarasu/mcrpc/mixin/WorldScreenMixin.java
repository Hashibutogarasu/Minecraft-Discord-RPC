package com.Hashibutogarasu.mcrpc.mixin;

import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.Objects;

@Mixin(World.class)
public class WorldScreenMixin {
    @Inject(at = @At("HEAD"), method = "isClient")
    private void init(CallbackInfoReturnable<Boolean> cir) {
        try{
            if(!MinecraftClient.getInstance().isInSingleplayer()) {
                List<? extends PlayerEntity> players = MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.world.getPlayers() : null;
                RunRPC.login(new TranslatableText("mcrpc.inmultiserver", (long) Objects.requireNonNull(players).size()).getString());
            }
            else{
                int x = (int) (MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getPos().x : 0);
                int y = (int) (MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getPos().y : 0);
                int z = (int) (MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getPos().z : 0);
                RunRPC.login(new TranslatableText("mcrpc.singleplayer",x,y,z).getString());
            }
        }
        catch(Exception ignored){

        }
    }
}
