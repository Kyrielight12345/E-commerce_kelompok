package com.ecommerce.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart_product {
    private Integer id;
    private Cart cart;
    private Product product;
    private Integer quantity;
}
