package com.paw.pawganizr.contact;

import com.paw.pawganizr.security.user.CurrentUser;
import com.paw.pawganizr.security.user.UserPrincipal;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/me/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a contact", response = Contacts.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contact successfully uploaded"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ContactDto addContact(@Valid @RequestBody final ContactDto contact,
                                 @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return contactService.saveContact(principal.getId(), contact);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get an object containing a list of all your contacts", response = Contacts.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved contacts"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Contacts getAllContacts(@ApiIgnore @CurrentUser final UserPrincipal principal) {
        return contactService.getAllContactsByUserId(principal.getId());
    }

    @GetMapping(value = "/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get one of your contacts by its ID", response = ContactDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a contact"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ContactDto getContactById(@ApiParam(value = "Contact ID", required = true) @PathVariable(name = "contactId") final Long contactId,
                                     @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return contactService.getContactById(contactId, principal.getId());
    }


    @DeleteMapping(value = "/{contactId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete your contact by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Contact successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteContactById(@ApiIgnore @CurrentUser final UserPrincipal userPrincipal,
                                  @ApiParam(value = "Contact ID", required = true) @PathVariable(name = "contactId") final Long contactId) {
        contactService.deleteContactById(contactId, userPrincipal.getId());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all of your contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Contacts successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteAllContacts(@ApiIgnore @CurrentUser final UserPrincipal userPrincipal) {
        contactService.deleteAllContactsByUserId(userPrincipal.getId());
    }

    @PutMapping(value = "/{contactId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Update contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contacts successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ContactDto updateContact(@ApiParam(value = "Contact ID", required = true) @PathVariable("contactId") final Long contactId,
                                    @RequestBody @Valid Contact updatedContact, @ApiIgnore @CurrentUser final UserPrincipal principal) {
        return contactService.updateContact(contactId, updatedContact, principal.getId());
    }
}
