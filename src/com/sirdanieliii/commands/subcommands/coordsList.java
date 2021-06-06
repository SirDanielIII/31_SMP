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
        return "Lists a saved coordinate";
    }

    @Override
    public String getSyntax() {
        return "/coords list <type>";
    }

    @Override
    public void perform(Player player, String[] args) {
        String type = args[1].toLowerCase();
        if (Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §A" + toTitleCase(type) + " " + retrievePlayerCoords(player, type));
        } else if (Stream.of("nether").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §D" + toTitleCase(type) + " " + retrievePlayerCoords(player, type));
        } else if (Stream.of("portal").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §D" + "Nether Portal " + retrievePlayerCoords(player, type));
        } else if (Stream.of("all").anyMatch(type::equalsIgnoreCase)) {
            player.sendMessage("§6[§FCoords§6] §A" + "Home " + retrievePlayerCoords(player, "home"));
            player.sendMessage("§6[§FCoords§6] §A" + "Overworld " + retrievePlayerCoords(player, "overworld"));
            player.sendMessage("§6[§FCoords§6] §D" + "Nether Portal " + retrievePlayerCoords(player, "portal"));
            player.sendMessage("§6[§FCoords§6] §D" + "Nether " + retrievePlayerCoords(player, "nether"));
        } else {
            player.sendMessage("§C[!] List Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
        }
    }
}
