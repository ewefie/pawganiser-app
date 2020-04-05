package com.paw.pawganizr.integrationtests;

import com.paw.pawganizr.contact.*;
import com.paw.pawganizr.exceptions.ResourceNotFoundException;
import com.paw.pawganizr.security.TestAuthorization;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactControllerTest {
    @LocalServerPort
    private int port;
    private String token;

    @MockBean
    private ContactService contactService;

    @BeforeAll
    public void authorize() {
        RestAssured.port = port;
        token = TestAuthorization.getToken();
    }

    @Test
    public void shouldReturnUnauthorizedWhenGetWithNoTokePerformed() {
        //@formatter:off
        when()
                .get("/users/me/contacts")
                .then()
                .assertThat()
                .statusCode(401);
        //@formatter:on
    }

    @Test
    public void shouldReturnListOfUsersContactsWhenGetAllPerformed() {
        var contactsToReturn = TestContacts.withListOfRandomContacts(6);

        Mockito.when(contactService.getAllContactsByUserId(ArgumentMatchers.anyLong()))
                .thenReturn(contactsToReturn);
        var response = given()
                .header("Authorization", "Bearer " + token)
                .get("/users/me/contacts");
        var foundContacts = TestContacts.fromJSONString(response.getBody()
                .asString());

        assert nonNull(foundContacts);
        assertEquals(6, foundContacts.getContacts()
                .size());
        assertEquals(contactsToReturn, foundContacts);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void shouldReturnSavedContactWhenPostPerformed() {
        var contactToSave = TestContactDto.aRandomContactDto();
        var jsonContactDto = TestContactDto.asJSONString(contactToSave);

// fixme: this should works but it doesn't ->       Mockito.when(contactService.saveContact(ArgumentMatchers.anyLong(), ArgumentMatchers.eq(contactToSave)))
        Mockito.when(contactService.saveContact(ArgumentMatchers.anyLong(), ArgumentMatchers.any(ContactDto.class)))
                .thenReturn(contactToSave);
        var response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonContactDto)
                .post("/users/me/contacts");
        var returnedContactDto = TestContactDto.fromJSONString(response.getBody()
                .asString());

        assertEquals(201, response.getStatusCode());
        assertEquals(contactToSave, returnedContactDto);
    }

    @Test
    public void shouldReturnContactWhenGetContactByIdPerformed() {
        var contactToReturn = TestContactDto.aRandomContactDto();
        contactToReturn.setId(3L);

        Mockito.when(contactService.getContactById(ArgumentMatchers.eq(3L), ArgumentMatchers.anyLong()))
                .thenReturn(contactToReturn);
        var response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .get("/users/me/contacts/" + contactToReturn.getId());
        var returnedContact = TestContactDto.fromJSONString(response.getBody()
                .asString());

        assertEquals(contactToReturn, returnedContact);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void shouldReturnNoContentStatusCodeAfterDeleteContactByIdPerformed() {
        var contactId = 4L;

        //@formatter:off
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/me/contacts/" + contactId)
                .then()
                .assertThat()
                .statusCode(204);
        //@formatter:on
    }

    @Test
    public void shouldReturnNotFoundStatusCodeWhenGetContactByIdPerformed() {
        var contactId = 6L;

        Mockito.when(contactService.getContactById(ArgumentMatchers.eq(contactId), ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Contact with given id does not exist"));
        var response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .get("/users/me/contacts/" + contactId);

        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody()
                .asString()
                .contains("Contact with given id does not exist"));
    }

    @Test
    public void shouldReturnedNoContentWhenDeleteAllContactsPerformed() {
        //@formatter:off
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/me/contacts")
                .then()
                .statusCode(204);
        //@formatter:on
    }

    @Test
    public void shouldReturnContactsWithEmptyListWhenGetAllContactsPerformed() {
        Mockito.when(contactService.getAllContactsByUserId(ArgumentMatchers.anyLong()))
                .thenReturn(new Contacts(Collections.emptyList()));
        var response = given()
                .header("Authorization", "Bearer " + token)
                .get("/users/me/contacts");
        var contacts = TestContacts.fromJSONString(response.getBody()
                .asString());

        assert nonNull(contacts);
        assertTrue(contacts.getContacts()
                .isEmpty());
        assertEquals(200, response.getStatusCode());
    }
}
