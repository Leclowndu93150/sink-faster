package com.leclowndu93150.sinkfaster.fabric;

import com.leclowndu93150.sinkfaster.SinkFasterConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class SinkfasterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SinkFasterConfig.load(FabricLoader.getInstance().getConfigDir());
    }
}
