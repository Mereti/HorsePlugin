package com.company;

import com.company.model.Breed;
import com.company.model.GamerStud;
import org.jetbrains.annotations.Nullable;

public class Horse {

    //TODO: stworzyc konstruktor, metody get i set, rozwiaza problem z breed i gamerStud

    private Integer horseId;
    private String bukkitHorseId;
    private GamerStud gamerStud;
    private String name; // imie konia
    private Breed breed; // rasa konia
    private double fast; // szybkość konia
    private double hungry; // głód konia
    private double thirst; // pragnienie konia
    private double appearance;
    private double value;

    public Horse(Integer horseId, String bukkitHorseId, GamerStud gamerStud, String name,
                 Breed breed, double fast, double hungry, double thirst,
                 double appearance, double value) {
        this.horseId = horseId;
        this.bukkitHorseId = bukkitHorseId;
        this.gamerStud = gamerStud;
        this.name = name;
        this.breed = breed;
        this.fast = fast;
        this.hungry = hungry;
        this.thirst = thirst;
        this.appearance = appearance;
        this.value = value;
    }

    public Integer getHorseId() {
        return horseId;
    }

    public void setHorseId(Integer horseId) {
        this.horseId = horseId;
    }

    public String getBukkitHorseId() {
        return bukkitHorseId;
    }

    public void setBukkitHorseId(String bukkitHorseId) {
        this.bukkitHorseId = bukkitHorseId;
    }

    public GamerStud getGamerStud() {
        return gamerStud;
    }

    public void setGamerStud(GamerStud gamerStud) {
        this.gamerStud = gamerStud;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public double getFast() {
        return fast;
    }

    public void setFast(double fast) {
        this.fast = fast;
    }

    public double getHungry() {
        return hungry;
    }

    public void setHungry(double hungry) {
        this.hungry = hungry;
    }

    public double getThirst() {
        return thirst;
    }

    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public double getAppearance() {
        return appearance;
    }

    public void setAppearance(double appearance) {
        this.appearance = appearance;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


}
