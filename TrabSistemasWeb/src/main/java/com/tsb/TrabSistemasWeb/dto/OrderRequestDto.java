package com.tsb.TrabSistemasWeb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestDto {
    private int clientId;
    private Set<OrderItemRequest> items;
}
