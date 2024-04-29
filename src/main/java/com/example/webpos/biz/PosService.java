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
    public Cart findCartById(long id);
    public Collection<Cart> findAllCarts();
    public void saveCart(Cart cart);
    public void deleteCart(Cart cart);
    public Collection<User> findAllUsers();
    public User findUserById(long id);
    public void deleteUser(User user);
    public void saveUser(User user);
    public List<User> findUsersByName(String Name);
    public Collection<Category> findAllCategories();
    public Category findCategoryById(long id);
    public List<Category> findCategoriesByName(String name);
    public void deleteCategory(Category category);
    public void saveCategory(Category category);

    public void setTax(double tax);
    public double getTax();

    public void setDiscount(double discount);
    public double getDiscount();

    public Cart getCart();

    public Cart newCart();

    public void checkout(Cart cart);

    public double total(Cart cart);

    public boolean add(Product product, int amount);

    public boolean add(long productId, int amount);

    public List<Product> products();

    public boolean combineItems(int a, int b);

    public boolean combineIdItems(long productId);

    public void combineAllItems();

    public boolean removeItem(int index);

    public boolean removeIdItems(long productId);

    public void clearCart();

    public void addItemQuanity(int index, int quantity);
    public void subItemQuanity(int index, int quantity);

    public List<Product> search_products(String search);

    public Product find_product_by_id(long id);
    public Product find_product_by_index(int index);
}
