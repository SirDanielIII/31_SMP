package com.sirdanieliii.events;

import com.sirdanieliii.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import static com.sirdanieliii.configuration.PlayerData.createPlayerSections;
import static com.sirdanieliii.events.Utilities.randomMessage;

public class Events implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd("§6§L[" + "§FTEST SMP" + "§6§L]" + "§A BIG BONG CHING CHONG" +
                "\n§C§O>>> 31 SMP Coming Soon :D");
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
        event.setQuitMessage("§4" + player.getName() + " " + message);
        createPlayerSections(player);
        ConfigManager.save();
        player.sendTitle("Hello There", "§6Welcome to the 31 SMP", 20, 70, 20);
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
            double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "playerdeaths");
            deaths += 1.0D;
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "playerdeaths", deaths);
            // Add to kill count
            double kills = ConfigManager.getConfig().getDouble(killer.getUniqueId().toString() + "." + "murders");
            kills += 1.0D;
            ConfigManager.getConfig().set(killer.getUniqueId().toString() + "." + "murders", kills);
        } else {
            double deaths = ConfigManager.getConfig().getDouble(player.getUniqueId().toString() + "." + "nonplayerdeath");
            deaths += 1.0D;
            ConfigManager.getConfig().set(player.getUniqueId().toString() + "." + "nonplayerdeath", deaths);
        }
        ConfigManager.save();
    }
}