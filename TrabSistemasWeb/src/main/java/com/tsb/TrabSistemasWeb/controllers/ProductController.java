package com.tsb.TrabSistemasWeb.controllers;

import com.tsb.TrabSistemasWeb.domain.entities.Product;
import com.tsb.TrabSistemasWeb.dto.ProductRequestDto;
import com.tsb.TrabSistemasWeb.dto.ProductResponseDto;
import com.tsb.TrabSistemasWeb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<String> GetProduct() {
        return ResponseEntity.ok("Success!");
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Product>> GetProducts() {
        List<Product> products = productService.Get();
        if (products != null) {
            return ResponseEntity.ok().body(products);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product product = productService.GetById(id);
        if (product != null) {
            return ResponseEntity.ok().body(product);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> Update(@PathVariable Integer id, @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto updatedProductDto = productService.Update(id, productRequestDto);
        return ResponseEntity.ok().body(updatedProductDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Integer id) {
        productService.Delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> FindProductByName(@RequestBody String name) {
        List<ProductResponseDto> products = productService.FindByName(name);
        return ResponseEntity.ok().body(products);
    }
}
