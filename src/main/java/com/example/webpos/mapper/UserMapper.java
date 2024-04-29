package com.example.webpos.mapper;

import com.example.webpos.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.samples.petclinic.rest.dto.TaxFieldsDto;
import org.springframework.samples.petclinic.rest.dto.UserDto;
import org.springframework.samples.petclinic.rest.dto.UserFieldsDto;

import java.util.*;

@Mapper
public interface UserMapper {

    @Mapping(source = "cart.id", target = "cartId")
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    User toUser(UserFieldsDto userFieldsDto);
    User toUser(TaxFieldsDto taxFieldsDto);

    List<UserDto> toUserDtos(Collection<User> userCollection);

    Collection<User> toUsers(Collection<UserDto> userDtos);
}
