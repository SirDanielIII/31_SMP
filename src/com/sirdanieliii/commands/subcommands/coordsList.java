package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

import static com.sirdanieliii.configuration.PlayerData.retrievePlayerCoords;
import static com.sirdanieliii.events.Utilities.toTitleCase;

public class coordsList extends SubCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "§7Lists a saved coordinate";
    }

    @Override
    public String getSyntax() {
        return "§6/coords list <type>";
    }

    @Override
    public void perform(Player player, String[] args) {
        String type = args[1].toLowerCase();
        if (Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §A" + toTitleCase(type) + " " + retrievePlayerCoords(player, type, "§F", "§E", "§C"));
        } else if (Stream.of("nether").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §D" + toTitleCase(type) + " " + retrievePlayerCoords(player, type, "§F", "§E", "§C"));
        } else if (Stream.of("portal").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §D" + "Nether Portal " + retrievePlayerCoords(player, type, "§F", "§E", "§C"));
        } else if (Stream.of("all").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §A" + "Home " + retrievePlayerCoords(player, "home", "§F", "§E", "§C"));
            player.sendMessage("§6[§FCoords§6] §A" + "Overworld " + retrievePlayerCoords(player, "overworld", "§F", "§E", "§C"));
            player.sendMessage("§6[§FCoords§6] §D" + "Nether Portal " + retrievePlayerCoords(player, "portal", "§F", "§E", "§C"));
            player.sendMessage("§6[§FCoords§6] §D" + "Nether " + retrievePlayerCoords(player, "nether", "§F", "§E", "§C"));
        } else {
            player.sendMessage("§C[!] List Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
        }
    }
}
