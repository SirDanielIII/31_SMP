package com.sirdanieliii.commands;

import com.sirdanieliii.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sirdanieliii.events.Utilities.toTitleCase;

public class CommandManager implements CommandExecutor {

    static Map<String, List<SubCommand>> subcommands = new HashMap<String, List<SubCommand>>();
    List<SubCommand> ivan = new ArrayList<>();
    List<SubCommand> coords = new ArrayList<>();
    List<SubCommand> death = new ArrayList<>();

    public CommandManager() {
        ivan.add(new ivanDog());
        ivan.add(new ivanDonkey());
        coords.add(new coordsSet());
        coords.add(new coordsList());
        coords.add(new coordsClear());
        coords.add(new coordsSend());
        death.add(new deathMurders());
        death.add(new deathPVP());
        death.add(new deathNonPVP());
        death.add(new deathTotal());
        death.add(new deathKDR());
        subcommands.put("ivan", ivan);
        subcommands.put("coords", coords);
        subcommands.put("death", death);
    }

    public static ArrayList<SubCommand> getSubcommands(String key) {
        return (ArrayList<SubCommand>) subcommands.get(key);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player p) {
            boolean present = false;
            if (args.length > 0) {
                try {
                    for (int i = 0; i < getSubcommands(cmd.getName()).size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubcommands(cmd.getName()).get(i).getName())) {
                            getSubcommands(cmd.getName()).get(i).perform(p, args);
                            present = true;
                        }
                    }
                } catch (Exception ignored) {
                }
                if (!present) incorrectFirstArg(p, label, args);

            } else {
                displayCommands(p, label);
            }
        }
        return true;
    }

    public void incorrectFirstArg(Player player, String cmd, String args[]) {
        if (cmd.equalsIgnoreCase("ivan")) {
            player.sendMessage("§C[!] §F<type> must be §E\"donkey\" §For §E\"dog\"");
        } else if (cmd.equalsIgnoreCase("coords")) {
            if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage("§C[!] List Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
            } else if (args[0].equalsIgnoreCase("clear")) {
                player.sendMessage("§C[!] Clear Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
            } else if (args[0].equalsIgnoreCase("set")) {
                player.sendMessage("§C[!] Set Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether");
            } else if (args[0].equalsIgnoreCase("send")) {
                player.sendMessage("§C[!] Send Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
            } else {
                player.sendMessage("§C[!] Action: §Eset §F/ §Elist §F/ §Eclear §F/ §Esend");
            }
        } else if (cmd.equalsIgnoreCase("death")) {
            player.sendMessage("§C[!] Statistics: §Eplayer §F/ §Enonplayer §F/ §Emurders §F/ §Etotal §F/ §Ekdr");
        }
    }

    public static void displayCommands(Player player, String cmd) {
        player.sendMessage("--------------§6§L" + toTitleCase(cmd) + "§R§F----------------------");
        for (SubCommand i : subcommands.get(cmd)) {
            player.sendMessage(i.getSyntax() + " §7→ " + i.getDescription());
        }
        player.sendMessage("------------------------------------------");
    }

    public static void displayAllCommands(Player player) {
        player.sendMessage("--------------§6§L" + "31 SMP Commands" + "§R§F----------------------");
        for (List<SubCommand> i : subcommands.values()) {
            for (SubCommand subCommand : i) {
                player.sendMessage(subCommand.getSyntax() + " §7→ " + subCommand.getDescription());
            }
        }
        player.sendMessage(Wand.getSyntax() + " §7→ " + Wand.getDescription());
        player.sendMessage("----------------------------------------------------");
    }
}
