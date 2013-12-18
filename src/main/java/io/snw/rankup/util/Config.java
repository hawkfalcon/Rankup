package io.snw.rankup.util;

import io.snw.rankup.Rankup;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class Config {

    public static boolean override;
    public static YamlConfiguration config;

    public static HashMap<String, Group> allGroups = new HashMap<String, Group>();


    public static void loadConfig(YamlConfiguration config) {
        Config.config = config;

        Map<String, Object> allGroups = new HashMap<String, Object>();

        if (!config.getKeys(false).isEmpty()) {

            allGroups = (Map<String, Object>) config.getConfigurationSection("ranks").getValues(false);

            if (allGroups != null) {

                Iterator<String> groupItr = allGroups.keySet().iterator();

                String groupName;
                Integer groupCount = 0;

                while (groupItr.hasNext()) {

                    try {
                        groupCount++;
                        groupName = groupItr.next();

                        double price = config.getDouble("ranks." + groupName + ".price");
                        boolean broadcast = false;
                        if (config.contains("ranks." + groupName + ".broadcast")) {
                            broadcast = config.getBoolean("ranks." + groupName + ".broadcast");
                        }

                        double pay = 0.0;
                        if (config.contains("ranks." + groupName + ".pay")) {
                            pay = config.getDouble("ranks." + groupName + ".pay");
                        }

                        Group g = new Group(groupName, price, broadcast, pay);
                        Config.allGroups.put(groupName.toLowerCase(), g);
                    } catch (Exception ex) {
                        throw new IllegalArgumentException("Invalid group name found, check your configuration file.");
                    }

                }

            }
        }

        override = config.getBoolean("settings.override-groups");

    }

    public static ArrayList<String> getAvailableGroups() {
        return new ArrayList<String>(allGroups.keySet());
    }

    public static Group getGroup(String name) {
        return allGroups.get(name);
    }

    public static ArrayList<String> getCurrentGroups(Player player) {
        return new ArrayList<String>(Arrays.asList(Rankup.perms.getPlayerGroups(player)));
    }

    public static boolean getOverride() {
        return override;
    }

    public static String getCurrentRankableGroup(Player player) {
        ArrayList<String> availableGroups = getAvailableGroups();
        ArrayList<String> currentGroups = getCurrentGroups(player);

        if (availableGroups.size() == 0 || availableGroups == null) {
            Language.send(player, "&cThere are no available groups to rank up to.");
            return null;
        }

        if (currentGroups.size() == 0 || currentGroups == null) {
            Language.send(player, "&cYou are not in a group that can rank up.");
            return null;
        }

        int count = 0;

        for (String s : availableGroups) {

            if (count == availableGroups.size()) {
                Language.send(player, "&cYou are not in a group that can rank up.");
            } else if (currentGroups.contains(s)) {
                return availableGroups.get(count + 1);
            }

            count++;
        }

        return null;

    }


    public static String getRankToGroup(Player player) {
        ArrayList<String> availableGroups = getAvailableGroups();
        ArrayList<String> currentGroups = getCurrentGroups(player);

        if (availableGroups.size() == 0 || availableGroups == null) {
            Language.send(player, "notRankable");
            return null;
        }

        if (currentGroups.size() == 0 || currentGroups == null) {
            Language.send(player, "notRankable");
            return null;
        }

        int count = 0;


        for (String s : currentGroups) {
            int index = availableGroups.indexOf(s) + 1;
            if (count == availableGroups.size() - 1 || !availableGroups.contains(s) || index == availableGroups.size()) {
                Language.send(player, "notRankable");
                return null;
            } else if (availableGroups.get(index) != null) {
                if (!currentGroups.contains(availableGroups.get(index))) {
                    return availableGroups.get(index);
                }
            }
            count++;
        }

        return null;

    }
}
