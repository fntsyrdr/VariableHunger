package com.fntsyrdr.variablehunger.config;

import org.bukkit.plugin.Plugin;

public class HungerChecks implements IHungerChecks{

    private IConfig config;

    private float eventsSinceSkip;

    private float remainderMultiplier;
    public HungerChecks(Plugin plugin){
        config = new Config(plugin);
    }

    public boolean IsHungerInstant(){
        return config.getCurrentHungerSpeed() >= config.getMaxHungerSpeed();
    }

    public boolean IsHungerGone(){
        return config.getCurrentHungerSpeed() == 0;
    }

    public boolean IsHungerNormal(){
        return config.getCurrentHungerSpeed() == config.getCurrentHungerDefault();
    }

    /**
     * Checks to see if enough hunger events have been skipped. Always returns false if config is set to have
     * current hunger rate greater than the default
     *
     * IsHungerGone and IsHungerInstant have both already been checked and failed
     * @return true if next hunger event should be skipped, false if not
     */
    public boolean IsNextHungerSkip(){

        float numberToSkip = (float)config.getCurrentHungerDefault() / (float)config.getCurrentHungerSpeed();
        if(numberToSkip < 1.0){
            return false;
        }
        if(eventsSinceSkip > numberToSkip){
            eventsSinceSkip -= numberToSkip;
            return false;
        }

        //events since skip go up if no skip occurs
        eventsSinceSkip++;
        return true;
    }

    public int IsNextHungerMultiplied(){
        if(config.getCurrentHungerSpeed() > config.getCurrentHungerDefault()){
            float multiplierCountWithRemainder = (float)config.getCurrentHungerSpeed() / (float)config.getCurrentHungerDefault();
            int multiplierCount = (int)multiplierCountWithRemainder;
            remainderMultiplier += multiplierCountWithRemainder - Math.floor(multiplierCountWithRemainder);
            if(remainderMultiplier > 1.0){
                remainderMultiplier -= 1.0;
                multiplierCount++;
            }
            return multiplierCount;
        }else{
            return -1;
        }
    }

}
