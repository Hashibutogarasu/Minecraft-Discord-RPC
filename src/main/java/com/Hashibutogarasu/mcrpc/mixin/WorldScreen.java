package com.Hashibutogarasu.mcrpc.mixin;

import com.Hashibutogarasu.mcrpc.MCRPCMod;
import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.gui.PlayerListGui;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(World.class)
public class WorldScreen {
    @Inject(at = @At("HEAD"), method = "isClient")
    private void init(CallbackInfoReturnable<Boolean> cir) {
        try{
            if(MinecraftClient.getInstance().isMultiplayerEnabled()) {
                List<? extends PlayerEntity> players = MinecraftClient.getInstance().player.world.getPlayers();
                RunRPC.init(new TranslatableText("mcrpc.inmultiserver",players.stream().count()).getString());
            }
            else{
                RunRPC.init(new TranslatableText("mcrpc.singleplayer","").getString());
            }
        }
        catch(Exception e){

        }
    }
}
