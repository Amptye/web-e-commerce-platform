package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
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

    private PosDB posDB;
    private double tax = 0;
    private double discount = 0;
    private PosService posService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CategoryRepository categoryRepository;

    public PosServiceImp(UserRepository userRepository, ProductRepository productRepository,
                         CartRepository cartRepository, ItemRepository itemRepository,
                         CategoryRepository categoryRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
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
    public Cart findCartById(long id) {
        return cartRepository.findById(id).orElse(null);
    }
    @Override
    //@Transactional(readOnly = true)
    public Collection<Cart> findAllCarts() {
        return cartRepository.findAll();
    }
    @Override
    //@Transactional
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
    @Override
    //@Transactional
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
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
    //@Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    @Override
    //@Transactional
    public void saveUser(User user) {
        //userRepository.save(user);
        Cart cart = newCart();
        cart.setUser(user);
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
    }
    @Override
    //@Transactional(readOnly = true)
    public List<User> findUsersByName(String Name) {
        return userRepository.findByName(Name);
    }

    public Collection<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
    public Category findCategoryById(long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public List<Category> findCategoriesByName(String name){
        return categoryRepository.findByName(name);
    }
    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    @Override
    public void setTax(double tax){
        this.tax = tax;
    }
    @Override
    public double getTax() {
        return tax;
    }

    @Override
    public void setDiscount(double discount){
        this.discount = discount;
    }
    @Override
    public double getDiscount(){
        return discount;
    }

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {

        Cart cart = posDB.getCart();
        if (cart == null) {
            cart = this.newCart();
        }
        return cart;
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {
        cart.getItems().clear();
    }

    @Override
    public double total(Cart cart) {
        double t = 0;
        for(Item item : cart.getItems()){
            t += item.getQuantity() * item.getProduct().getPrice();
        }
        return t;
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(long productId, int amount) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        return this.getCart().addItem(new Item(this.getCart(), product, amount));
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }

    @Override
    public boolean combineItems(int a, int b){
        return this.getCart().combineItems(a-1, b-1);
    }

    @Override
    public boolean combineIdItems(long productId){
        Product product = posDB.getProduct(productId);
        if (product == null) return false;
        return this.getCart().combineIdItems(productId);
    }

    @Override
    public void combineAllItems(){
        this.getCart().combineAllItems();
    }

    @Override
    public boolean removeItem(int index){
        return this.getCart().removeItem(index);
    }

    @Override
    public boolean removeIdItems(long productId){
        Product product = posDB.getProduct(productId);
        if (product == null) return false;
        return this.getCart().removeIdItems(productId);
    }

    @Override
    public void clearCart(){
        this.getCart().clearItems();
    }

    @Override
    public void addItemQuanity(int index, int quantity){
        this.getCart().addItemQuanity(index, quantity);
    }
    @Override
    public void subItemQuanity(int index, int quantity){
        this.getCart().subItemQuanity(index, quantity);
    }

    @Override
    public List<Product> search_products(String search){
        if(search == null) return products();
        List<Product> products = products();
        List<Product> res = new ArrayList<>();
        Pattern s = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
        for (Product p : products) {
            Matcher m = s.matcher(p.getName());
            if (m.find()) {
                res.add(p);
            }
        }
        return res;
    }

    @Override
    public Product find_product_by_id(long id){
        Product res = null;
        List<Product> products = products();
        for (Product product : products) {
            if(product.getId() == (id)){
                res = product;
                break;
            }
        }
        return res;
    }
    @Override
    public Product find_product_by_index(int index){
        if(index < 0 || index > products().size()) return null;
        return products().get(index);
    }
}
