package com.sirdanieliii.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack wand;

    public static void init() {
        createWand();
    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        // Set Name
        meta.setDisplayName("§6§LWand");
        // Create Description
        List<String> lore = new ArrayList<>();
        lore.add("§EThey who hold this wand ");
        lore.add("§Ecannot comprehend its full power...");
        meta.setLore(lore);  // Set Description to Item
        // Enchantments
        meta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 10, true);
        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        // Set Attributes to Item
        item.setItemMeta(meta);
        // Create Wand
        wand = item;

        for (byte i = 0; i < 3; i++) {
            System.out.print("C");
        }
    }
}
