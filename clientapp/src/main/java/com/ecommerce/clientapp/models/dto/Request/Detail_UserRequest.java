package com.ecommerce.clientapp.models.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail_UserRequest {

    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Integer id_user;

}
