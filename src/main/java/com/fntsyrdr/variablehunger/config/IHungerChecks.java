package com.fntsyrdr.variablehunger.config;

public interface IHungerChecks {

    boolean IsHungerInstant();

    boolean IsHungerGone();

    boolean IsHungerNormal();

    boolean IsNextHungerSkip();

    int IsNextHungerMultiplied();
}
