package com.example.webpos.db;

import com.example.webpos.model.Cart;
import com.example.webpos.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PosInMemoryDB implements PosDB {
    private List<Product> products = new ArrayList<>();

    private Cart cart;

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Cart saveCart(Cart cart) {
        this.cart = cart;
        return this.cart;
    }

    @Override
    public Cart getCart() {
        return this.cart;
    }

    @Override
    public Product getProduct(long productId) {
        for (Product p : getProducts()) {
            if (p.getId() == (productId)) {
                return p;
            }
        }
        return null;
    }

    private PosInMemoryDB() {
        this.products.add(new Product("iPhone 13", 8999, "1.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "2.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "3.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "4.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "5.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "6.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "7.jpg"));
        this.products.add(new Product("MacBook Pro", 29499, "comp.png"));

    }

}
