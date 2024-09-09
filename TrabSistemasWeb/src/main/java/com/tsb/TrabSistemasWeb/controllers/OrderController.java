package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.Order;
import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.services.OrderService;
import com.tsb.TrabSistemasWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.Create(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        Order updatedOrder = orderService.Update(id, order);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.Delete(id);
        return ResponseEntity.noContent().build();
    }

}
