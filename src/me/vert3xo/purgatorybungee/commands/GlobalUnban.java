package me.vert3xo.purgatorybungee.commands;

import me.vert3xo.purgatorybungee.PurgatoryBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalUnban extends Command {

    public GlobalUnban() {
        super("unban", "purgatory.unban");
    }

    private PurgatoryBungee plugin = PurgatoryBungee.getInstance();

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (this.plugin.playerList.contains(args[0])) {
            String uuid = this.plugin.playerList.getString(args[0] + ".uuid");
            this.plugin.bans.configuration.set(uuid, null);
            this.plugin.bans.saveConfig();
            this.plugin.getProxy().broadcast(new TextComponent(ChatColor.RED + args[0] + ChatColor.GREEN + " has been unbanned!"));
        }
    }

}
