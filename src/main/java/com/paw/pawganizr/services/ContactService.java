package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.wrappers.Contacts;
import com.paw.pawganizr.repositories.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserService userService;

    public ContactService(ContactRepository contactRepository, UserService userService) {
        this.contactRepository = contactRepository;
        this.userService = userService;
    }

    public Contact createContact(final UUID appUserId, final Contact contact) {
        final AppUser existingUser = userService.findExistingUser(appUserId);
        contact.setUser(existingUser);
        return contactRepository.save(contact);
    }

    public void deleteById(final UUID contactId, final UUID appUserId) {
        userService.findExistingUser(appUserId);
        contactRepository.deleteById(contactId);
    }

    public Contacts findAllContactsByUserId(final UUID appUserId) {
        final AppUser existingUser = userService.findExistingUser(appUserId);
        return new Contacts(contactRepository.findAllByUser(existingUser));
    }

    public Contact updateContact(final UUID appUserId, UUID contactId, final Contact updatedContact) {
        findExistingContact(appUserId, contactId);
        updatedContact.setId(contactId);
        return contactRepository.save(updatedContact);
    }


    public Optional<Contact> findContactById(final UUID contactId) {
        return contactRepository.findById(contactId);
    }

    public Contact findExistingContact(final UUID userId, final UUID contactId) {
        userService.findExistingUser(userId);
        return findContactById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact with given id does not exist"));
    }

    public void deleteAllByUserId(final UUID userId) {
        userService.findExistingUser(userId);
        contactRepository.deleteAllByUserId(userId);
    }
}
