package com.paw.pawganizr.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PetGender {
    FEMALE("samica"),
    MALE("samiec"),
    OTHER("inna");

    private String value;
}
