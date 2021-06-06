package com.sirdanieliii;

import com.sirdanieliii.commands.*;
import com.sirdanieliii.commands.tabcompletion.CoordsTab;
import com.sirdanieliii.commands.tabcompletion.DeathTab;
import com.sirdanieliii.commands.tabcompletion.IvanTab;
import com.sirdanieliii.configuration.ConfigManager;
import com.sirdanieliii.events.Events;
import org.bukkit.plugin.java.JavaPlugin;


public class SMP extends JavaPlugin {
    @Override
    public void onEnable() {
        // Custom Config File
        ConfigManager.setup();
        ConfigManager.save();
        // Initialize Events
        getServer().getPluginManager().registerEvents(new Events(), this);
        // Custom Commands
        getCommand("ivan").setExecutor(new CommandManager());
        getCommand("coords").setExecutor(new CommandManager());
        getCommand("death").setExecutor(new CommandManager());
        // Tab Completion
        getCommand("ivan").setTabCompleter(new IvanTab());
        getCommand("coords").setTabCompleter(new CoordsTab());
        getCommand("death").setTabCompleter(new DeathTab());
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Cancelling tasks...");
        this.getServer().getScheduler().cancelTasks(this);
    }
}
