package com.paw.pawganizr.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContactType {
    VET("Weterynarz"),
    BEHAVIORIST("Behawiorysta"),
    PET_HOTEL("Hotelik"),
    DOG_KINDERGARTEN("Przedszkole"),
    ANIMAL_SHELTER("Schronisko"),
    BREEDER("Chodowla"),
    DOG_WALKER("Osoba do wyprowadzania na spacer"),
    OTHER("Inny");

    private String value;
}
