package com.Hashibutogarasu.mcrpc.Config;

import com.Hashibutogarasu.mcrpc.DiscordID;
import com.Hashibutogarasu.mcrpc.MCRPCMod;
import com.Hashibutogarasu.mcrpc.Utils.RunRPC;
import com.Hashibutogarasu.mcrpc.gui.Screen.KeyConfigScreen;
import com.Hashibutogarasu.mcrpc.rpc.RPCMain;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class ClothConfigScreenFactory {

    public static AtomicReference<String> currentValue = new AtomicReference<>();
    public static RPCMain rpcmain = new RPCMain(currentValue.get(), DiscordID.ApplicationID);

    public ClothConfigScreenFactory(){

    }
    public static Screen genConfig(Screen parent){

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(new KeyConfigScreen(new TranslatableText("gui.mcdiscordrpc.title")))
                .setTitle(new TranslatableText("gui.mcdiscordrpc.title"));
        builder.setSavingRunnable(() -> {
            try {
                File file = new File("discord-rpc-application.txt");
                FileWriter filewriter = new FileWriter(file);

                filewriter.write(currentValue.get());
                filewriter.close();

                DiscordID.ApplicationID = currentValue.get();
//                rpcmain = new RPCMain(DiscordID.ApplicationID,"Logined");
                RunRPC.login("Logined");

                MCRPCMod.LOGGER.info("Loggined as" + DiscordID.ApplicationID);

            } catch (IOException ignored) {

            }
        });

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.mcdiscordrpc.general"));
        builder.setParentScreen(parent);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        currentValue = new AtomicReference<>(DiscordID.ApplicationID);

        general.addEntry(entryBuilder.startStrField(new TranslatableText("option.mcdiscordrpc.discordapplicationid"), currentValue.get())
                .setDefaultValue(DiscordID.ApplicationID) // Recommended: Used when user click "Reset"
                .setSaveConsumer(newValue -> currentValue.set(newValue)) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        return builder.build();
    }
}
