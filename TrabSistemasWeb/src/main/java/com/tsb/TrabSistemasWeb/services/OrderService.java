package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.Order;
import com.tsb.TrabSistemasWeb.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> Get() {
        return orderRepository.findAll();
    }

    public Order GetById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public Order Create(Order order) {
        return orderRepository.save(order);
    }

    public Order Update(Integer id, Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        existingOrder.setMoment(order.getMoment());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setClient(order.getClient());
        existingOrder.setItems(order.getItems());
        existingOrder.setPayment(order.getPayment());

        return orderRepository.save(existingOrder);
    }

    public void Delete(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

}
