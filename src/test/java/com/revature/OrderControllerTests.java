package com.revature;

import com.revature.controllers.OrderController;
import com.revature.models.Order;
import com.revature.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class OrderControllerTests {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderServiceMock;

    @Test
    void order_test(){
        List<Order> orders = new ArrayList<>();
        when(orderServiceMock.addOrders(orders)).thenReturn(orders);

        ResponseEntity<List<Order>> re = orderController.order(orders);
        Assertions.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void get_order_by_id_test(){
        List<Order> orders = new ArrayList<>();
        when(orderServiceMock.findAllOrderByUserId(1)).thenReturn(orders);

        ResponseEntity<List<Order>> re = orderController.getOrdersByUserId("jwt", 1);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
    }
}
