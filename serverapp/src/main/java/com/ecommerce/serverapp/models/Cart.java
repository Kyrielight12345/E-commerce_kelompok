package com.ecommerce.serverapp.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart", length = 5)
    private Integer id;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "cart")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Cart_product> cart_products;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;
}
