package com.ecommerce.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String Username;
    private String password;
    private Detail_User detailuser;
    private List<Role> roles;
    private List<Product> products;
    private Cart cart;
    private Integer id_user;

}
