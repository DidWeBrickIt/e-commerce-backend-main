package com.revature;

import com.revature.controllers.ProductController;
import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTests {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productServiceMock;

    @Test
    void get_inventory_test(){
        List<Product> products = new ArrayList<>();
        when(productServiceMock.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> re = productController.getInventory("jwt");
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void get_product_by_id_test(){
        Product p = new Product();
        when(productServiceMock.findById(1)).thenReturn(Optional.of(p));

        ResponseEntity<Product> re = productController.getProductById("jwt", 1);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void get_product_not_found_test(){
        Product p = new Product();
        when(productServiceMock.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Product> re = productController.getProductById("jwt", 1);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void upsert_test(){
        Product p = new Product();
        when(productServiceMock.save(p)).thenReturn(p);
        ResponseEntity<Product> re = productController.createProduct("jwt", p);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void purchase_works_test(){
        ProductInfo pi = new ProductInfo(1,1);
        Product p = new Product(1,2,1.00,"desc", "img", "name");
        List<ProductInfo> metaMock = new ArrayList<>();
        metaMock.add(pi);
        when(productServiceMock.findById(1)).thenReturn(Optional.of(p));

        ResponseEntity<List<Product>> re = productController.purchase("jwt", metaMock);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
        Assertions.assertEquals(1,p.getQuantity());
    }
    @Test
    void purchase_id_not_found_test(){
        ProductInfo pi = new ProductInfo(1,1);
        Product p = new Product(1,2,1.00,"desc", "img", "name");
        List<ProductInfo> metaMock = new ArrayList<>();
        metaMock.add(pi);
        when(productServiceMock.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<List<Product>> re = productController.purchase("jwt", metaMock);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
    @Test
    void purchase_quantity_not_available_test(){
        ProductInfo pi = new ProductInfo(1,3);
        Product p = new Product(1,2,1.00,"desc", "img", "name");
        List<ProductInfo> metaMock = new ArrayList<>();
        metaMock.add(pi);
        when(productServiceMock.findById(1)).thenReturn(Optional.of(p));

        ResponseEntity<List<Product>> re = productController.purchase("jwt", metaMock);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }

    @Test
    void delete_works_test(){
        Product p = new Product();
        when(productServiceMock.findById(0)).thenReturn(Optional.of(p));

        ResponseEntity<Product> re = productController.deleteProduct("jwt", 0);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
    }
    @Test
    void delete_not_found_test(){
        Product p = new Product();
        when(productServiceMock.findById(0)).thenReturn(Optional.empty());

        ResponseEntity<Product> re = productController.deleteProduct("jwt", 0);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }


}
