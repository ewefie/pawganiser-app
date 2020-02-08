package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.services.ContactService;
import com.paw.pawganizr.services.UserService;
import com.paw.pawganizr.wrappers.Contacts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/hello")
    public String printHello() {
        return "Hello";
    }

    @GetMapping("/{userId}/contacts")
    public Contacts findAllContacts(@PathVariable(name = "userId") final UUID userId) {
        return contactService.findAllContactsByUserId(userId);
    }

    @GetMapping("/{userId}/contacts/{contactId}")
    public Contact findContact(@PathVariable(name = "userId") final UUID userId,
                               @PathVariable(name = "contactId") final UUID contactId) {
        return contactService.findExistingContact(userId, contactId);
    }

    @PostMapping("/{userId}/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@Valid @RequestBody final Contact contact,
                                 @PathVariable("userId") final UUID userId) {
        return contactService.createContact(userId, contact);
    }

    @DeleteMapping("/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@PathVariable("contactId") final UUID contactId,
                                  @PathVariable("userId") final UUID userId) {
        contactService.deleteById(contactId, userId);
    }

    @DeleteMapping("/{userId}/contacts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllContacts(@PathVariable("userId") final UUID userId) {
        contactService.deleteAllByUserId(userId);
    }

    @PutMapping("/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@PathVariable("contactId") final UUID contactId,
                              @PathVariable("userId") final UUID userId,
                              @RequestBody @Valid Contact updatedContact) {
        contactService.updateContact(userId, contactId, updatedContact);
    }
}
