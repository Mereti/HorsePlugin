package com.company;

import com.company.model.HorseBreed;
import org.bukkit.entity.Horse;

public interface HorseInterface extends Horse {

    HorseBreed getHorseBreed();
    HorseBreed setHorseBreed();

    com.company.Horse getFast();
    com.company.Horse setFast(double var1);

    com.company.Horse getThirsty();
    com.company.Horse setThirsty(double var1);

    double getThirstyAbsorptionAmount();
    double setThirstyAbsorptionAmount(double var1);

    @Deprecated
    void getMaxThirsty();

    @Deprecated
    void resetMaxThirsty();

    @Deprecated
    void setMaxThirsty(double var1);

    com.company.Horse getAppearance();
    com.company.Horse setAppearance(double var1);

    com.company.Horse getValue();
    com.company.Horse setValue(double var1);





}
