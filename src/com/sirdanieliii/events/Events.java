package com.sirdanieliii.events;

import com.sirdanieliii.configuration.ConfigManager;
import com.sirdanieliii.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;

import static com.sirdanieliii.configuration.PlayerData.createPlayerSections;
import static com.sirdanieliii.events.Utilities.randomMessage;

public class Events implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd("           §6§L31 SMP §F[§A1.17§F] §F[§CWHITELIST ONLY§F]" +
                "\n§E    ▷ With Proximity Chat & Bedrock Support ◁");
    }

    @EventHandler
    //Player Join Message
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String message = randomMessage("join", player);
        event.setJoinMessage("§E" + player.getName() + " is " + message + " :)");
        createPlayerSections(player);
        ConfigManager.save();
        player.sendTitle("Hello There", "§6Welcome to the 31 SMP", 20, 70, 20);
    }

    @EventHandler
    //Player Quit Message
    public static void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String message = randomMessage("quit", player);
        event.setQuitMessage("§C" + player.getName() + " " + message);
    }

    @EventHandler
    //Player Sleep Message
    public static void onPlayerSleep(final PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Player player = event.getPlayer();
            String message = randomMessage("sleep", player);
            Bukkit.broadcastMessage("§B" + player.getName() + " has " + message);
        }
    }

    @EventHandler
    public static void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        if (killer != null) {
            // Add to death count
            double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId() + "." + "playerdeaths");
            deaths += 1.0D;
            ConfigManager.getConfig().set(player.getUniqueId() + "." + "playerdeaths", deaths);
            // Add to kill count
            double kills = ConfigManager.getConfig().getDouble(killer.getUniqueId() + "." + "murders");
            kills += 1.0D;
            ConfigManager.getConfig().set(killer.getUniqueId() + "." + "murders", kills);
        } else {
            double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId() + "." + "nonplayerdeath");
            deaths += 1.0D;
            ConfigManager.getConfig().set(player.getUniqueId() + "." + "nonplayerdeath", deaths);
        }
        ConfigManager.save();
    }

    @EventHandler
    public static void powers(PlayerInteractEvent event) { // Wand Powers
        EquipmentSlot hand = event.getHand();
        Player player;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && Objects.equals(hand, EquipmentSlot.HAND) && event.getItem() != null &&
                Objects.equals(event.getItem().getItemMeta(), ItemManager.wand.getItemMeta())) {
            player = event.getPlayer();
            player.getWorld().strikeLightning((Objects.requireNonNull(event.getClickedBlock())).getLocation());
            player.getWorld().createExplosion((Objects.requireNonNull(event.getClickedBlock())).getLocation(), 3.5F);
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR && Objects.equals(((ItemStack) Objects.requireNonNull(event.getItem())).getItemMeta(),
                ItemManager.wand.getItemMeta())) {
            player = event.getPlayer();
            Fireball fire = player.getWorld().spawn(event.getPlayer().getLocation().add(new Vector(0.0D, 1.5D, 0.0D))
                    .add(player.getVelocity()), Fireball.class);
            fire.setFireTicks(0);
            fire.setShooter(player);
        }
    }

    @EventHandler
    public static void disableEndPortal(PlayerPortalEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 25, 25);
            }
        }
    }
}