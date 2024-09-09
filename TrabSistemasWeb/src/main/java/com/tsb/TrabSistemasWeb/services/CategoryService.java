package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.Category;
import com.tsb.TrabSistemasWeb.repository.CategoryRepository;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> FindAll() {
        return categoryRepository.findAll();
    }

    public Category FindById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    public Category Create(Category category) {
        return categoryRepository.save(category);
    }

    public Category Update(int id, Category category) {
        Category existingCategory = FindById(id);
        existingCategory.setName(category.getName());

        return categoryRepository.save(existingCategory);
    }

    public void Delete(int id) {
        Category category = FindById(id);
        categoryRepository.delete(category);
    }
}
