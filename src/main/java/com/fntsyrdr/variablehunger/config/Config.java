package com.fntsyrdr.variablehunger.config;

import com.fntsyrdr.variablehunger.config.IConfig;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Config implements IConfig {

    private Plugin plugin;

    private int maxSpeed;

    private int currentSpeed;

    private int currentDefault;
    public Config(Plugin plugin){
        this.plugin = plugin;
        this.maxSpeed = plugin.getConfig().getInt("MaxHungerSpeed");
        this.currentSpeed = plugin.getConfig().getInt("CurrentHungerSpeed");
        this.currentDefault = plugin.getConfig().getInt("DefaultHungerSpeed");
    }

    /**
     * Tests constraints on config file.
     *
     * Constraints require that max speed be higher than both current and default speeds
     */
    public boolean CheckConfigValidity(){
        plugin.getLogger().info("Checking validity of config");
        return maxSpeed >= currentSpeed && maxSpeed >= currentDefault;
    }

    public boolean ResetConfigToDefaults(){
        try{
            File f = new File(plugin.getDataFolder(), "config.yml");
            f.delete();
            plugin.saveDefaultConfig();
            return true;
        }catch(Exception ex){
            plugin.getLogger().warning("Failed to reset config. Stacktrace: \n" + ex.getMessage() + "\n" + ex.getStackTrace());
            return false;
        }
    }

    public boolean ChangeConfigValues(int newDefault, int newCurrent, int newMax){
        try{
            plugin.getConfig().set("MaxHungerSpeed", newMax);
            plugin.getConfig().set("CurrentHungerSpeed", newCurrent);
            plugin.getConfig().set("DefaultHungerSpeed", newDefault);
            this.maxSpeed = newMax;
            this.currentDefault = newDefault;
            this.currentSpeed = newCurrent;
        }catch(Exception ex){
            plugin.getLogger().warning("Failed to change config. Stacktrace: \n" + ex.getMessage() + "\n" + ex.getStackTrace());
            return false;
        }
        return CheckConfigValidity();
    }


    public boolean ChangeDefault(int newDefault){
        return ChangeConfigValues(newDefault, this.currentSpeed, this.maxSpeed);
    }

    public boolean ChangeCurrent(int newCurrent){
        return ChangeConfigValues(this.currentDefault, newCurrent, this.maxSpeed);
    }

    public boolean ChangeMax(int newMax){
        return ChangeConfigValues(this.currentDefault, this.currentSpeed, newMax);
    }
}
