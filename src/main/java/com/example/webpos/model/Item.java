package com.example.webpos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item(){

    }

    public Item(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice(){
        return this.getQuantity() * this.getProduct().getPrice();
    }

    @Override
    public String toString(){
        return product.toString() +"\t" + quantity;
    }

    public void combineWith(Item other){
        this.quantity += other.quantity;
    }

    public void addQuanity(int q){
        this.quantity += q;
    }
    public void subQuanity(int q){
        this.quantity -= q;
        if(this.quantity < 0) this.quantity = 0;
    }
}
