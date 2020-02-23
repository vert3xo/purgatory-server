package me.vert3xo.purgatorybungee.commands;

import me.vert3xo.purgatorybungee.PurgatoryBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalKick extends Command {

    public GlobalKick() {
        super("kick", "purgatory.kick");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        boolean playerFound = false;
        if (PurgatoryBungee.getInstance().getProxy().getPlayers().isEmpty()) return;
        if (args.length > 1) {
            for (ProxiedPlayer player : PurgatoryBungee.getInstance().getProxy().getPlayers()) {
                if (args[0].equalsIgnoreCase(player.getDisplayName())) {
                    args[0] = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String reason : args) {
                        stringBuilder.append(reason).append(" ");
                    }
                    player.disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&', stringBuilder.toString())));
                    playerFound = true;
                    break;
                }
            }
            if (!playerFound) {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "Player not found!"));
            }
            return;
        } else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/kick <player> [<reason>]"));
            return;
        }
    }

}
