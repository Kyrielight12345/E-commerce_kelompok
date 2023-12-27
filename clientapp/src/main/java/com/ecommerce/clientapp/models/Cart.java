package com.ecommerce.clientapp.models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;
    private LocalDateTime createdTime;
    private List<Cart_product> cart_products;
    private User user;
}
