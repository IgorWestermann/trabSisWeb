package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.Order;
import com.tsb.TrabSistemasWeb.domain.entities.OrderItem;
import com.tsb.TrabSistemasWeb.domain.entities.Product;
import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.dto.OrderItemRequest;
import com.tsb.TrabSistemasWeb.dto.OrderRequestDto;
import com.tsb.TrabSistemasWeb.services.OrderService;
import com.tsb.TrabSistemasWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping
    public ResponseEntity<Order> Create(@RequestBody OrderRequestDto orderRequest) {
        Order newOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.ok().body(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> Update(@PathVariable Integer id, @RequestBody Order order) {
        Order updatedOrder = orderService.Update(id, order);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Integer id) {
        orderService.Delete(id);
        return ResponseEntity.noContent().build();
    }

}
