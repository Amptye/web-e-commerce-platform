package com.example.webpos.mapper;

import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.samples.petclinic.rest.dto.ProductDto;
import org.springframework.samples.petclinic.rest.dto.UserDto;
import org.springframework.samples.petclinic.rest.dto.UserFieldsDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPass( user.getPass() );
        userDto.setMoney( user.getMoney() );
        userDto.setAddress( user.getAddress() );
        userDto.setContact( user.getContact() );
        userDto.setImage( user.getImage() );
        userDto.setId( user.getId() );
        userDto.setItems( itemMapper.toItemDtos( user.getItems() ) );
        userDto.setProducts( productMapper.toProductDtos( user.getProducts() ) );

        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        if ( userDto.getId() != null ) {
            user.setId( userDto.getId() );
        }
        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        user.setPass( userDto.getPass() );
        if ( userDto.getMoney() != null ) {
            user.setMoney( userDto.getMoney() );
        }
        user.setItems( itemDtoListToItemList( userDto.getItems() ) );
        user.setProducts( productDtoListToProductList( userDto.getProducts() ) );
        user.setAddress( userDto.getAddress() );
        user.setContact( userDto.getContact() );
        user.setImage( userDto.getImage() );

        return user;
    }

    @Override
    public User toUser(UserFieldsDto userFieldsDto) {
        if ( userFieldsDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userFieldsDto.getName() );
        user.setEmail( userFieldsDto.getEmail() );
        user.setPass( userFieldsDto.getPass() );
        if ( userFieldsDto.getMoney() != null ) {
            user.setMoney( userFieldsDto.getMoney() );
        }
        user.setAddress( userFieldsDto.getAddress() );
        user.setContact( userFieldsDto.getContact() );
        user.setImage( userFieldsDto.getImage() );

        return user;
    }

    @Override
    public List<UserDto> toUserDtos(Collection<User> userCollection) {
        if ( userCollection == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userCollection.size() );
        for ( User user : userCollection ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }

    @Override
    public Collection<User> toUsers(Collection<UserDto> userDtos) {
        if ( userDtos == null ) {
            return null;
        }

        Collection<User> collection = new ArrayList<User>( userDtos.size() );
        for ( UserDto userDto : userDtos ) {
            collection.add( toUser( userDto ) );
        }

        return collection;
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

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productMapper.toProduct( productDto ) );
        }

        return list1;
    }
}
