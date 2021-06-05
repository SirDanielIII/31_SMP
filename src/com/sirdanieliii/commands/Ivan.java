package com.sirdanieliii.commands;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static com.sirdanieliii.events.Utilities.offsetFromDirection;

public class Ivan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§C[!] Sorry, but only OP'ed players can use that command");
            return true;
        }
        Player player = (Player) sender;
        // Checks for OP (Breaks if isn't)
        if (!(player.isOp())) {
            sender.sendMessage("§C[!] Sorry, but only operators can use this command");
            return true;
        }
        // Spawn Ivan
        if (cmd.getName().equalsIgnoreCase("ivan")) {
            if (args.length == 1) {
                try {
                    Vector offset = offsetFromDirection(player, 2.0D); // Calculates 2 block forward offset
                    switch (args[0].toLowerCase()) {
                        case ("donkey") -> {
                            Donkey donkey = (Donkey) player.getWorld().spawnEntity(player.getLocation().add(offset), EntityType.DONKEY);
                            donkey.setTamed(true);
                            donkey.setOwner(player);
                            donkey.setCustomName("Ivan");
                            donkey.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                            donkey.setCarryingChest(true);
                            donkey.setJumpStrength(0.75F);
                            player.playSound(player.getLocation(), Sound.ENTITY_DONKEY_AMBIENT, 1, 3);
                        }
                        case ("dog") -> {
                            Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation().add(offset), EntityType.WOLF);
                            wolf.setTamed(true);
                            wolf.setOwner(player);
                            wolf.setCustomName("Ivan");
                            wolf.setCollarColor(DyeColor.LIGHT_BLUE);
                            wolf.setSitting(true);
                            player.playSound(player.getLocation(), Sound.ENTITY_WOLF_PANT, 1, 1);
                        }
                        default -> sender.sendMessage("§E[!] §F<type> must be §E\"donkey\" §For §E\"dog\"");
                    }
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("§E[!] §F<type> must be §E\"donkey\" §For §E\"dog\"");
                }
            } else {
                sender.sendMessage("§C[!] §E/ivan <type>");
            }
        }
        return true;
    }
}
