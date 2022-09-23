package com.revature.controllers;

import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Authorized(authorities = {AuthRestriction.ADMIN})
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestHeader("auth") String jwt, @RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @Authorized(authorities = {AuthRestriction.USER, AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @GetMapping
    public ResponseEntity<List<Product>> getInventory(@RequestHeader("auth") String jwt) {
        return ResponseEntity.ok(productService.findAll());
    }

    @Authorized(authorities = {AuthRestriction.USER, AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@RequestHeader("auth") String jwt, @PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @Authorized(authorities = {AuthRestriction.USER , AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @PatchMapping
    public ResponseEntity<List<Product>> purchase(@RequestHeader("auth") String jwt, @RequestBody List<ProductInfo> metadata) {
    	List<Product> productList = new ArrayList<Product>();
    	
    	for (int i = 0; i < metadata.size(); i++) {
    		Optional<Product> optional = productService.findById(metadata.get(i).getId());

    		if(!optional.isPresent()) {
    			return ResponseEntity.notFound().build();
    		}

    		Product product = optional.get();

    		if(product.getQuantity() - metadata.get(i).getQuantity() < 0) {
    			return ResponseEntity.badRequest().build();
    		}
    		
    		product.setQuantity(product.getQuantity() - metadata.get(i).getQuantity());
    		productList.add(product);
    	}
        
        productService.saveAll(productList, metadata);

        return ResponseEntity.ok(productList);
    }

    @Authorized(authorities = {AuthRestriction.ADMIN })
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@RequestHeader("auth") String jwt, @PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);

        return ResponseEntity.ok(optional.get());
    }
}
