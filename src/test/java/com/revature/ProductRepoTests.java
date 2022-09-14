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
public class ProductRepoTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Disabled
    void create_product(){
        Product toy = new Product(0, 5, 10.41, "This is a toy", "Cool image of a toy", "Super Toy");
        this.productRepository.save(toy);
        Assertions.assertEquals("Super Toy", toy.getName());
    }

    @Test
    @Disabled
    void get_products(){
        List<Product> products = this.productRepository.findAll();
        Assertions.assertNotEquals(0, products.size());
    }

    @Test
    void get_product_by_id(){
        Product product = this.productRepository.getById(1);
        Assertions.assertEquals("Headphones", product.getName());
    }

    @Test
    @Disabled
    void delete_product(){
        Product toy = new Product(0, 5, 10.41, "This is a toy", "Cool image of a toy", "Super Toy");
        int id = toy.getId();
        this.productRepository.deleteById(id);
        Assertions.assertThrows(RuntimeException.class, () -> this.productRepository.findById(id));
    }
}
