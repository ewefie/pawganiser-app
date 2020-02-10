package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.services.ContactService;
import com.paw.pawganizr.wrappers.Contacts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:9000", "http://pawganiser.sdacademy.xyz"})
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public Contacts findAllContacts(final Principal principal) {
        return contactService.findAllContactsByPrincipal(principal);
    }

    @GetMapping("/contacts/{contactId}")
    public Contact findContact(
            @PathVariable(name = "contactId") final UUID contactId,
            final Principal principal) {
        return contactService.findExistingContact(contactId);
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@Valid @RequestBody final Contact contact,
                                 final Principal principal) {
        return contactService.createContact(contact, principal);
    }

    @DeleteMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@PathVariable("contactId") final UUID contactId,
                                  final Principal principal) {
        contactService.deleteById(contactId);
    }

    @DeleteMapping("/contacts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllContacts(final Principal principal) {
        contactService.deleteAllByPrincipal(principal);
    }

    @PutMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@PathVariable("contactId") final UUID contactId,
                              @RequestBody @Valid Contact updatedContact, final Principal principal) {
        contactService.updateContact(contactId, updatedContact);
    }
}
