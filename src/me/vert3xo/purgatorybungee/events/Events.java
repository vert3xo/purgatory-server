package me.vert3xo.purgatorybungee.events;

import me.vert3xo.purgatorybungee.PurgatoryBungee;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {

    @EventHandler
    public void checkIfBannned(ServerConnectedEvent e) {
        PurgatoryBungee plugin = PurgatoryBungee.getInstance();
        if (plugin.bans.configuration.contains(e.getPlayer().getUniqueId().toString())) {
            String purgatory = plugin.configuration.getString("purgatory-server");
            String welcomeMsg = plugin.configuration.getString("welcome-msg");
            String failedToConnect = plugin.configuration.getString("connection-failed");
            Callback callback = new Callback() {
                @Override
                public void done(Object o, Throwable throwable) {
                    if (throwable != null) {
                        e.getPlayer().disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&', failedToConnect)));
                    } else {
                        e.getPlayer().connect(plugin.getProxy().getServerInfo(purgatory));
                        e.getPlayer().sendMessage(new TextComponent(ChatColor.BOLD.toString() + ChatColor.WHITE + "[" + ChatColor.GOLD.toString() + "INFO" + ChatColor.WHITE.toString() + "] " + ChatColor.RESET.toString() + ChatColor.translateAlternateColorCodes('&', welcomeMsg)));
                    }
                }
            };
            plugin.getProxy().getServerInfo(purgatory).ping(callback);
        }
    }

}
