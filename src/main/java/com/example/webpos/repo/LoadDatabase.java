package com.example.webpos.repo;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Product;
import com.example.webpos.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private final PosService posService;

    public LoadDatabase(PosService posService){
        this.posService = posService;
    }

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            User user = new User("admin","admin123","10086","15968774896","");
            posService.saveUser(user);
            posService.saveProduct(new Product("cola",3,"Cola.jpg",16,user));
            posService.saveProduct(new Product("sprite",4,"Sprite.png",12,user));
            posService.saveProduct(new Product("red bull",5,"Redbull.jpg",4,user));
            posService.saveProduct(new Product("Milk",5,"Milk.jpg",1,user));
        };
    }
}