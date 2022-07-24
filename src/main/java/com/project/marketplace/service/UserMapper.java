package com.project.marketplace.service;

import com.project.marketplace.dto.UserDto;
import com.project.marketplace.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
* Declaring an instruction for mapstruct
*/
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto mapUserDto(User account);
    User mapUser(UserDto userDto);

}


