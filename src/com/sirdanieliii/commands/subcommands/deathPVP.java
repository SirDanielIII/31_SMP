package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.entity.Player;

import static com.sirdanieliii.configuration.PlayerData.pvpDeaths;

public class deathPVP extends SubCommand {
    @Override
    public String getName() {
        return "player";
    }

    @Override
    public String getDescription() {
        return "§7Shows death count from PVP";
    }

    @Override
    public String getSyntax() {
        return "§4/death player";
    }

    @Override
    public void perform(Player player, String[] args) {
        pvpDeaths(player);
    }
}
