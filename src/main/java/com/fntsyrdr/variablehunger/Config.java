package com.fntsyrdr.variablehunger;

import org.bukkit.plugin.Plugin;

public class Config implements IConfig{

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
            plugin.getConfig().set("MaxHungerSpeed", 10);
            plugin.reloadConfig();
            return true;
        }catch(Exception ex){
            plugin.getLogger().warning("Failed to reset config");
            return false;
        }
    }

    public boolean ChangeConfigValues(int newDefault, int newCurrent, int newMax){

        return false;
    }


    public boolean ChangeDefault(int newDefault){
        if(newDefault > this.maxSpeed){
            return false;
        }else{
            try{
                return true;
            }catch(Exception ex){
                plugin.getLogger().warning("Default speed change failed");
                return false;
            }
        }
    }

    public boolean ChangeCurrent(int newCurrent){
        if(newCurrent > this.maxSpeed){
            return false;
        }else{
            try{
                return true;
            }catch(Exception ex){
                plugin.getLogger().warning("Default speed change failed");
                return false;
            }
        }
    }

    public boolean ChangeMax(int newMax){
        if(newMax < this.maxSpeed){
            return false;
        }else{
            try{
                return true;
            }catch(Exception ex){
                plugin.getLogger().warning("Default speed change failed");
                return false;
            }
        }
    }
}
