package com.tsb.TrabSistemasWeb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemRequest {
    private int productId;
    private Integer quantity;
}
