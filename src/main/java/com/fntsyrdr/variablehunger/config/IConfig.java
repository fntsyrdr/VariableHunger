package com.fntsyrdr.variablehunger.config;


/**
 * This interface programmatically gets and adjusts config values
 *
 * @author fntsyrdr
 */
public interface IConfig {

    /**
     * Ensures that the plugin is valid
     *
     * @return boolean indicating whether the plugin meets constraints on config
     * */
    boolean CheckConfigValidity();

    /**
     * Resets the plugin to its default value
     *
     * @return boolean indicating whether the plugin was succesfully reset
     */
    boolean ResetConfigToDefaults();

    /**
     * Changes the value of only the default speed
     *
     * @param newDefault
     *  New default
     * @return boolean indicating whether the config was successfully changed
     */
    boolean ChangeDefault(int newDefault);

    /**
     * Changes the value of only the current speed
     * @param newCurrent
     * @return boolean indicating whether the config was successfully changed
     */
    boolean ChangeCurrent(int newCurrent);

    /**
     * Changes the value of only the max speed
     * @param newMax
     * @return boolean indicating whether the config was successfully changed
     */
    boolean ChangeMax(int newMax);

    /**
     * Changes the value of the default, max, and current speeds
     * @param newDefault
     * @param newCurrent
     * @param newMax
     * @return boolean indicating whether the config was changed successfully
     */
    boolean ChangeConfigValues(int newDefault, int newCurrent, int newMax);
}