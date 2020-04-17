package com.paw.pawganizr.contact;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.user.AppUserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final AppUserService appUserService;

    public ContactService(ContactRepository contactRepository, AppUserService appUserService) {
        this.contactRepository = contactRepository;
        this.appUserService = appUserService;
    }

    public ContactDto saveContact(final Long userId, final ContactDto contactDto) {
        var existingUser = appUserService.getExistingUser(userId);
        var contactToSave = ContactMapper.INSTANCE.dtoToContact(contactDto);
        contactToSave.setUser(existingUser);
        contactRepository.save(contactToSave);
        return ContactMapper.INSTANCE.contactToDto(contactToSave);
    }

    Contact getExistingContact(final Long contactId) {
        return contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact with given id does not exist"));
    }

    public Contacts getAllContactsByUserId(final Long userId) {
        var list = contactRepository.findAllByUserId(userId).stream()
                .map(ContactMapper.INSTANCE::contactToDto)
                .collect(Collectors.toList());
        return new Contacts(list);
    }

    public ContactDto getContactById(final Long contactId, final Long userId) {
        var existingContact = getExistingContact(contactId);
        if (existingContact.getUser().getId().equals(userId)) {
            return ContactMapper.INSTANCE.contactToDto(existingContact);
        }
        throw new AccessDeniedException("You do not have permission to access this content");
    }

    public void deleteAllContactsByUserId(final Long userId) {
        contactRepository.deleteAllByUserId(userId);
    }

    public void deleteContactById(final Long contactId, final Long userId) {
        var existingContact = getExistingContact(contactId);
        if (!existingContact.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to access this content");
        }
        contactRepository.deleteById(contactId);
    }

    public ContactDto updateContact(final Long contactId, final Contact updatedContact, final Long userId) {
        var existingContact = getContactById(userId, contactId);
        existingContact.setDescription(updatedContact.getDescription());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        existingContact.setType(existingContact.getType());
        existingContact.setName(updatedContact.getName());
        contactRepository.save(ContactMapper.INSTANCE.dtoToContact(existingContact));
        return existingContact;
    }
}
