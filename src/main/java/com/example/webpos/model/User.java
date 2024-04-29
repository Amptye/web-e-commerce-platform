package com.example.webpos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    //@NotBlank(message = "Email is mandatory")
    private String email;

    //@NotBlank(message = "Password is mandatory")
    private String pass;

    private double money = 0;
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Pattern(regexp = "^\\d{0,20}$", message = "必须是 1 到 20 位的数字")
    private String address1 = "";
    @Pattern(regexp = "^\\d{0,20}$", message = "必须是 1 到 20 位的数字")
    private String address2 = "";
    @Pattern(regexp = "^\\d{0,20}$", message = "必须是 1 到 20 位的数字")
    private String contact = "";
    private boolean tax;
    private int percentage;
    private String symbol;
    private String footer;
    private String image;

    public User() {
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public User(String name, String address1, String address2, String contact, boolean tax, int percentage, String symbol, String footer, String image) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.contact = contact;
        this.tax = tax;
        this.percentage = percentage;
        this.symbol = symbol;
        this.footer = footer;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isTax() {
        return tax;
    }

    public void setTax(boolean tax) {
        this.tax = tax;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }

    // standard constructors / setters / getters / toString
}
