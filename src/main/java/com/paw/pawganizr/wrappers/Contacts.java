package com.paw.pawganizr.wrappers;

import com.paw.pawganizr.models.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Contacts {
    private List<Contact> contacts;
}
