package com.ecommerce.serverapp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", length = 5)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "image", nullable = false, length = 10000000)
    private String image;

    @Column(name = "description", nullable = false, length = 10000000)
    private String desc;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "category", nullable = false, length = 25)
    private String category;

    @OneToMany(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Cart_product> cart_products;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
