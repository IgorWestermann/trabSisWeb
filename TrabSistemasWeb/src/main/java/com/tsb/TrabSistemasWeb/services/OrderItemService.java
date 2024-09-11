package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.OrderItem;
import com.tsb.TrabSistemasWeb.repository.OrderItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> FindAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem FindById(int id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found with id: " + id));
    }

    public void Create(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public void Create(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }

    public void Delete(int id) {
        OrderItem orderItem = FindById(id);
        orderItemRepository.delete(orderItem);
    }
}
