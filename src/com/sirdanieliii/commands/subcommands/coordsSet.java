package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import com.sirdanieliii.configuration.ConfigManager;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

import static com.sirdanieliii.configuration.PlayerData.savePlayerCoords;

public class coordsSet extends SubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Saves a coordinate in a specific slot";
    }

    @Override
    public String getSyntax() {
        return "/coords set <type>";
    }

    @Override
    public void perform(Player player, String[] args) {
        String type = args[1].toLowerCase();
        if (!type.equalsIgnoreCase("all") && Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
            if (!(player.getWorld().getEnvironment() == World.Environment.NETHER)) {
                savePlayerCoords(player, type);
                ConfigManager.save();
            } else {
                player.sendMessage("§C[!] §EHey you're not in the Overworld!");
            }
        } else if (Stream.of("portal", "nether").anyMatch(type::equalsIgnoreCase)) {
            if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                savePlayerCoords(player, type);
                ConfigManager.save();
            } else {
                player.sendMessage("§C[!] §EHey you're not in the Nether!");
            }
        } else {
            player.sendMessage("§C[!] Set Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether");
        }
    }
}
