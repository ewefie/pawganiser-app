package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Contact;
import com.paw.pawganizr.security.CurrentUser;
import com.paw.pawganizr.security.UserPrincipal;
import com.paw.pawganizr.services.ContactService;
import com.paw.pawganizr.wrappers.Contacts;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("hasRole('USER')")
    public Contacts findAllContacts(@CurrentUser final UserPrincipal principal) {
        return contactService.findAllContactsByUserId(principal.getId());
    }

    @GetMapping("/contacts/{contactId}")
    @PreAuthorize("hasRole('USER')")
    public Contact findContact(
            @PathVariable(name = "contactId") final UUID contactId,
            @CurrentUser final UserPrincipal principal) {
        return contactService.findExistingContact(contactId);
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public Contact createContact(@Valid @RequestBody final Contact contact,
                                 @CurrentUser final UserPrincipal principal) {
        return contactService.createContact(principal.getId(), contact);
    }

    @DeleteMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteContactById(@PathVariable("contactId") final UUID contactId,
                                  @CurrentUser final UserPrincipal principal) {
        contactService.deleteById(contactId);
    }

    @DeleteMapping("/contacts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void deleteAllContacts(@CurrentUser final UserPrincipal principal) {
        contactService.deleteAllByUserId(principal.getId());
    }

    @PutMapping("/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void updateContact(@PathVariable("contactId") final UUID contactId,
                              @RequestBody @Valid Contact updatedContact, @CurrentUser final UserPrincipal principal) {
        contactService.updateContact(contactId, updatedContact);
    }
}
