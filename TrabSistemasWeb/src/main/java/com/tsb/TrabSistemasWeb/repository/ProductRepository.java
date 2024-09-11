package com.tsb.TrabSistemasWeb.repository;

import com.tsb.TrabSistemasWeb.domain.entities.Category;
import com.tsb.TrabSistemasWeb.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int id);
    List<Product> findByNameContaining(String name);
    Optional<Product> findByName(String name);
}
