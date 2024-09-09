package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.Category;
import com.tsb.TrabSistemasWeb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.Create(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategory.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdCategory);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.FindAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.FindById(id);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        Category updatedCategory = categoryService.Update(id, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
