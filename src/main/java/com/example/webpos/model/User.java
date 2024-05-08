package com.example.webpos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    static final long UID_BASE = 1_999_999;
    static public long getUidById(long id){
        return id + UID_BASE;
    }
    static public long getIdByUid(long uid){
        return uid - UID_BASE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long uid;

    //@NotBlank(message = "Name is mandatory")
    private String name;

    //@NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$")
    private String email;

    //@NotBlank(message = "Password is mandatory")
    private String pass;

    private double money = 0;
    @Pattern(regexp = "^\\d{0,20}$", message = "必须是 1 到 20 位的数字")
    private String address = "";
    @Pattern(regexp = "^\\d{0,20}$", message = "必须是 1 到 20 位的数字")
    private String contact = "";
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public User(String name, String address, String contact, String image) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        this.uid = id + UID_BASE;
    }

    public long getUid() {
        this.setUid();
        return uid;
    }

    public void setUid() {
        this.uid = id + UID_BASE;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product){
        return products.add(product);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addMoney(double money){
        this.money += money;
    }

    public void subMoney(double money){
        this.money -= money;
    }

    public void clearItems(){
        this.items.clear();
    }

    public boolean deleteItem(Item item){
        return items.remove(item);
    }

    public boolean deleteProduct(Product product){
        return products.remove(product);
    }

    public boolean charge(){
        double total = 0;
        for(Item item : items){
            total += item.getPrice();
        }
        if(total <= this.money){
            this.money -= total;
            this.items.clear();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }

    // standard constructors / setters / getters / toString
}
