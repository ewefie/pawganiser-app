package com.paw.pawganizr.treatment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TreatmentType {
    CHECK_UP("Badanie_ogólne"),
    X_RAY("Prześwietlenie"),
    C_SECTION("Cesarka"),
    SURGERY("Operacja"),
    CASTRATION("Kastracja"),
    STERILISATION("Sterylizacja"),
    ABORTION("Aborcja"),
    WOUND_CARE("Opatrzenie rany"),
    DIARRHEA("Biegunka"),
    BURN("Oparzenie"),
    FIRST_AID("Pierwsza pomoc"),
    TEETH_REMOVAL("usunięcie zęba"),
    TEETH_STONE_REMOVAL("Usunięcie kamienia nazębnego"),
    BONES_FRACTURE("Złamanie"),
    VACCINATION("Szczepienie"),
    OTHER("Inne");

    private String value;
}
