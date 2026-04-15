package com.leclowndu93150.sinkfaster;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SinkFasterConfig {

    public boolean enabled = true;
    public double sinkSpeedMultiplier = 8.0;
    public boolean onlyWhenSneaking = false;
    public boolean onlyPlayers = true;

    private static SinkFasterConfig INSTANCE;

    public static SinkFasterConfig get() {
        return INSTANCE != null ? INSTANCE : new SinkFasterConfig();
    }

    public static void load(Path configDir) {
        Path file = configDir.resolve("sinkfaster.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        SinkFasterConfig config = new SinkFasterConfig();
        try {
            if (Files.exists(file)) {
                String json = Files.readString(file);
                SinkFasterConfig loaded = gson.fromJson(json, SinkFasterConfig.class);
                if (loaded != null) config = loaded;
            }
            config.clamp();
            Files.createDirectories(configDir);
            Files.writeString(file, gson.toJson(config));
        } catch (IOException ignored) {
        }
        INSTANCE = config;
    }

    private void clamp() {
        if (sinkSpeedMultiplier < 0.1) sinkSpeedMultiplier = 0.1;
        if (sinkSpeedMultiplier > 50.0) sinkSpeedMultiplier = 50.0;
    }
}
