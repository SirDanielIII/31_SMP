package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.entity.Player;

import static com.sirdanieliii.configuration.PlayerData.kdr;

public class deathKDR extends SubCommand {
    @Override
    public String getName() {
        return "kdr";
    }

    @Override
    public String getDescription() {
        return "§7Shows you your kill-death ratio";
    }

    @Override
    public String getSyntax() {
        return "§4/death kdr";
    }

    @Override
    public void perform(Player player, String[] args) {
        kdr(player);
    }
}
