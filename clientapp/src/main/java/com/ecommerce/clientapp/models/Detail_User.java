package com.ecommerce.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail_User {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private User user;
    private Integer id_user;
}
