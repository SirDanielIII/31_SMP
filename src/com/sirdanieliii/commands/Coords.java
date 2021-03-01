package com.sirdanieliii.commands;

import com.sirdanieliii.configuration.ConfigManager;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static com.sirdanieliii.configuration.PlayerData.*;

public class Coords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§C[!] §ESorry, but only players can use this command");
            return true;
        }
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("coords")) {
            if (args.length == 0) {
                sender.sendMessage("§C[!] Missing 2 arguments: §E/coords <action> <type>");
                sender.sendMessage("§F>>> Action: §Eset §F/ §Elist §F/ §Eclear");
                sender.sendMessage("§F>>> Set: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether");
                sender.sendMessage("§F>>> List & Clear: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
            } else if (args.length == 1) {
                if (Stream.of("clear", "list").anyMatch(args[0]::equalsIgnoreCase)) {
                    sender.sendMessage("§F>>> Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
                } else if (Stream.of("set").anyMatch(args[0]::equalsIgnoreCase)) {
                    sender.sendMessage("§F>>> §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether");
                } else {
                    sender.sendMessage("§C[!] Invalid Action: §Eset §F/ §Elist §F/ §Eclear");
                }
            } else if (args.length == 2) {
                String type = args[1].toLowerCase();
                // Set Coords
                if (args[0].equalsIgnoreCase("set")) {
                    if (!type.equalsIgnoreCase("all") && Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
                        if (!(player.getWorld().getEnvironment() == World.Environment.NETHER)) {
                            savePlayerCoords(player, type);
                            ConfigManager.save();
                        } else {
                            sender.sendMessage("§C[!] §EHey you're not in the Overworld!");
                        }
                    } else if (Stream.of("portal", "nether").anyMatch(type::equalsIgnoreCase)) {
                        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                            savePlayerCoords(player, type);
                            ConfigManager.save();
                        } else {
                            sender.sendMessage("§C[!] §EHey you're not in the Nether!");
                        }
                    } else {
                        sender.sendMessage("§C[!] Set: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether");
                    }
                }
                // List Coords
                else if (args[0].equalsIgnoreCase("list")) {
                    if (Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
                        sender.sendMessage("§6[§FCoords§6] §A" + toTitleCase(type) + " " + retrievePlayerCoords(player, type));
                    } else if (Stream.of("nether").anyMatch(type::equalsIgnoreCase)) {
                        sender.sendMessage("§6[§FCoords§6] §D" + toTitleCase(type) + " " + retrievePlayerCoords(player, type));
                    } else if (Stream.of("portal").anyMatch(type::equalsIgnoreCase)) {
                        sender.sendMessage("§6[§FCoords§6] §D" + "Nether Portal " + retrievePlayerCoords(player, type));
                    } else if (Stream.of("all").anyMatch(type::equalsIgnoreCase)) {
                        sender.sendMessage("§6[§FCoords§6] §A" + "Home " + retrievePlayerCoords(player, "home"));
                        sender.sendMessage("§6[§FCoords§6] §A" + "Overworld " + retrievePlayerCoords(player, "overworld"));
                        sender.sendMessage("§6[§FCoords§6] §D" + "Nether Portal " + retrievePlayerCoords(player, "portal"));
                        sender.sendMessage("§6[§FCoords§6] §D" + "Nether " + retrievePlayerCoords(player, "nether"));
                    } else {
                        sender.sendMessage("§F[!] List: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
                    }
                }
                // Clear Coords
                else if (args[0].equalsIgnoreCase("clear")) {
                    if (Stream.of("home", "overworld", "portal", "nether").anyMatch(type::equalsIgnoreCase)) {
                        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + type, new Array[]{});
                        if (Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
                            sender.sendMessage("§6[§FCoords§6] §A" + toTitleCase(type) + " §Fcoordinate cleared successfully");
                        } else {
                            sender.sendMessage("§6[§FCoords§6] §D" + toTitleCase(type) + " §Fcoordinate cleared successfully");
                        }
                        ConfigManager.save();
                    } else if (type.equalsIgnoreCase("all")) {
                        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "home", new Array[]{});
                        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "overworld", new Array[]{});
                        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "portal", new Array[]{});
                        ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "nether", new Array[]{});
                        ConfigManager.save();
                        sender.sendMessage("§6[§FCoords§6] §B" + "All" + " §Fcoordinates cleared successfully");
                    } else {
                        sender.sendMessage("§C[!] Clear: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
                    }
                } else {
                    sender.sendMessage("§C[!] Action: §Eset §F/ §Elist §F/ §Eclear");
                }
            } else {  // Add /coords message, and arguments one
                sender.sendMessage("§C[!] Invalid arguments: §F/coords <action> <type>");
            }
        }
        return true;
    }
}
