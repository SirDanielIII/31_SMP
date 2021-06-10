package com.sirdanieliii.commands;

import com.sirdanieliii.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.sirdanieliii.events.Utilities.toTitleCase;

public class CommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager() {
        subcommands.add(new ivanDog());
        subcommands.add(new ivanDonkey());
        subcommands.add(new coordsSet());
        subcommands.add(new coordsList());
        subcommands.add(new coordsClear());
        subcommands.add(new coordsSend());
        subcommands.add(new deathMurders());
        subcommands.add(new deathPVP());
        subcommands.add(new deathNonPVP());
        subcommands.add(new deathTotal());
        subcommands.add(new deathKDR());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            boolean present = false;
            if (args.length > 0) {
                try {
                    for (int i = 0; i < getSubcommands().size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                            getSubcommands().get(i).perform(p, args);
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

    public ArrayList<SubCommand> getSubcommands() {
        return subcommands;
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

    public void displayCommands(Player player, String cmd) { // I'm hardcoding screw this
        player.sendMessage("--------------§6§L" + toTitleCase(cmd) + "§R§F----------------------");
        if (cmd.equalsIgnoreCase("ivan")) {
            player.sendMessage(getSubcommands().get(0).getSyntax() + " - " + getSubcommands().get(0).getDescription());
            player.sendMessage(getSubcommands().get(1).getSyntax() + " - " + getSubcommands().get(1).getDescription());
        } else if (cmd.equalsIgnoreCase("coords")) {
            player.sendMessage(getSubcommands().get(2).getSyntax() + " - " + getSubcommands().get(2).getDescription());
            player.sendMessage(getSubcommands().get(3).getSyntax() + " - " + getSubcommands().get(3).getDescription());
            player.sendMessage(getSubcommands().get(4).getSyntax() + " - " + getSubcommands().get(4).getDescription());
            player.sendMessage(getSubcommands().get(5).getSyntax() + " - " + getSubcommands().get(5).getDescription());
        } else if (cmd.equalsIgnoreCase("death")) {
            player.sendMessage(getSubcommands().get(6).getSyntax() + " - " + getSubcommands().get(6).getDescription());
            player.sendMessage(getSubcommands().get(7).getSyntax() + " - " + getSubcommands().get(7).getDescription());
            player.sendMessage(getSubcommands().get(8).getSyntax() + " - " + getSubcommands().get(8).getDescription());
            player.sendMessage(getSubcommands().get(9).getSyntax() + " - " + getSubcommands().get(9).getDescription());
            player.sendMessage(getSubcommands().get(10).getSyntax() + " - " + getSubcommands().get(10).getDescription());
        }
        player.sendMessage("------------------------------------------");
    }
}
