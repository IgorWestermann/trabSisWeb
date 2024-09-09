package com.tsb.TrabSistemasWeb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductResponseDto {
        private int id;
        private String name;
        private String description;
        private String imgUrl;
        private Double price;
        private Set<CategoryDto> categories;
}

