package com.example.webpos.mapper;

import com.example.webpos.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.samples.petclinic.rest.dto.CartDto;
import org.springframework.samples.petclinic.rest.dto.CartFieldsDto;

import java.util.Collection;
import java.util.*;

@Mapper(uses = ItemMapper.class)
public interface CartMapper {

    @Mapping(source = "user.id", target = "userId")
    CartDto toCartDto(Cart cart);

    Cart toCart(CartDto cartDto);

    Cart toCart(CartFieldsDto cartFieldsDto);

    List<CartDto> toCartDtos(Collection<Cart> cartCollection);

    Collection<Cart> toCarts(Collection<CartDto> cartDtos);
}
