package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class OrderServiceTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;


    @Test
    void create_order(){
        User user = new User(0, "testuser10@gmail.com", "password", "first", "last", "", AuthRestriction.USER);
        this.userRepository.save(user);
        Product product = new Product(0, 5, 10.42, "A new toy", "Image of cool toy", "Super Toy");
        Product product2 = new Product(0, 3, 20.00, "A new toy", "Image of cool toy", "Super Toy");
        this.productRepository.save(product);
        this.productRepository.save(product2);
        Order order = new Order(0, user.getId(), product.getId(), 3, 1000000000l);
        Order order2 = new Order(0, user.getId(), product2.getId(), 1, 1000000000l);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        this.orderService.addOrders(orderList);
        Assertions.assertNotEquals(0,order.getId());
        Assertions.assertNotEquals(0,order2.getId());

    }

    @Test
    void find_orders_by_user_id(){
        User user = new User(0, "testuser21@gmail.com", "password", "first", "last", "", AuthRestriction.USER);
        this.userRepository.save(user);
        Product product = new Product(0, 5, 10.42, "A new toy", "Image of cool toy", "Super Toy");
        Product product2 = new Product(0, 3, 20.00, "A new toy", "Image of cool toy", "Super Toy");
        this.productRepository.save(product);
        this.productRepository.save(product2);
        Order order = new Order(0, user.getId(), product.getId(), 3, 1000000000l);
        Order order2 = new Order(0, user.getId(), product2.getId(), 1, 1000000000l);
        this.orderRepository.save(order);
        this.orderRepository.save(order2);

        Assertions.assertEquals(2,orderService.findAllOrderByUserId(user.getId()).size());
    }
}
