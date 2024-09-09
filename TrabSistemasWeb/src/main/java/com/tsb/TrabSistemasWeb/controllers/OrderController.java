package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.Order;
import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.services.OrderService;
import com.tsb.TrabSistemasWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<String> GetOrder() {
        return ResponseEntity.ok("Success!");
    }

    @GetMapping(value = "list")
    public ResponseEntity<List<Order>> GetOrders() {
        List<Order> orders = orderService.Get();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> FindById(@PathVariable Integer id) {
        Order order = orderService.GetById(id);
        return ResponseEntity.ok().body(order);
    }

}
