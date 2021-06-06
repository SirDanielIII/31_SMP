package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.entity.Player;

import static com.sirdanieliii.configuration.PlayerData.murders;

public class deathMurders extends SubCommand {
    @Override
    public String getName() {
        return "murders";
    }

    @Override
    public String getDescription() {
        return "Shows your PVP kill statistics";
    }

    @Override
    public String getSyntax() {
        return "/death murders";
    }

    @Override
    public void perform(Player player, String[] args) {
        murders(player);
    }
}
