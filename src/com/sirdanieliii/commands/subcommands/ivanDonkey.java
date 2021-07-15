package com.sirdanieliii.commands.subcommands;

import com.sirdanieliii.commands.SubCommand;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static com.sirdanieliii.events.Utilities.offsetFromDirection;

public class ivanDonkey extends SubCommand {
    @Override
    public String getName() {
        return "donkey";
    }

    @Override
    public String getDescription() {
        return "§C[OP] §7Spawns a donkey named \"Ivan\"";
    }

    @Override
    public String getSyntax() {
        return "§B/ivan donkey";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.isOp()) {
            player.sendMessage("§C[!] Sorry, but only operators can use this command");
        } else {
            Vector offset = offsetFromDirection(player, 2.0D); // Calculates 2 block forward offset
            Donkey donkey = (Donkey) player.getWorld().spawnEntity(player.getLocation().add(offset), EntityType.DONKEY);
            donkey.setTamed(true);
            donkey.setOwner(player);
            donkey.setCustomName("Ivan");
            donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            donkey.setCarryingChest(true);
            donkey.setJumpStrength(0.75F);
            player.playSound(player.getLocation(), Sound.ENTITY_DONKEY_AMBIENT, 1, 1);
            player.sendMessage("§B[§FIvan§B] §FYou have spawned a stoopid Ivan!");
        }
    }
}
