package com.rodiconmc.rodisongsaver;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        File file = this.getDataFolder();
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
        this.getCommand("record").setExecutor(new RecordCommand(this));
    }

}
