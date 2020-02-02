package com.paw.pawganizr.services;

import com.paw.pawganizr.models.Pet;
import com.paw.pawganizr.models.BasicPetInfo;
import org.springframework.stereotype.Service;

@Service
public class PetToBasicPetInfoMapper {

    public BasicPetInfo mapPetToBasicPetInfo(final Pet pet) {
        return BasicPetInfo.builder()
                .avatarUrl(pet.getPetAvatarUrl())
                .petName(pet.getPetName())
                .id(pet.getId())
                .type(pet.getType())
                .build();
    }
}
