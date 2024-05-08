package com.example.webpos.biz;

import com.example.webpos.model.*;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

public interface PosService {

    //@Transactional(readOnly = true)
    public Collection<Product> findAllProducts();
    public Product findProductById(long id);
    public List<Product> findProductsByName(String name);
    public void deleteProduct(Product product);
    public void saveProduct(Product product);
    public Item findItemById(long itemId);
    public Collection<Item> findAllItems();
    public void deleteItem(Item item);
    public List<Item> findItemsByProductId(long productId);
    public void saveItem(Item item);
    public Collection<User> findAllUsers();
    public User findUserById(long id);
    public User findUserByUid(long uid);
    public void deleteUser(User user);
    public void saveUser(User user);
    public List<User> findUsersByName(String Name);
}
