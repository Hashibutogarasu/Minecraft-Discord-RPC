package com.Hashibutogarasu.mcrpc;

import com.Hashibutogarasu.mcrpc.Config.ClothConfigScreenFactory;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            return ClothConfigScreenFactory::genConfig;
        } else {
            return (parent) -> null;
        }
    }
}
