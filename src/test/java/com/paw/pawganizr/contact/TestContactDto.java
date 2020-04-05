package com.paw.pawganizr.contact;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Slf4j
public class TestContactDto {
    private static final List<ContactType> CONTACT_TYPE_LIST = List.of(ContactType.values());
    private static final Random RANDOM = new Random();
    private final static Faker FAKER = new Faker();

    public static ContactDto aRandomContactDto() {
        return ContactDto.builder()
                .id(RANDOM.nextLong())
                .description(FAKER.lorem()
                        .sentence())
                .email(FAKER.internet()
                        .emailAddress())
                .name(FAKER.name()
                        .fullName())
                .phoneNumber(FAKER.phoneNumber()
                        .subscriberNumber(9))
                .type(CONTACT_TYPE_LIST.get(RANDOM.nextInt(CONTACT_TYPE_LIST.size())))
                .build();
    }

    public static String asJSONString(ContactDto contactDto) {
        var contactAsJSONString = new JSONObject();
        try {
            contactAsJSONString.put("email", contactDto.getEmail());
            contactAsJSONString.put("name", contactDto.getName());
            contactAsJSONString.put("phone_number", contactDto.getPhoneNumber());
            contactAsJSONString.put("type", contactDto.getType());
            contactAsJSONString.put("description", contactDto.getDescription());
            contactAsJSONString.put("id", contactDto.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contactAsJSONString.toString();
    }

    public static ContactDto fromJSONString(String contactAsJSONString) {
        try {
            return new ObjectMapper().readValue(contactAsJSONString, ContactDto.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
