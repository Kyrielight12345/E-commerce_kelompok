package com.ecommerce.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private Integer id_user;
    private String name;
    private String address;
    private String email;
    private String phone;

}