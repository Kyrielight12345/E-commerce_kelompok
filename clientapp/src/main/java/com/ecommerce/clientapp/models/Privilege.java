package com.ecommerce.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {
    private Integer id;
    private String privilege_name;
    private List<Role> roles;
}
