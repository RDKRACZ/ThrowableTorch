package com.daniking.throwabletorch;

import com.daniking.throwabletorch.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;

public class ThrowableTorch implements ModInitializer {


    public static final String MODID = "throwabletorch";

    @Override
    public void onInitialize() {
        ModRegistry.registerItem();

    }

}


