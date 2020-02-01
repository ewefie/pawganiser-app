package com.paw.pawganizr;

import com.paw.pawganizr.model.AppUser;
import com.paw.pawganizr.model.Pet;
import com.paw.pawganizr.model.PetGender;
import com.paw.pawganizr.model.PetType;
import com.paw.pawganizr.repository.PetRepository;
import com.paw.pawganizr.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MockDataCreationRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public MockDataCreationRunner(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final AppUser user = AppUser.builder()
                .firstName("Lolek")
                .lastName("Lolkowski")
                .email("lolek@lolek.com")
                .build();
        userRepository.save(user);


        final Pet petCat = Pet.builder()
                .petName("Bobek")
                .owner(user)
                .type(PetType.CAT)
                .birthDate(LocalDate.now())
                .gender(PetGender.OTHER)
                .build();
        final Pet petDog = Pet.builder()
                .petName("Azorek")
                .owner(user)
                .type(PetType.DOG)
                .birthDate(LocalDate.now())
                .gender(PetGender.MALE)
                .build();

        petRepository.save(petCat);
        petRepository.save(petDog);
    }
}
