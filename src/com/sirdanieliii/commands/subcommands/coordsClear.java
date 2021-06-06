package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import com.sirdanieliii.configuration.ConfigManager;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static com.sirdanieliii.events.Utilities.toTitleCase;

public class coordsClear extends SubCommand {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Clears a specific coordinate slot";
    }

    @Override
    public String getSyntax() {
        return "/coords clear <type>";
    }

    @Override
    public void perform(Player player, String[] args) {
        String type = args[1].toLowerCase();
        if (Stream.of("home", "overworld", "portal", "nether").anyMatch(type::equalsIgnoreCase)) {
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + type, new Array[]{});
            if (Stream.of("home", "overworld").anyMatch(type::equalsIgnoreCase)) {
                player.sendMessage("§6[§FCoords§6] §A" + toTitleCase(type) + " §Fcoordinate cleared successfully");
            } else {
                player.sendMessage("§6[§FCoords§6] §D" + toTitleCase(type) + " §Fcoordinate cleared successfully");
            }
            ConfigManager.save();
        } else if (type.equalsIgnoreCase("all")) {
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "home", new Array[]{});
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "overworld", new Array[]{});
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "portal", new Array[]{});
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "nether", new Array[]{});
            ConfigManager.save();
            player.sendMessage("§6[§FCoords§6] §B" + "All" + " §Fcoordinates cleared successfully");
        } else {
            player.sendMessage("§C[!] Clear Types: §Ahome §F/ §Aoverworld §F/ §Dportal §F/ §Dnether §F/ §Ball");
        }
    }
}
