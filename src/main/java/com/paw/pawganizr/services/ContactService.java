package com.paw.pawganizr.services;

import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.models.AppUser;
import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.wrappers.Contacts;
import com.paw.pawganizr.repositories.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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

    public Contact createContact(final Contact contact, final Principal principal) {
        final AppUser existingUser = userService.getUserByPrincipal(principal);
        contact.setUser(existingUser);
        return contactRepository.save(contact);
    }

    public void deleteById(final UUID contactId) {
        contactRepository.deleteById(contactId);
    }

    public Contacts findAllContactsByPrincipal(final Principal principal) {
        final AppUser existingUser = userService.getUserByPrincipal(principal);
        return new Contacts(contactRepository.findAllByUser(existingUser));
    }

    public Contact updateContact(final UUID contactId, final Contact updatedContact) {
        updatedContact.setId(contactId);
        return contactRepository.save(updatedContact);
    }


    public Optional<Contact> findContactById(final UUID contactId) {
        return contactRepository.findById(contactId);
    }

    public Contact findExistingContact(final UUID contactId) {
        return findContactById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact with given id does not exist"));
    }

    public void deleteAllByPrincipal(final Principal principal) {
        final UUID userId = userService.getUserId(principal);
        contactRepository.deleteAllByUserId(userId);
    }
}
