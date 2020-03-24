package com.paw.pawganizr.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private Long id;
    private ContactType type;
    private String description;
    private String name;
    private String phoneNumber;
    private String email;
}
