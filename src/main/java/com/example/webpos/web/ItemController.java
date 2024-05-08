package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.mapper.ItemMapper;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.rest.api.ItemsApi;
import org.springframework.samples.petclinic.rest.dto.ItemDto;
import org.springframework.samples.petclinic.rest.dto.ItemFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class ItemController implements ItemsApi {

    private final PosService posService;

    private final ItemMapper itemMapper;

    public ItemController(PosService posService, ItemMapper itemMapper) {
        this.posService = posService;
        this.itemMapper = itemMapper;
    }

    @Override
    public ResponseEntity<List<ItemDto>> listItems() {
        List<ItemDto> items = new ArrayList<>(itemMapper.toItemDtos(this.posService.findAllItems()));
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> showItemById(Long itemId) {
        Item item = this.posService.findItemById(itemId);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemMapper.toItemDto(item), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItem(ItemFieldsDto itemFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Product product = posService.findProductById(itemFieldsDto.getProductId());
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = itemMapper.toItem(itemFieldsDto);
        item.setProduct(product);
        this.posService.saveItem(item);
        ItemDto itemDto = itemMapper.toItemDto(item);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/items/{id}")
                .buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<>(itemDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemDto> deleteItem(Long itemId) {
        Item item = this.posService.findItemById(itemId);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.posService.deleteItem(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ItemDto> updateItem(Long itemId, ItemFieldsDto itemFieldsDto) {
        Item currentItem = this.posService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(itemFieldsDto.getQuantity());
        this.posService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItemById(Long itemId) {
        Item currentItem = this.posService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()+1);
        this.posService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> subItemById(Long itemId) {
        Item currentItem = this.posService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()-1);
        this.posService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }


}
