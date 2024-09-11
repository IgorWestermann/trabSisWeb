package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.*;
import com.tsb.TrabSistemasWeb.domain.enums.OrderStatus;
import com.tsb.TrabSistemasWeb.dto.OrderItemRequest;
import com.tsb.TrabSistemasWeb.dto.OrderRequestDto;
import com.tsb.TrabSistemasWeb.repository.OrderItemRepository;
import com.tsb.TrabSistemasWeb.repository.OrderRepository;
import com.tsb.TrabSistemasWeb.repository.ProductRepository;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;

    public List<Order> Get() {
        return orderRepository.findAll();
    }

    public Order GetById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public Order createOrder(OrderRequestDto orderRequest) {
        User client = userService.GetById(orderRequest.getClientId());

        Order order = new Order();
        order.setClient(client);
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        Set<OrderItem> items = new HashSet<>();

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            Product product = productService.GetById(itemRequest.getProductId());
            OrderItem orderItem = new OrderItem(order, product, itemRequest.getQuantity(), product.getPrice());
            items.add(orderItem);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(items);
        return order;
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
