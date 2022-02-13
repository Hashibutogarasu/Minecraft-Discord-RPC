package com.Hashibutogarasu.mcrpc.rpc;

import com.Hashibutogarasu.mcrpc.MCRPCMod;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

import static com.Hashibutogarasu.mcrpc.rpc.StatusObject.status;

public class RPCMain implements ReadyCallback {

    public RPCMain(String ID,String Status){

        try {
            status = Status;

            DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
                MCRPCMod.LOGGER.info("Welcome " + user.username + "#" + user.discriminator + ".");
                DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(status);
                DiscordRPC.discordUpdatePresence(presence.build());
            }).build();

            DiscordRPC.discordInitialize(ID, handlers, true);
            DiscordRPC.discordRunCallbacks();

            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(status);
            DiscordRPC.discordUpdatePresence(presence.build());
        }
        catch (Exception e){

        }
    }

    @Override
    public void apply(DiscordUser discordUser) {

    }
}
