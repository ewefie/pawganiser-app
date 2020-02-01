package com.paw.pawganizr;

import com.paw.pawganizr.model.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PawganizrApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http//localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetUSerById() {
        AppUser appUser = restTemplate.getForObject(getRootUrl() + "/user/1", AppUser.class);
        System.out.println(appUser.getFirstName());
        Assert.assertNotNull(appUser);
    }

    @Test
    public void testCreateUser() {
        AppUser user = new AppUser();
        user.setEmail("test@test.test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setCreatedAt(new Date());
        user.setPets(new HashSet<>());

        ResponseEntity<AppUser> postResponse = restTemplate.postForEntity(getRootUrl() + "/user", user, AppUser.class);


    }

}
