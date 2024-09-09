package com.tsb.TrabSistemasWeb.repository;

import com.tsb.TrabSistemasWeb.domain.entities.Order;
import com.tsb.TrabSistemasWeb.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int id);
}
