package com.paw.pawganizr.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MedicineImportancy {
    SUPPLEMENT("suplement"),
    NORMAL("normalny"),
    ESSENTIAL("niezbędny");

    private String value;
}
