package com.example.webpos.mapper;

import com.example.webpos.model.User;
import org.mapstruct.Mapper;
import org.springframework.samples.petclinic.rest.dto.UserDto;
import org.springframework.samples.petclinic.rest.dto.UserFieldsDto;

import java.util.*;

@Mapper(uses = {ProductMapper.class, ItemMapper.class})
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    User toUser(UserFieldsDto userFieldsDto);

    List<UserDto> toUserDtos(Collection<User> userCollection);

    Collection<User> toUsers(Collection<UserDto> userDtos);
}
