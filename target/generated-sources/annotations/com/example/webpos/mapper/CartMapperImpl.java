package com.example.webpos.mapper;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.rest.dto.CartDto;
import org.springframework.samples.petclinic.rest.dto.CartFieldsDto;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public CartDto toCartDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setUserId( cartUserId( cart ) );
        cartDto.setItems( itemMapper.toItemDtos( cart.getItems() ) );
        cartDto.setId( cart.getId() );

        return cartDto;
    }

    @Override
    public Cart toCart(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        Cart cart = new Cart();

        if ( cartDto.getId() != null ) {
            cart.setId( cartDto.getId() );
        }
        cart.setItems( itemDtoListToItemList( cartDto.getItems() ) );

        return cart;
    }

    @Override
    public Cart toCart(CartFieldsDto cartFieldsDto) {
        if ( cartFieldsDto == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setItems( itemDtoListToItemList( cartFieldsDto.getItems() ) );

        return cart;
    }

    @Override
    public List<CartDto> toCartDtos(Collection<Cart> cartCollection) {
        if ( cartCollection == null ) {
            return null;
        }

        List<CartDto> list = new ArrayList<CartDto>( cartCollection.size() );
        for ( Cart cart : cartCollection ) {
            list.add( toCartDto( cart ) );
        }

        return list;
    }

    @Override
    public Collection<Cart> toCarts(Collection<CartDto> cartDtos) {
        if ( cartDtos == null ) {
            return null;
        }

        Collection<Cart> collection = new ArrayList<Cart>( cartDtos.size() );
        for ( CartDto cartDto : cartDtos ) {
            collection.add( toCart( cartDto ) );
        }

        return collection;
    }

    private Long cartUserId(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        long id = user.getId();
        return id;
    }

    protected List<Item> itemDtoListToItemList(List<ItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Item> list1 = new ArrayList<Item>( list.size() );
        for ( ItemDto itemDto : list ) {
            list1.add( itemMapper.toItem( itemDto ) );
        }

        return list1;
    }
}
