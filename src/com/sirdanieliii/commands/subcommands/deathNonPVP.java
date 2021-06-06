package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.entity.Player;

import static com.sirdanieliii.configuration.PlayerData.nonPVPDeaths;


public class deathNonPVP extends SubCommand {
    @Override
    public String getName() {
        return "nonplayer";
    }

    @Override
    public String getDescription() {
        return "Shows death count, excluding PVP";
    }

    @Override
    public String getSyntax() {
        return "/death nonplayer";
    }

    @Override
    public void perform(Player player, String[] args) {
        nonPVPDeaths(player);
    }
}
