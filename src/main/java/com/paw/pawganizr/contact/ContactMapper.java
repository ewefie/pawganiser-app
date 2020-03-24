package com.paw.pawganizr.contact;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact ContactDtoToContact(ContactDto contactDto);

    ContactDto ContactToContactDto(Contact contact);
}
