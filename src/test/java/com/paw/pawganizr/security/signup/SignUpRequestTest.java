package com.paw.pawganizr.security.signup;

import com.github.javafaker.Faker;
import com.paw.pawganizr.security.TestAuthorization;
import com.paw.pawganizr.security.user.SignUpRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.paw.pawganizr.user.TestUsers.generateStrongPassword;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SignUpRequestTest {
    @ParameterizedTest
    @MethodSource("signupRequests")
    void validatePasswords(SignUpRequest request, Boolean isPasswordValid) {
        assertEquals(isPasswordValid, request.isPasswordValid());
    }

    private static Stream<Arguments> signupRequests() {
        return Stream.of(
                Arguments.of(TestAuthorization.aSignUpRequest("strongPassword124!"), true),
                Arguments.of(TestAuthorization.aSignUpRequest(generateStrongPassword()), true),
                Arguments.of(TestAuthorization.aSignUpRequest(new Faker().internet()
                        .password(8, 16, false)), false),
                Arguments.of(TestAuthorization.aSignUpRequest(new Faker().internet()
                        .password(0, 7)), false)
        );
    }
}
