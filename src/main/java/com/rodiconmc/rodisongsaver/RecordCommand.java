package com.rodiconmc.rodisongsaver;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class RecordCommand implements TabExecutor {
    Plugin plugin;

    RecordCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] rawArgs) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can do this command");
            return true;
        }
        Player player = (Player) sender;
        String stringArgs = String.join(" ", rawArgs);
        String[] args = getArgs(stringArgs);

        if (args.length != 2) {
            player.sendMessage("Correct usage: /record <song name> <file name>");
            return true;
        }
        new Recording(args[0], args[1], player, plugin);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }

    private String[] getArgs(String string) {
        List<String> args = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            char testChar = string.toCharArray()[i];
            if (testChar == '"') {
                i++;
                StringBuilder builder = new StringBuilder();
                while (i + 1 < string.length()) {
                    char quotedChar = string.toCharArray()[i];
                    if (quotedChar == '"') {
                        i++;
                        break;
                    }
                    builder.append(quotedChar);
                    i++;
                }
                args.add(builder.toString());

            } else if (testChar == ' ') {
                i++;
            } else {
                StringBuilder builder = new StringBuilder();
                while (i< string.length()) {
                    char quotedChar = string.toCharArray()[i];
                    if (quotedChar == ' ') {
                        break;
                    }
                    builder.append(quotedChar);
                    i++;
                }
                args.add(builder.toString());

            }
        }
        return args.toArray(String[]::new);
    }
}
