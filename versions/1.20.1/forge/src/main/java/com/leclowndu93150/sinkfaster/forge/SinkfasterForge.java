package com.leclowndu93150.sinkfaster.forge;

import com.leclowndu93150.sinkfaster.SinkFasterConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("sinkfaster")
public class SinkfasterForge {
    public SinkfasterForge() {
        SinkFasterConfig.load(FMLPaths.CONFIGDIR.get());
    }
}
