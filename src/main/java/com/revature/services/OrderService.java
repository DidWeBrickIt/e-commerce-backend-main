package com.revature.services;

import com.revature.models.Order;
import com.revature.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> addOrders(List<Order> orderList){return this.orderRepository.saveAll(orderList);}
    public List<Order> findAllOrderByUserId(int id){
        return this.orderRepository.findByUserId(id);
    }
}
