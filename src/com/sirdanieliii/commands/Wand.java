package com.sirdanieliii.commands;

import com.sirdanieliii.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Wand implements CommandExecutor {
    public Wand() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§C[!] Sorry, but only OP'ed players can use this command");
            return true;
        } else {
            Player player = (Player)sender;
            if (!player.isOp()) {
                sender.sendMessage("§C[!] Sorry, but only operators can use this command");
                return true;
            } else {
                if (cmd.getName().equalsIgnoreCase("wand")) {
                    Bukkit.broadcastMessage("§6§OA wand of the gods has been summoned...");
                    player.getInventory().addItem(new ItemStack[]{ItemManager.wand});
                }

                return true;
            }
        }
    }
}
