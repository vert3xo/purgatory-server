package me.vert3xo.purgatorybungee.configuration;

import me.vert3xo.purgatorybungee.PurgatoryBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Bans {

    public Bans() {
        this.createConfiguration();
    }

    private PurgatoryBungee plugin = PurgatoryBungee.getInstance();

    public File file;
    public Configuration configuration;

    private void createConfiguration() {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        file = new File(this.plugin.getDataFolder(), "banned.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, this.file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
