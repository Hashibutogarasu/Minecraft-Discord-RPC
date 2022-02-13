package com.Hashibutogarasu.mcrpc.Utils;

import com.Hashibutogarasu.mcrpc.Config.ClothConfigScreenFactory;
import com.Hashibutogarasu.mcrpc.DiscordID;
import com.Hashibutogarasu.mcrpc.rpc.RPCMain;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunRPC {
    public static void login(String Status){
        try {
            Path file = Paths.get("discord-rpc-application.txt");
            DiscordID.ApplicationID = Files.readString(file);

            if(DiscordID.ApplicationID == null){
                DiscordID.ApplicationID = "";
            }

            ClothConfigScreenFactory.rpcmain = new RPCMain(DiscordID.ApplicationID,Status);

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}
