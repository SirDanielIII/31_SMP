package com.sirdanieliii;

import com.sirdanieliii.commands.Coords;
import com.sirdanieliii.commands.Death;
import com.sirdanieliii.commands.tabcompletion.CoordsTab;
import com.sirdanieliii.commands.Ivan;
import com.sirdanieliii.commands.Wand;
import com.sirdanieliii.commands.tabcompletion.DeathTab;
import com.sirdanieliii.commands.tabcompletion.IvanTab;
import com.sirdanieliii.configuration.ConfigManager;
import com.sirdanieliii.events.Events;
import com.sirdanieliii.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;


public class SMP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Custom Config File
        ConfigManager.setup();
        ConfigManager.save();
        // Initialize Events
        getServer().getPluginManager().registerEvents(new Events(), this);
        // Create Custom Items
        ItemManager.init();
        // Custom Commands
        getCommand("wand").setExecutor(new Wand());
        getCommand("ivan").setExecutor(new Ivan());
        getCommand("coords").setExecutor(new Coords());
        getCommand("death").setExecutor(new Death());
        // Tab Completion
        getCommand("ivan").setTabCompleter(new IvanTab());
        getCommand("coords").setTabCompleter(new CoordsTab());
        getCommand("death").setTabCompleter(new DeathTab());
    }

    @Override
    public void onDisable() {
    }
}
