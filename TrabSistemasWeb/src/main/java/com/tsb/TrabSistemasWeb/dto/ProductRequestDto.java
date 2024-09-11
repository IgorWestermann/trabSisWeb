package com.tsb.TrabSistemasWeb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private String description;
    private String imgUrl;
    private Double price;
    private Set<Integer> categoryIds;
}
