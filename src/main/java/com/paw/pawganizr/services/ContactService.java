package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.AccessDeniedException;
import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.repositories.ContactRepository;
import com.paw.pawganizr.wrappers.Contacts;
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

    public Contact createContact(final UUID userId, final Contact contact) {
        final AppUser existingUser = userService.findExistingUser(userId);
        contact.setUser(existingUser);
        return contactRepository.save(contact);
    }

    public void deleteById(final UUID contactId, final UUID userId) {
        returnContactIfUserHasAccess(userId, contactId);
        contactRepository.deleteById(contactId);
    }

    public Contacts findAllContactsByUserId(final UUID userId) {
        return new Contacts(contactRepository.findAllByUserId(userId));
    }

    public Optional<Contact> findContactById(final UUID contactId) {
        return contactRepository.findById(contactId);
    }

    public Contact findExistingContact(final UUID userId, final UUID contactId) {
        return returnContactIfUserHasAccess(userId, contactId);
    }

    public Contact findExistingContact(final UUID contactId) {
        return findContactById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact with given id does not exist"));
    }

    public void deleteAllByUserId(final UUID userId) {
        userService.findExistingUser(userId);
        contactRepository.deleteAllByUserId(userId);
    }

    public Contact updateContact(final UUID contactId, final Contact updatedContact, final UUID userId) {
        final Contact existingContact = returnContactIfUserHasAccess(userId, contactId);
        existingContact.setDescription(updatedContact.getDescription());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        existingContact.setType(existingContact.getType());
        existingContact.setName(updatedContact.getName());
        return contactRepository.save(existingContact);
    }

    public Contact returnContactIfUserHasAccess(final UUID userId, final UUID contactId) {
        final Contact existingContact = findExistingContact(contactId);
        if (existingContact.getUser().getId().equals(userId)) {
            return existingContact;
        }
        throw new AccessDeniedException("User do not have permissions to see this content");
    }
}
