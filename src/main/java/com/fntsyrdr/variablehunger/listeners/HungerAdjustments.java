package com.fntsyrdr.variablehunger.listeners;

import com.fntsyrdr.variablehunger.config.IHungerChecks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import com.fntsyrdr.variablehunger.config.HungerChecks;
import org.bukkit.plugin.Plugin;

public class HungerAdjustments implements Listener {

    private Plugin plugin;
    private IHungerChecks hungerChecks;

    public HungerAdjustments(Plugin plugin){
        this.plugin = plugin;
        hungerChecks = new HungerChecks(plugin);
    }
    @EventHandler
    public void HungerEvent(FoodLevelChangeEvent event){
        plugin.getLogger().info("FoodLevelChangEvent Started");
        Player player = (Player) event.getEntity();

        //Only check for instances where player is hungrier
        if(player.getFoodLevel() < event.getFoodLevel()){
            return;
        }
        plugin.getLogger().info("Passed check for hungrier change");
        //Check for special cases
        if(hungerChecks.IsHungerGone()){
            plugin.getLogger().info("IsHungerGone passed");
            event.setFoodLevel(20);
            return;
        }else if(hungerChecks.IsHungerInstant()){
            plugin.getLogger().info("IsHungerInstant passed");
            event.setFoodLevel(0);
            return;
        }else if(hungerChecks.IsHungerNormal()){
            plugin.getLogger().info("IsHungerNormal passed");

            //explicitly do nothing
            return;
        }
        int hungerMultiplier = hungerChecks.IsNextHungerMultiplied();
        plugin.getLogger().info("Hunger Multiplier " + hungerMultiplier);

        if(hungerMultiplier > 0){
            event.setFoodLevel(player.getFoodLevel() - hungerMultiplier);
        }else if(hungerChecks.IsNextHungerSkip()){
            plugin.getLogger().info("Hunger Skipped");
            event.setCancelled(true);
        }
    }
}
