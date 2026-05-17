package com.test.gac_4.entities;

public enum CategoryEnum {
    EDUCATION("Education"),
    HEALTH("Santé"),
    ENVIRONMENT("Environnement"),
    POVERTY("Pauvreté"),
    DISASTER_RELIEF("Aide aux Sinistrés"),
    ANIMAL_WELFARE("Bien-être Animal"),
    OTHER("Autre");

    private final String label;

    CategoryEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
