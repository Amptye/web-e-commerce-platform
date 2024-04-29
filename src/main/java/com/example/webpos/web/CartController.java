package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.mapper.CartMapper;
import com.example.webpos.mapper.ItemMapper;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.rest.api.CartsApi;
import org.springframework.samples.petclinic.rest.api.ItemsApi;
import org.springframework.samples.petclinic.rest.dto.CartDto;
import org.springframework.samples.petclinic.rest.dto.CartFieldsDto;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.samples.petclinic.rest.dto.ItemFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CartController implements CartsApi {

    private final PosService posService;

    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

    public CartController(PosService posService, CartMapper cartMapper, ItemMapper itemMapper) {
        this.posService = posService;
        this.cartMapper = cartMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public ResponseEntity<List<CartDto>> listCarts() {
        List<CartDto> carts = new ArrayList<>(cartMapper.toCartDtos(this.posService.findAllCarts()));
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> showCartById(Long cartId) {
        Cart cart = this.posService.findCartById(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartMapper.toCartDto(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItemToCart(Long cartId, ItemFieldsDto itemFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Cart cart = posService.findCartById(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = posService.findProductById(itemFieldsDto.getProductId());
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = itemMapper.toItem(itemFieldsDto);
        item.setProduct(product);
        item.setCart(cart);
        this.posService.saveItem(item);
        cart.addItem(item);
        this.posService.saveItem(item);
        ItemDto itemDto = itemMapper.toItemDto(item);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/carts/{cartId}")
                .buildAndExpand(cartId).toUri());
        return new ResponseEntity<>(itemDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemDto> getCartsItem(Long cartId, Long itemId) {
        Cart cart = this.posService.findCartById(cartId);
        Item item = this.posService.findItemById(itemId);
        if (cart == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getCart().equals(cart)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(itemMapper.toItemDto(item), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<List<ItemDto>> listCartsItems(Long cartId) {
        Cart cart = this.posService.findCartById(cartId);
        if (cart == null || cart.getItems().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ItemDto> items = itemMapper.toItemDtos(cart.getItems());
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Void> updateCartsItem(Long cartId, Long itemId, ItemFieldsDto itemFieldsDto) {
        Cart cart = this.posService.findCartById(cartId);
        Item item = this.posService.findItemById(itemId);
        if (cart == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getCart().equals(cart)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                item.setQuantity(itemFieldsDto.getQuantity());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<CartDto> addCart(CartFieldsDto cartFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Cart cart = cartMapper.toCart(cartFieldsDto);
        this.posService.saveCart(cart);
        CartDto cartDto = cartMapper.toCartDto(cart);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/carts/{id}")
                .buildAndExpand(cart.getId()).toUri());
        return new ResponseEntity<>(cartDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CartDto> deleteCart(Long cartId) {
        Cart cart = this.posService.findCartById(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.posService.deleteCart(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ItemDto> deleteCartsItem(Long cartId, Long itemId) {
        Cart cart = this.posService.findCartById(cartId);
        Item item = this.posService.findItemById(itemId);
        if (cart == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getCart().equals(cart)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                this.posService.deleteCart(cart);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<CartDto> clearCart(Long cartId) {
        Cart currentCart = this.posService.findCartById(cartId);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCart.getItems().clear();
        this.posService.saveCart(currentCart);
        return new ResponseEntity<>(cartMapper.toCartDto(currentCart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addCartsItemById(Long cartId, Long itemId) {
        Cart currentCart = this.posService.findCartById(cartId);
        if (currentCart == null) {
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
    public ResponseEntity<ItemDto> subCartsItemById(Long cartId, Long itemId) {
        Cart currentCart = this.posService.findCartById(cartId);
        if (currentCart == null) {
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
    public ResponseEntity<CartDto> addProductToCart(Long cartId, Long productId) {
        Cart currentCart = this.posService.findCartById(cartId);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = this.posService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item old_item = null;
        for(Item item : currentCart.getItems()){
            if(item.getProduct().getId() == productId){
                old_item = item;
                break;
            }
        }
        if(old_item == null){
            Item item = new Item(currentCart, product, 1);
            this.posService.saveItem(item);
            currentCart.addItem(item);
            this.posService.saveItem(item);
        } else {
            old_item.setQuantity(old_item.getQuantity()+1);
            this.posService.saveItem(old_item);
        }
        return new ResponseEntity<>(cartMapper.toCartDto(currentCart), HttpStatus.OK);
    }
}
