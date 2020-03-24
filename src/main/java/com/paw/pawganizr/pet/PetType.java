package com.paw.pawganizr.pet;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PetType {
    CAT("cat"),
    DOG("dog"),
    //    RAT,
    //    GUINEA_PIG,
    //    PARROT,
    RABBIT("królik"),
    //    FISH,
    //    HAMSTER,
    //    LIZARD,
    //    SNAKE,
    TURTLE_TORTOISE("żółw"),
    OTHER("inny");
    private String value;
}
