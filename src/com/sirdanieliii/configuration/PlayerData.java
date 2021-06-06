package com.sirdanieliii.configuration;

import org.bukkit.entity.Player;

import java.util.stream.Stream;

import static com.sirdanieliii.events.Utilities.randomMessage;
import static com.sirdanieliii.events.Utilities.toTitleCase;

public class PlayerData {

    public static void createPlayerSections(Player player) {
        // First Time Player Data Section Generation
        if (!ConfigManager.getConfig().contains(player.getUniqueId().toString())) {
            ConfigManager.getConfig().createSection(player.getUniqueId().toString());
            ConfigManager.getConfig().createSection(player.getUniqueId().toString() + ".name");
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
        // Update Player Name
        if (!player.getName().equalsIgnoreCase(String.valueOf(ConfigManager.getConfig().contains(player.getUniqueId().toString() + ".name")))) {
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "name", player.getName());
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

    public static void murders(Player player) {
        double kills = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "murders");
        if (kills == 0.0) {
            player.sendMessage("§4[§FDeath§4] §7You've never been killed anybody before :O");
        } else {
            ConfigManager.reload();
            if (kills == 1.0) {
                player.sendMessage("§4[§FDeath§4] §FYou have " + randomMessage("kill", player) + " §A" + (int) kills + " person!");
            } else {
                player.sendMessage("§4[§FDeath§4] §FYou have " + randomMessage("kill", player) + " §A" + (int) kills + " people!");
            }
        }
    }

    public static void pvpDeaths(Player player) {
        double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        if (deaths == 0) {
            player.sendMessage("§4[§FDeath§4] §AYou've never been killed by a player before :O");
        } else {
            ConfigManager.reload();
            if (deaths == 1) {
                player.sendMessage("§4[§FDeath§4] §FYou have been " + randomMessage("kill", player) + " §Conce!");
            } else {
                player.sendMessage("§4[§FDeath§4] §FYou have been " + randomMessage("kill", player) + " §C" + (int) deaths + " times!");
            }
        }
    }

    public static void nonPVPDeaths(Player player) {
        double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
        if (deaths == 0) {
            player.sendMessage("§4[§FDeath§4] §AYou've never died to " + randomMessage("death", player) + " before!");
        } else {
            ConfigManager.reload();
            if (deaths == 1) {
                player.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + "only once!" + "§F due to " + randomMessage("death", player));
            } else {
                player.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + (int) deaths + " times §Fdue to " + randomMessage("death", player));
            }
        }
    }

    public static void totalDeaths(Player player) {
        double playerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        double nonPlayerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
        double total = playerDeaths + nonPlayerDeaths;
        if (total == 0.0) {
            player.sendMessage("§4[§FDeath§4] §AYou've never died before!)");
        } else if (total == 1.0) {
            player.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + "only once §Fin total!");
        } else {
            player.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + (int) total + " times §Fin total! Imagine.");
        }
    }

    public static void kdr(Player player) {
        double kills = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "murders");
        double playerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        if (playerDeaths == 0) player.sendMessage("§4[§FDeath§4] §7You need to have died to a player at least ONCE");
        else {
            double kd = kills / playerDeaths;
            if (kd < 0.5) {
                player.sendMessage("§4[§FDeath§4] §FYour K/D ratio is §C" + String.format("%.2f", kd));
            } else if (kd > 0.5 && kd < 1.0) {
                player.sendMessage("§4[§FDeath§4] §FYour K/D ratio is §E" + String.format("%.2f", kd));
            } else if (kd >= 1.0) {
                player.sendMessage("§4[§FDeath§4] §FYour K/D ratio is §A" + String.format("%.2f", kd));
            }
        }
    }
}
