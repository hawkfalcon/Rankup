package io.snw.rankup.bukkit;

import io.snw.rankup.Ranking;
import io.snw.rankup.util.FileManager;
import io.snw.rankup.util.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    FileManager FileManager = new FileManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("rankup")) {
            if (args.length == 0) {

                if (sender.hasPermission("rankup.rankup")) {
                    Ranking.rankup(player);
                    return true;
                } else {
                    Language.send(player, "&cYou do not have permission to rankup.");
                }

            } else if (args.length == 1) {

                if (args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("version")) {

                    if (player.hasPermission("rankup.reload")) {
                        Language.send(player, " &3&oRankup (" + Bukkit.getServer().getPluginManager().getPlugin("Rankup").getDescription().getVersion() + ")&7&o by FVZA is running.");
                    } else {
                        Language.send(player, "&cYou do not have permission for this command.");
                    }

                } else if (args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload")) {

                    if (player.hasPermission("rankup.reload")) {
                        FileManager.loadFiles();
                        Language.send(player, "&7Rankup has been reloaded.");
                    } else {
                        Language.send(player, "&cYou do not have permission for this command.");
                    }
                }
            }
        }

        return false;
    }

}
