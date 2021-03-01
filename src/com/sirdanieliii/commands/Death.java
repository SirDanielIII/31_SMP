package com.sirdanieliii.commands;

import com.sirdanieliii.configuration.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Death implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§C[!] §ESorry, but only players can use this command");
            return true;
        }
        Player player = (Player) sender;
        // Spawn Ivan
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
            sender.sendMessage("§4[§FDeath§4] §EYou've never been killed anybody before :O");
        } else {
            ConfigManager.reload();
            if (kills == 1.0) {
                sender.sendMessage("§4[§FDeath§4] §EYou have " + randomKillDescription() + " §A" + (int) kills + " person!");
            } else {
                sender.sendMessage("§4[§FDeath§4] §EYou have " + randomKillDescription() + " §A" + (int) kills + " people!");
            }
        }
    }

    public static void playerDeaths(Player player, CommandSender sender) {
        double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        if (deaths == 0) {
            sender.sendMessage("§4[§FDeath§4] §CYou've never been killed by a player before :O");
        } else {
            ConfigManager.reload();
            if (deaths == 1) {
                sender.sendMessage("§4[§FDeath§4] §EYou have been " + randomKillDescription() + " §C" + (int) deaths + " time!");
            } else {
                sender.sendMessage("§4[§FDeath§4] §EYou have been " + randomKillDescription() + " §C" + (int) deaths + " times!");
            }
        }
    }

    public static void nonPlayerDeaths(Player player, CommandSender sender) {
        double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
        if (deaths == 0) {
            sender.sendMessage("§4[§FDeath§4] §AYou've never died to " + randomDeathDescription() + " before!");
        } else {
            ConfigManager.reload();
            if (deaths == 1) {
                sender.sendMessage("§4[§FDeath§4] §EYou have died " + "§C" + "only once!" + "§F due to " + randomDeathDescription());
            } else {
                sender.sendMessage("§4[§FDeath§4] §EYou have died " + "§C" + (int) deaths + " times §Fdue to " + randomDeathDescription());
            }
        }
    }

    public static void totalDeaths(Player player, CommandSender sender) {
        double playerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
        double nonPlayerDeaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
        double total = playerDeaths + nonPlayerDeaths;
        if (total == 0.0) {
            sender.sendMessage("§4[§FDeath§4] §AYou've never died before! §F(Congratulations!)");
        } else if (total == 1.0) {
            sender.sendMessage("§4[§FDeath§4] §EYou have died " + "§C" + "only once §Fin total!");
        } else {
            sender.sendMessage("§4[§FDeath§4] §EYou have died " + "§C" + (int) total + " times §Fin total! Imagine.");
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

    public static String randomKillDescription() {
        // Random message
        String[] messages = {"brutally murdered", "slaughtered", "booty clapped", "massacred", "slayed"};
        Random r = new Random();
        int idx = r.nextInt(messages.length);
        return messages[idx];
    }

    public static String randomDeathDescription() {
        // Random message
        String[] messages = {"your own stupidity", "the de-evolution of the human species", "lack of skill", "incompetence",
                "not getting gooder"};
        Random r = new Random();
        int idx = r.nextInt(messages.length);
        return messages[idx];
    }
}