package com.sirdanieliii.commands.tabcompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CoordsTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            List<String> actions = new ArrayList<>();
            actions.add("set");
            actions.add("list");
            actions.add("clear");
            return actions;
        } else if (args.length == 2) {
            List<String> types = new ArrayList<>();
            types.add("home");
            types.add("overworld");
            types.add("portal");
            types.add("nether");
            types.add("all");
            return types;
        }
        return null;
    }
}
