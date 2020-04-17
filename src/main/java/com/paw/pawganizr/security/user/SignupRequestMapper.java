package com.paw.pawganizr.security.user;

import com.paw.pawganizr.user.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignupRequestMapper {
    SignupRequestMapper INSTANCE = Mappers.getMapper(SignupRequestMapper.class);

    AppUser map(SignUpRequest signUpRequest);
}
