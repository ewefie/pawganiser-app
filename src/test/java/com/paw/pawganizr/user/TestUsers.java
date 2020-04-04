package com.paw.pawganizr.user;

import com.github.javafaker.Faker;

public class TestUsers {
    public static AppUser aUserWithRandomCredentials() {
        var faker = new Faker();
        return AppUser.builder()
                .email(faker.number()
                        .numberBetween(0, 100) + faker.internet()
                        .emailAddress())//faker uniqueness is not sufficient, that's why I add a prefix to generated email
                .name(faker.name()
                        .username())
                .password(faker.internet()
                        .password(8, 16, true, true, true) + faker.number()
                        .numberBetween(0, 10))
                .build();
    }

    public static AppUser aDefaultUser() {
        return AppUser.builder()
                .email("unique@email.com")
                .name("Sarah")
                .password("StrongPassword123")
                .build();
    }
}
