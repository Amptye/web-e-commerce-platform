package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.mapper.ItemMapper;
import com.example.webpos.mapper.ProductMapper;
import com.example.webpos.mapper.UserMapper;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.rest.api.UsersApi;
import org.springframework.samples.petclinic.rest.dto.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class UserController implements UsersApi {

    private final PosService posService;

    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final ProductMapper productMapper;

    public UserController(PosService posService, UserMapper userMapper,
                          ItemMapper itemMapper, ProductMapper productMapper) {
        this.posService = posService;
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<List<UserDto>> listUsers() {
        List<UserDto> users = new ArrayList<>(userMapper.toUserDtos(this.posService.findAllUsers()));
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> showUserById(Long userId) {
        User user = this.posService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> addUser(UserFieldsDto userFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        if(userFieldsDto.getPass() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.toUser(userFieldsDto);
        this.posService.saveUser(user);
        UserDto userDto = userMapper.toUserDto(user);
        headers.setLocation(UriComponentsBuilder.newInstance()
                .path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(userDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long userId, UserFieldsDto userFieldsDto) {
        User currentUser = this.posService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(userFieldsDto.getName() != null) {
            currentUser.setName(userFieldsDto.getName());
        }
        if(userFieldsDto.getPass() != null) {
            currentUser.setPass(userFieldsDto.getPass());
        }
        if(userFieldsDto.getEmail() != null) {
            currentUser.setEmail(userFieldsDto.getEmail());
        }
        if(userFieldsDto.getMoney() != null) {
            currentUser.setMoney(userFieldsDto.getMoney());
        }
        if(userFieldsDto.getAddress() != null){
            currentUser.setAddress(userFieldsDto.getAddress());
        }
        if(userFieldsDto.getContact() != null){
            currentUser.setContact(userFieldsDto.getContact());
        }
        this.posService.saveUser(currentUser);
        return new ResponseEntity<>(userMapper.toUserDto(currentUser), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(Long userId) {
        User user = this.posService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.posService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UserDto> loginUser(UserDto userDto) {
        if(userDto.getUid() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User currentUser = posService.findUserByUid(userDto.getUid());
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean login = false;
        if(userDto.getPass() != null) {
            login = userDto.getPass().equals(currentUser.getPass());
        } else {
            login = (currentUser.getPass() == null);
        }
        if(login) {
            return new ResponseEntity<>(userMapper.toUserDto(currentUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<UserDto> chargeUserById(Long userId) {
        User user = this.posService.findUserById(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!user.charge()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        posService.saveUser(user);
        return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItemToUser(Long userId, ItemFieldsDto itemFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        User user = posService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = posService.findProductById(itemFieldsDto.getProductId());
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = itemMapper.toItem(itemFieldsDto);
        item.setProduct(product);
        item.setUser(user);
        this.posService.saveItem(item);
        user.addItem(item);
        this.posService.saveItem(item);
        ItemDto itemDto = itemMapper.toItemDto(item);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/users/{userId}")
                .buildAndExpand(userId).toUri());
        return new ResponseEntity<>(itemDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> addProductToOwner(Long userId, ProductFieldsDto productFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        User user = posService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productMapper.toProduct(productFieldsDto);
        if(productFieldsDto.getQuantity() == null){
            product.setQuantity(1);
        } else {
            product.setQuantity(productFieldsDto.getQuantity());
        }
        if(product.getQuantity() <= 0){
            product.setQuantity(0);
        }
        this.posService.saveProduct(product);
        ProductDto productDto = productMapper.toProductDto(product);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(productDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemDto> addProductToUser(Long userId, Long productId) {
        User currentUser = this.posService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = this.posService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item old_item = null;
        for(Item item : currentUser.getItems()){
            if(item.getProduct().getId() == productId){
                old_item = item;
                break;
            }
        }
        if(old_item == null){
            old_item = new Item(currentUser, product, 1);
            this.posService.saveItem(old_item);
            currentUser.addItem(old_item);
            this.posService.saveItem(old_item);
        } else {
            old_item.setQuantity(old_item.getQuantity()+1);
            this.posService.saveItem(old_item);
        }
        return new ResponseEntity<>(itemMapper.toItemDto(old_item), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addUsersItemById(Long userId, Long itemId) {
        User currentUser = this.posService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item currentItem = this.posService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()+1);
        this.posService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> deleteOwnersProduct(Long userId, Long productId) {
        User user = this.posService.findUserById(userId);
        Product product = this.posService.findProductById(productId);
        if (user == null || product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!product.getOwner().equals(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                this.posService.deleteProduct(product);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<ItemDto> deleteUsersItem(Long userId, Long itemId) {
        User user = this.posService.findUserById(userId);
        Item item = this.posService.findItemById(itemId);
        if (user == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getUser().equals(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                this.posService.deleteItem(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<ProductDto> getOwnersProduct(Long userId, Long productId) {
        User user = this.posService.findUserById(userId);
        Product product = this.posService.findProductById(productId);
        if (user == null || product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!product.getOwner().equals(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<ItemDto> getUsersItem(Long userId, Long itemId) {
        User user = this.posService.findUserById(userId);
        Item item = this.posService.findItemById(itemId);
        if (user == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getUser().equals(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(itemMapper.toItemDto(item), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<List<ItemDto>> listUsersItems(Long userId) {
        User user = this.posService.findUserById(userId);
        if (user == null || user.getItems().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ItemDto> items = itemMapper.toItemDtos(user.getItems());
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<ProductDto>> listOwnersProducts(Long userId) {
        User user = this.posService.findUserById(userId);
        if (user == null || user.getProducts().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ProductDto> products = productMapper.toProductDtos(user.getProducts());
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ItemDto> subUsersItemById(Long userId, Long itemId) {
        User currentUser = this.posService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item currentItem = this.posService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()-1);
        this.posService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> updateOwnersProduct(Long userId, Long productId, ProductFieldsDto productFieldsDto) {
        User user = this.posService.findUserById(userId);
        Product product = this.posService.findProductById(productId);
        if (user == null || product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!product.getOwner().equals(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                product.setName(productFieldsDto.getName());
                if(productFieldsDto.getPrice() != null){
                    product.setPrice(productFieldsDto.getPrice());
                }
                if(productFieldsDto.getImage() != null){
                    product.setImage(productFieldsDto.getImage());
                }
                if(productFieldsDto.getQuantity() != null){
                    product.setQuantity(productFieldsDto.getQuantity());
                }
                this.posService.saveProduct(product);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<ItemDto> updateUsersItem(Long userId, Long itemId, ItemFieldsDto itemFieldsDto) {
        User user = this.posService.findUserById(userId);
        Item item = this.posService.findItemById(itemId);
        if (user == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getUser().equals(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                item.setQuantity(itemFieldsDto.getQuantity());
                this.posService.saveItem(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

}
