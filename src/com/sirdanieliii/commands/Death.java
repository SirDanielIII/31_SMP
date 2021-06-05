package com.sirdanieliii.commands;

import com.sirdanieliii.configuration.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.sirdanieliii.events.Utilities.randomMessage;

public class Death implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§C[!] §ESorry, but only players can use this command");
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("death")) {
            if (args.length == 0) {
                sender.sendMessage("§C[!] Missing 1 argument: §E/death <stat>");
                sender.sendMessage("§F>>> Stats: §Eplayer §F/ §Enonplayer §F/ §Eplayerkills §F/ §Etotal §F/ §Ekdr");
            } else if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case ("murders") -> murders(player, sender);
                    case ("player") -> playerDeaths(player, sender);
                    case ("nonplayer") -> nonPlayerDeaths(player, sender);
                    case ("total") -> totalDeaths(player, sender);
                    case ("kdr") -> kdr(player, sender);
                    default -> sender.sendMessage("§C[!] Invalid Stat: §Eplayer §F/ §Enonplayer §F/ §Emurders §F/ §Etotal §F/ §Ekdr");
                }
            } else {
                sender.sendMessage("§C[!] Invalid arguments: §F/death <stat>");
            }
        }
        return true;
    }

    public static void murders(Player player, CommandSender sender) {
        double kills = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "murders");
        if (kills == 0.0) {
            sender.sendMessage("§4[§FDeath§4] §7You've never been killed anybody before :O");
        } else {
            ConfigManager.reload();
            if (kills == 1.0) {
                sender.sendMessage("§4[§FDeath§4] §FYou have " + randomMessage("kill", player) + " §A" + (int) kills + " person!");
            } else {
                sender.sendMessage("§4[§FDeath§4] §FYou have " + randomMessage("kill", player) + " §A" + (int) kills + " people!");
            }
        }
    }

    public static void playerDeaths(Player player, CommandSender sender) {
        double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        if (deaths == 0) {
            sender.sendMessage("§4[§FDeath§4] §AYou've never been killed by a player before :O");
        } else {
            ConfigManager.reload();
            if (deaths == 1) {
                sender.sendMessage("§4[§FDeath§4] §FYou have been " + randomMessage("kill", player) + " §Conce!");
            } else {
                sender.sendMessage("§4[§FDeath§4] §FYou have been " + randomMessage("kill", player) + " §C" + (int) deaths + " times!");
            }
        }
    }

    public static void nonPlayerDeaths(Player player, CommandSender sender) {
        double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
        if (deaths == 0) {
            sender.sendMessage("§4[§FDeath§4] §AYou've never died to " + randomMessage("death", player) + " before!");
        } else {
            ConfigManager.reload();
            if (deaths == 1) {
                sender.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + "only once!" + "§F due to " + randomMessage("death", player));
            } else {
                sender.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + (int) deaths + " times §Fdue to " + randomMessage("death", player));
            }
        }
    }

    public static void totalDeaths(Player player, CommandSender sender) {
        double playerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        double nonPlayerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
        double total = playerDeaths + nonPlayerDeaths;
        if (total == 0.0) {
            sender.sendMessage("§4[§FDeath§4] §AYou've never died before!)");
        } else if (total == 1.0) {
            sender.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + "only once §Fin total!");
        } else {
            sender.sendMessage("§4[§FDeath§4] §FYou have died " + "§C" + (int) total + " times §Fin total! Imagine.");
        }
    }

    public static void kdr(Player player, CommandSender sender) {
        double kills = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "murders");
        double playerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        if (playerDeaths == 0) sender.sendMessage("§4[§FDeath§4] §7You need to have died to a player at least ONCE");
        else {
            double kd = kills / playerDeaths;
            if (kd < 0.5) {
                sender.sendMessage("§4[§FDeath§4] §FYour K/D ratio is §C" + String.format("%.2f", kd));
            } else if (kd > 0.5 && kd < 1.0) {
                sender.sendMessage("§4[§FDeath§4] §FYour K/D ratio is §E" + String.format("%.2f", kd));
            } else if (kd >= 1.0) {
                sender.sendMessage("§4[§FDeath§4] §FYour K/D ratio is §A" + String.format("%.2f", kd));
            }
        }
    }
}