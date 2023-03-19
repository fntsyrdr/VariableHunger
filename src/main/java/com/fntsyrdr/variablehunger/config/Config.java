package com.fntsyrdr.variablehunger.config;

import org.bukkit.plugin.Plugin;

import java.io.File;

public class Config implements IConfig {

    private Plugin plugin;

    private int maxHungerSpeed;

    private int currentHungerSpeed;

    private int currentHungerDefault;
    public Config(Plugin plugin){
        this.plugin = plugin;
        if(!CheckConfigValidity()){
            ResetConfigToDefaults();
        }
        this.maxHungerSpeed = plugin.getConfig().getInt("MaxHungerSpeed");
        this.currentHungerSpeed = plugin.getConfig().getInt("CurrentHungerSpeed");
        this.currentHungerDefault = plugin.getConfig().getInt("DefaultHungerSpeed");
    }

    /**
     * Tests constraints on config file.
     *
     * Outputs warnings to plugin logger based off of possible issues
     */
    public boolean CheckConfigValidity(){
        try{
            int temporaryMax = plugin.getConfig().getInt("MaxHungerSpeed", -1);
            int temporaryDefault = plugin.getConfig().getInt("DefaultHungerSpeed", -1);
            int temporaryCurrent = plugin.getConfig().getInt("CurrentHungerSpeed", -1);
            return temporaryCurrent >= 0 && temporaryDefault >= 0 && temporaryMax >= 0;
        }catch (Exception ex){
            plugin.getLogger().warning("Failed attempt to test config values. Stacktrace: \n" + ex.getMessage() + "\n" + ex.getStackTrace());
            return false;
        }
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
            this.maxHungerSpeed = newMax;
            this.currentHungerDefault = newDefault;
            this.currentHungerSpeed = newCurrent;
        }catch(Exception ex){
            plugin.getLogger().warning("Failed to change config. Stacktrace: \n" + ex.getMessage() + "\n" + ex.getStackTrace());
            return false;
        }
        return CheckConfigValidity();
    }


    public boolean ChangeDefault(int newDefault){
        return ChangeConfigValues(newDefault, this.currentHungerSpeed, this.maxHungerSpeed);
    }

    public boolean ChangeCurrent(int newCurrent){
        return ChangeConfigValues(this.currentHungerDefault, newCurrent, this.maxHungerSpeed);
    }

    public boolean ChangeMax(int newMax){
        return ChangeConfigValues(this.currentHungerDefault, this.currentHungerSpeed, newMax);
    }
}
