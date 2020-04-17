package com.paw.pawganizr.medicine;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MedicineImportancy {
    SUPPLEMENT("suplement"),
    NORMAL("normalny"),
    ESSENTIAL("niezbędny");

    private String value;
}
