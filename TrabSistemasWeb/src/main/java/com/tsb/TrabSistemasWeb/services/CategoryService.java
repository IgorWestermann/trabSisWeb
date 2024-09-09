package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.Category;
import com.tsb.TrabSistemasWeb.repository.CategoryRepository;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> Get() {
        return categoryRepository.findAll();
    }

    public Category GetById(int id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.get();
    }

}
