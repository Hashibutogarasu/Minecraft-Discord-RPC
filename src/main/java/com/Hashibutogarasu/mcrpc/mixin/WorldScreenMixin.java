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

@Mixin(World.class)
public class WorldScreen {
    @Inject(at = @At("HEAD"), method = "isClient")
    private void init(CallbackInfoReturnable<Boolean> cir) {
        try{
            if(!MinecraftClient.getInstance().isInSingleplayer()) {
                List<? extends PlayerEntity> players = MinecraftClient.getInstance().player.world.getPlayers();
                RunRPC.update(new TranslatableText("mcrpc.inmultiserver",players.stream().count()).getString());
            }
            else{
                int x = (int)MinecraftClient.getInstance().player.getPos().x;
                int y = (int)MinecraftClient.getInstance().player.getPos().y;
                int z = (int)MinecraftClient.getInstance().player.getPos().z;
                RunRPC.update(new TranslatableText("mcrpc.singleplayer",x,y,z).getString());
            }
        }
        catch(Exception e){

        }
    }
}
