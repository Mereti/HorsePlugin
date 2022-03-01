package com.company;

import com.company.model.Breed;
import com.company.model.GamerStud;

public class Horse{

    //TODO: stworzyc konstruktor, metody get i set, rozwiaza problem z breed i gamerStud

    private Integer horseId;
    private GamerStud gamerStud;
    private String name; // imie konia
    private Breed breed; // rasa konia
    private double fast; // szybkość konia
    private double hungry; // głód konia
    private Integer thirst; // pragnienie konia
    private double appearance;
    private double value;

    /*{
        org.bukkit.entity.Horse horse = null;
        horse.setJumpStrength();
        horse.setJumpStrength();
    }*/

    public Horse(Integer horseId, GamerStud gamerStud, String name,
                 Breed breed, double fast, double hungry, Integer thirst,
                 double appearance, double value) {
        this.horseId = horseId;
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

    public Integer getThirst() {
        return thirst;
    }

    public void setThirst(Integer thirst) {
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
