package com.example.webpos.biz;

import com.example.webpos.model.*;
import com.example.webpos.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.Set;

@Service
@Component
public class PosServiceImp implements PosService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public PosServiceImp(UserRepository userRepository, ProductRepository productRepository,
                         ItemRepository itemRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    //@Transactional(readOnly = true)
    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product findProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findProductsByName(String name){
        return productRepository.findByName(name);
    }
    @Override
    //@Transactional
    public void deleteProduct(Product product) {
        List<Item> items = (List<Item>) findAllItems();
        for (Item item : items){
            if(item.getProduct().equals(product)){
                this.deleteItem(item);
            }
        }
        User user = product.getOwner();
        user.deleteProduct(product);
        productRepository.delete(product);
    }
    @Override
    //@Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    //@Transactional(readOnly = true)
    public Item findItemById(long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }
    @Override
    //@Transactional(readOnly = true)
    public Collection<Item> findAllItems() {
        return itemRepository.findAll();
    }
    @Override
    //@Transactional
    public void deleteItem(Item item) {
        User user = item.getUser();
        user.deleteItem(item);
        itemRepository.delete(item);
    }
    @Override
    //@Transactional(readOnly = true)
    public List<Item> findItemsByProductId(long productId) {
        return itemRepository.findByProductId(productId);
    }
    @Override
    //@Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    @Override
    //@Transactional(readOnly = true)
    public Collection<User> findAllUsers() {
        return userRepository.findAll();
    }
    @Override
    //@Transactional(readOnly = true)
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User findUserByUid(long uid){
        return findUserById(User.getIdByUid(uid));
    }
    @Override
    //@Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    @Override
    //@Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Override
    //@Transactional(readOnly = true)
    public List<User> findUsersByName(String Name) {
        return userRepository.findByName(Name);
    }

}
