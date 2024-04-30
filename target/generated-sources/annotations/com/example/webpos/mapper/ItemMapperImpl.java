package com.example.webpos.mapper;

import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.samples.petclinic.rest.dto.ItemFieldsDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDto toItemDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setUserId( itemUserId( item ) );
        itemDto.setProductId( itemProductId( item ) );
        itemDto.setQuantity( item.getQuantity() );
        itemDto.setId( item.getId() );

        return itemDto;
    }

    @Override
    public Item toItem(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Item item = new Item();

        if ( itemDto.getId() != null ) {
            item.setId( itemDto.getId() );
        }
        if ( itemDto.getQuantity() != null ) {
            item.setQuantity( itemDto.getQuantity() );
        }

        return item;
    }

    @Override
    public Item toItem(ItemFieldsDto itemFieldsDto) {
        if ( itemFieldsDto == null ) {
            return null;
        }

        Item item = new Item();

        if ( itemFieldsDto.getQuantity() != null ) {
            item.setQuantity( itemFieldsDto.getQuantity() );
        }

        return item;
    }

    @Override
    public List<ItemDto> toItemDtos(Collection<Item> itemCollection) {
        if ( itemCollection == null ) {
            return null;
        }

        List<ItemDto> list = new ArrayList<ItemDto>( itemCollection.size() );
        for ( Item item : itemCollection ) {
            list.add( toItemDto( item ) );
        }

        return list;
    }

    @Override
    public Collection<Item> toItems(Collection<ItemDto> itemDtos) {
        if ( itemDtos == null ) {
            return null;
        }

        Collection<Item> collection = new ArrayList<Item>( itemDtos.size() );
        for ( ItemDto itemDto : itemDtos ) {
            collection.add( toItem( itemDto ) );
        }

        return collection;
    }

    private Long itemUserId(Item item) {
        if ( item == null ) {
            return null;
        }
        User user = item.getUser();
        if ( user == null ) {
            return null;
        }
        long id = user.getId();
        return id;
    }

    private Long itemProductId(Item item) {
        if ( item == null ) {
            return null;
        }
        Product product = item.getProduct();
        if ( product == null ) {
            return null;
        }
        long id = product.getId();
        return id;
    }
}
