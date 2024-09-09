package com.tsb.TrabSistemasWeb.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    @Getter
    @Setter
    private Integer quantity;
    @Setter
    @Getter
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return price * quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

}
