package com.paw.pawganizr.contact;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Contacts {
    private List<ContactDto> contacts;
}
