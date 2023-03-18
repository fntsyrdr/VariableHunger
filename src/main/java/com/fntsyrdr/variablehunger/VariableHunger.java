package com.fntsyrdr.variablehunger;

import org.bukkit.plugin.java.JavaPlugin;
import com.fntsyrdr.variablehunger.listeners.HungerAdjustments;
import java.util.logging.Logger;

public final class VariableHunger extends JavaPlugin {

    public static Logger log;
    @Override
    public void onEnable() {
        log = getLogger();
        log.info("Plugin Enable Started");

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new HungerAdjustments(), this);



        log.info("Plugin Enable Finished");
    }

    @Override
    public void onDisable() {
        log.info("Plugin Disabled");
    }
}
