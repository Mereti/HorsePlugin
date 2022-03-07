package com.company.model;

import java.util.Arrays;
import java.util.Optional;

public enum HorseBreed {
    FRIESIAN_HORSE(1),
    ARABIAN_HORSE(2),
    PONY(3),
    FIRESIAN(4),
    TRAKEHNER(5),
    LIPIZZANER(6),
    ANDALUSIAN(7),
    HANOVER(8),
    WESTPHALIAN(9),
    WELSH(10);

    private int id;

    HorseBreed(int id) {
        this.id = id;
    }

    public static Optional<HorseBreed> getById(int id){
        return Arrays.stream(values()).filter(horseBreed -> horseBreed.id == id).findAny();
    }
}
