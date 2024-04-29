package com.example.webpos.repo;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Category;
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
            Category category = new Category("drink");
            posService.saveCategory(category);
            posService.saveProduct(new Product("cola",3,"Cola.jpg",category,16,true));
            posService.saveProduct(new Product("sprite",4,"Sprite.png",category,12,true));
            posService.saveProduct(new Product("red bull",5,"Redbull.jpg",category,4,true));
            posService.saveProduct(new Product("Milk",5,"Milk.jpg",category,1,true));
            posService.saveUser(new User("123","10086","10087","15968774896",true,10,"$","",""));
        };
    }
}