package com.example.webpos.mapper;

import com.example.webpos.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.samples.petclinic.rest.dto.ItemFieldsDto;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ItemMapper {

    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "product.id", target = "productId")
    ItemDto toItemDto(Item item);

    Item toItem(ItemDto itemDto);

    Item toItem(ItemFieldsDto itemFieldsDto);

    List<ItemDto> toItemDtos(Collection<Item> itemCollection);

    Collection<Item> toItems(Collection<ItemDto> itemDtos);
}
