package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.entity.Player;

import static com.sirdanieliii.configuration.PlayerData.totalDeaths;

public class deathTotal extends SubCommand {
    @Override
    public String getName() {
        return "total";
    }

    @Override
    public String getDescription() {
        return "§7Shows your total death count";
    }

    @Override
    public String getSyntax() {
        return "§4/death total";
    }

    @Override
    public void perform(Player player, String[] args) {
        totalDeaths(player);
    }
}
