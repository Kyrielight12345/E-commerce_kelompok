package com.ecommerce.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private String image;
    private String desc;
    private Integer stock;
    private String category;
    private List<Cart_product> cart_products;
    private User user;
}
