package com.leclowndu93150.sinkfaster.neoforge;

import com.leclowndu93150.sinkfaster.SinkFasterConfig;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

@Mod("sinkfaster")
public class SinkfasterNeoForge {
    public SinkfasterNeoForge() {
        SinkFasterConfig.load(FMLPaths.CONFIGDIR.get());
    }
}
