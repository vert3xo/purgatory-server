package me.vert3xo.purgatorybungee.commands;

import me.vert3xo.purgatorybungee.PurgatoryBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.conf.Configuration;

public class GlobalBan extends Command {

    public GlobalBan() {
        super("ban", "purgatory.ban");
    }

    private PurgatoryBungee plugin = PurgatoryBungee.getInstance();
    private Configuration configuration;


    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (this.plugin.getProxy().getPlayers().isEmpty()) return;
        boolean playerFound = false;
        if (args.length > 1) {
            for (ProxiedPlayer player : this.plugin.getProxy().getPlayers()) {
                if (player.getDisplayName().equalsIgnoreCase(args[0])) {
                    args[0] = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String reason : args) {
                        stringBuilder.append(reason).append(" ");
                    }
                    player.disconnect(new TextComponent(ChatColor.RED + "[BANNED]" + ChatColor.translateAlternateColorCodes('&', stringBuilder.toString())));
                    this.plugin.bans.configuration.set(player.getUniqueId().toString() + ".Username", player.getDisplayName());
                    this.plugin.bans.configuration.set(player.getUniqueId().toString() + ".Reason", stringBuilder.toString());
                    this.plugin.bans.saveConfig();
                    playerFound = true;
                    break;
                }
            }
            if (!playerFound) {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "Player not found!"));
            }
            return;
        } else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/ban <player> [<reason>]"));
            return;
        }
    }
}
