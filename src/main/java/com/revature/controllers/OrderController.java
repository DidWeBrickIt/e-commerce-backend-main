package com.revature.controllers;

import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.models.Order;
import com.revature.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @Authorized(authorities = {AuthRestriction.USER})
    @PostMapping
    public ResponseEntity<List<Order>> order(@RequestBody List<Order> orderList){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrders(orderList));
    }


    @Authorized(authorities = {AuthRestriction.USER})
    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestHeader("auth") String jwt, @PathVariable("id") int id){
        return ResponseEntity.ok(orderService.findAllOrderByUserId(id));
    }

}
