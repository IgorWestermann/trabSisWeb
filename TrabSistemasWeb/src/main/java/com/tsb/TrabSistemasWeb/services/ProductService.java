package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.Category;
import com.tsb.TrabSistemasWeb.domain.entities.Product;
import com.tsb.TrabSistemasWeb.dto.CategoryDto;
import com.tsb.TrabSistemasWeb.dto.ProductRequestDto;
import com.tsb.TrabSistemasWeb.dto.ProductResponseDto;
import com.tsb.TrabSistemasWeb.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> Get() {
        return productRepository.findAll();
    }

    public Product GetById(int id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.get();
    }

    public ProductResponseDto Create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImgUrl(productRequestDto.getImgUrl());

        Set<Category> categories = productRequestDto.getCategoryIds().stream()
                .map(categoryService::FindById)
                .collect(Collectors.toSet());
        product.setCategories(categories);

        Product savedProduct = productRepository.save(product);

        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(savedProduct.getId());
        responseDto.setName(savedProduct.getName());
        responseDto.setDescription(savedProduct.getDescription());
        responseDto.setPrice(savedProduct.getPrice());
        responseDto.setImgUrl(savedProduct.getImgUrl());
        responseDto.setCategories(savedProduct.getCategories().stream()
                .map(category -> new CategoryDto(category.getId(), category.getName()))
                .collect(Collectors.toSet()));

        return responseDto;
    }

    public ProductResponseDto Update(Integer id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImgUrl(productRequestDto.getImgUrl());

        Product updatedProduct = productRepository.save(product);

        return convertToDto(updatedProduct);
    }
    public void Delete(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
    public List<ProductResponseDto> FindByName(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductResponseDto convertToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImgUrl(product.getImgUrl());
        return dto;
    }

}
