package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.Product;
import com.tsb.TrabSistemasWeb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> Get() {
        return productRepository.findAll();
    }

    public Product GetById(int id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.get();
    }

}
