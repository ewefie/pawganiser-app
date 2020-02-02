package com.paw.pawganizr.models;

import com.paw.pawganizr.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicPetInfo {
    private UUID id;
    private String petName;
    private PetType type;
    private String avatarUrl;
}
