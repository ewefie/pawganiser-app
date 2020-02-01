package com.paw.pawganizr.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MedicineImportancy {
    SUPPLEMENT("suplement"),
    NORMAL("normalny"),
    ESSENTIAL("niezbędny");

    private String value;
}
