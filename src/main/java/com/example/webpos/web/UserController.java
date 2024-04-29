package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.mapper.UserMapper;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.rest.api.UsersApi;
import org.springframework.samples.petclinic.rest.dto.TaxFieldsDto;
import org.springframework.samples.petclinic.rest.dto.UserDto;
import org.springframework.samples.petclinic.rest.dto.UserFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class UserController implements UsersApi {

    private final PosService posService;

    private final UserMapper userMapper;

    public UserController(PosService posService, UserMapper userMapper) {
        this.posService = posService;
        this.userMapper = userMapper;
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
        User user = userMapper.toUser(userFieldsDto);
        user.setTax(false);
        user.setPercentage(0);
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
        currentUser.setName(userFieldsDto.getName());
        if(userFieldsDto.getPass() != null) {
            currentUser.setPass(userFieldsDto.getPass());
        }
        if(userFieldsDto.getEmail() != null) {
            currentUser.setEmail(userFieldsDto.getEmail());
        }
        if(userFieldsDto.getMoney() != null) {
            currentUser.setMoney(userFieldsDto.getMoney());
        }
        if(userFieldsDto.getAddress1() != null){
            currentUser.setAddress1(userFieldsDto.getAddress1());
        }
        if(userFieldsDto.getAddress2() != null){
            currentUser.setAddress2(userFieldsDto.getAddress2());
        }
        if(userFieldsDto.getContact() != null){
            currentUser.setContact(userFieldsDto.getContact());
        }
        if(userFieldsDto.getFooter() != null){
            currentUser.setFooter(userFieldsDto.getFooter());
        }
        if(userFieldsDto.getImage() != null){
            currentUser.setFooter(userFieldsDto.getFooter());
        }
        if(userFieldsDto.getSymbol() != null){
            currentUser.setSymbol(userFieldsDto.getSymbol());
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
    public ResponseEntity<UserDto> chargeUserById(Long userId) {
        User user = this.posService.findUserById(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double total = posService.total(posService.getCart()) * (1- posService.getDiscount()) * (1+ posService.getTax());
        if(total <= user.getMoney()) {
            posService.checkout(posService.getCart());
            user.subMoney(total);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        posService.saveUser(user);
        return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> taxUpdateUser(Long userId, TaxFieldsDto taxFieldsDto) {
        User currentUser = this.posService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setTax(taxFieldsDto.getTax());
        if(taxFieldsDto.getPercentage() != null){
            currentUser.setPercentage(taxFieldsDto.getPercentage());
        }
        this.posService.saveUser(currentUser);
        return new ResponseEntity<>(userMapper.toUserDto(currentUser), HttpStatus.OK);
    }
}
