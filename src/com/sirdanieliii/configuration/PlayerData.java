package com.sirdanieliii.configuration;

import org.bukkit.entity.Player;

import java.util.stream.Stream;

public class PlayerData {

    public static void createPlayerSections(Player player) {
        // First Time Player Data Section Generation
        if (!ConfigManager.getConfig().contains(player.getUniqueId().toString())) {
            ConfigManager.getConfig().createSection(player.getUniqueId().toString());
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".home");
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".overworld");
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".portal");
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".nether");
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".murders");
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".playerdeaths");
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".nonplayerdeath");
            // Set Default Values
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "murders", 0.0D);
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "playerdeaths", 0.0D);
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "nonplayerdeath", 0.0D);
        }
    }

    public static void savePlayerCoords(Player player, String type) {
        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." +
                type + ".X", Math.round(player.getLocation().getX()));
        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." +
                type + ".Y", Math.round(player.getLocation().getY()));
        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." +
                type + ".Z", Math.round(player.getLocation().getZ()));
        if (Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] " + "§A" + toTitleCase(type) + " §FCoordinate Saved to " +
                    "§6(" + Math.round(player.getLocation().getX()) + " " + Math.round(player.getLocation().getY()) + " " +
                    Math.round(player.getLocation().getZ()) + ")");
        } else if (Stream.of("nether").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] " + "§D" + toTitleCase(type) + " §FCoordinate Saved to " +
                    "§6(" + Math.round(player.getLocation().getX()) + " " + Math.round(player.getLocation().getY()) + " " +
                    Math.round(player.getLocation().getZ()) + ")");
        } else {
            player.sendMessage("§6[§FCoords§6] " + "§D" + "Nether Portal" + " §FCoordinate Saved to " +
                    "§6(" + Math.round(player.getLocation().getX()) + " " + Math.round(player.getLocation().getY()) + " " +
                    Math.round(player.getLocation().getZ()) + ")");
        }
    }

    public static String retrievePlayerCoords(Player player, String type) {
        ConfigManager.reload();
        String x = ConfigManager.getConfig().getString(
                player.getUniqueId().toString() + "." + type + ".X");
        String y = ConfigManager.getConfig().getString(
                player.getUniqueId().toString() + "." + type + ".Y");
        String z = ConfigManager.getConfig().getString(
                player.getUniqueId().toString() + "." + type + ".Z");
        if (x == null) {
            return "§Cis not set";
        } else {
            return "§Fis at §E" + x + " " + y + " " + z;
        }
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;
        for (char c : input.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }
}
