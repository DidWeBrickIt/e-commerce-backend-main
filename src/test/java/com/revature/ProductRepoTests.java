package com.revature;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ProductRepoTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void create_product(){
        Product toy = new Product(0, 5, 10.42, "A new toy", "Image of cool toy", "Super Toy");
        this.productRepository.save(toy);
        Assertions.assertEquals("Super Toy", toy.getName());
    }

    @Test
    void get_all_products(){
        List<Product> products = this.productRepository.findAll();
        Assertions.assertNotEquals(0, products.size());
    }

    @Test
    void get_product_by_id(){
        Product toy = new Product(0, 5, 10.42, "A new toy", "Image of cool toy", "Super Toy");
        this.productRepository.save(toy);
        Product savedToy = this.productRepository.getById(toy.getId());
        Assertions.assertEquals("Super Toy", savedToy.getName());
    }

    @Test
    void delete_product(){
        Product toy = new Product(0, 5, 10.42, "A new toy", "Image of cool toy", "Super Toy");
        this.productRepository.save(toy);
        int toyId = toy.getId();
        this.productRepository.deleteById(toyId);
        Assertions.assertThrows(RuntimeException.class, () -> this.productRepository.getById(toyId));
    }
}
