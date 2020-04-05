package com.paw.pawganizr.security;

import com.paw.pawganizr.security.jwt.AuthResponse;
import com.paw.pawganizr.security.user.LoginRequest;
import com.paw.pawganizr.security.user.SignUpRequest;
import com.paw.pawganizr.user.AppUser;
import com.paw.pawganizr.user.TestUsers;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

@Slf4j
public class TestAuthorization {
    public static String getToken() {
        var user = TestUsers.aUserWithRandomCredentials();
        var signupRequest = aSignupRequestAsString(user);
        var loginRequest = aLoginRequestAsString(user);

        given()
                .basePath("/")
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .post("/auth/signup");

        var authResponse = given()
                .basePath("/")
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post("/auth/login")
                .then()
                .extract()
                .body()
                .asString();

        return Objects.requireNonNull(getTokenFromJSONString(authResponse))
                .getAccessToken();
    }

    public static String aLoginRequestAsString(final AppUser userToLogin) {
        var loginRequestBody = new JSONObject();
        try {
            loginRequestBody.put("email", userToLogin.getEmail());
            loginRequestBody.put("password", userToLogin.getPassword());
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        return loginRequestBody.toString();
    }

    public static LoginRequest aLoginRequest(final AppUser userToLogin) {
        return new LoginRequest(userToLogin.getEmail(), userToLogin.getPassword());
    }

    public static String aSignupRequestAsString(final AppUser userToRegister) {
        var signupRequestBody = new JSONObject();
        try {
            signupRequestBody.put("email", userToRegister.getEmail());
            signupRequestBody.put("name", userToRegister.getName());
            signupRequestBody.put("password", userToRegister.getPassword());
        } catch (JSONException e) {
            log.error(e.getMessage());
        }
        return signupRequestBody.toString();
    }

    public static SignUpRequest aSignupRequest(final AppUser userToRegister) {
        return new SignUpRequest(userToRegister.getName(), userToRegister.getEmail(), userToRegister.getPassword());
    }

    public static SignUpRequest aSignUpRequest(final String password) {
        var request = new SignUpRequest();
        request.setPassword(password);
        return request;
    }

    public static AuthResponse getTokenFromJSONString(String responseBody) {
        try {
            return new ObjectMapper().readValue(responseBody, AuthResponse.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
