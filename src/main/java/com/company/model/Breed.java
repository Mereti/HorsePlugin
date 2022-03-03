package com.company.model;


public class Breed {
    private Integer breedId;
    private HorseBreed horseBreed;
    private double fast;
    private double hungry;
    private double thirst;
    private double appearance;
    private double value;

    public Breed(Integer breedId, HorseBreed horseBreed, double fast, double hungry, double thirst, double appearance, double value) {
        this.breedId = breedId;
        this.horseBreed = horseBreed;
        this.fast = fast;
        this.hungry = hungry;
        this.thirst = thirst;
        this.appearance = appearance;
        this.value = value;
    }



    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public HorseBreed getHorseBreed() {
        return horseBreed;
    }

    public void setHorseBreed(HorseBreed horseBreed) {
        this.horseBreed = horseBreed;
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

    public void setValue(Double value) {
        this.value = value;
    }
}


