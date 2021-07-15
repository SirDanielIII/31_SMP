package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.util.Vector;

import static com.sirdanieliii.events.Utilities.offsetFromDirection;

public class ivanDog extends SubCommand {
    @Override
    public String getName() {
        return "dog";
    }

    @Override
    public String getDescription() {
        return "§C[OP] §7Spawns a dog named \"Ivan\"";
    }

    @Override
    public String getSyntax() {
        return "§B/ivan dog";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.isOp()) {
            player.sendMessage("§C[!] Sorry, but only operators can use this command");
        } else {
            Vector offset = offsetFromDirection(player, 2.0D); // Calculates 2 block forward offset
            Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation().add(offset), EntityType.WOLF);
            wolf.setTamed(true);
            wolf.setOwner(player);
            wolf.setCustomName("Ivan");
            wolf.setCollarColor(DyeColor.LIGHT_BLUE);
            wolf.setSitting(true);
            player.playSound(player.getLocation(), Sound.ENTITY_WOLF_PANT, 1, 1);
            player.sendMessage("§B[§FIvan§B] §FYou have spawned a little Ivan!");
        }
    }
}

