package com.tsb.TrabSistemasWeb.repository;

import com.tsb.TrabSistemasWeb.domain.entities.OrderItem;
import com.tsb.TrabSistemasWeb.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    Optional<OrderItem> findById(int id);
}
