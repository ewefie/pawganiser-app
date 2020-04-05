package com.paw.pawganizr.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class TestContacts {
    public static Contacts withListOfRandomContacts(int listSize) {
        var contactDtoList = new ArrayList<ContactDto>();
        for (int i = 0; i < listSize; i++) {
            contactDtoList.add(TestContactDto.aRandomContactDto());
        }
        return new Contacts(contactDtoList);
    }

    public static Contacts fromJSONString(String contactAsString) {
        try {
            return new ObjectMapper().readValue(contactAsString, Contacts.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
