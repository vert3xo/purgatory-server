package me.vert3xo.purgatorybungee;

import me.vert3xo.purgatorybungee.commands.GlobalBan;
import me.vert3xo.purgatorybungee.commands.GlobalKick;
import me.vert3xo.purgatorybungee.commands.PingCommand;
import me.vert3xo.purgatorybungee.configuration.Bans;
import me.vert3xo.purgatorybungee.events.Events;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class PurgatoryBungee extends Plugin {

    private static PurgatoryBungee instance;
    public Configuration configuration;
    public Bans bans;

    @Override
    public void onEnable() {
        instance = this;
        this.configuration = this.createConfig();
        this.registerEvents();
        this.registerCommands();
        bans = new Bans();
        this.getLogger().info("Loaded!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Unloaded!");
    }

    private void registerEvents() {
        this.getProxy().getPluginManager().registerListener(this, new Events());
        this.getLogger().info("Events registered!");
    }

    private void registerCommands() {
        this.getProxy().getPluginManager().registerCommand(this, new PingCommand());
        this.getProxy().getPluginManager().registerCommand(this, new GlobalKick());
        this.getProxy().getPluginManager().registerCommand(this, new GlobalBan());
        this.getLogger().info("Commands registered!");
    }

    private Configuration createConfig() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                this.getLogger().info(ChatColor.RED + "An error has occurred while creating config.yml!");
            }
        }
            try {
                Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                BufferedReader br = new BufferedReader(new FileReader(file));
                if (br.readLine() == null) {
                    configuration.set("purgatory-server", "purgatory");
                    configuration.set("welcome-msg", "&cWelcome to the Purgatory. Why are you here? You are here because you have been banned!");
                    configuration.set("connection-failed", "&cAn error occurred while connecting to the server");
                }
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
                return configuration;
            } catch (IOException e) {
                this.getLogger().info(ChatColor.RED + "An error has occurred while loading config.yml");
                return null;
            }
        }

    public static PurgatoryBungee getInstance() {
        return instance;
    }
}
