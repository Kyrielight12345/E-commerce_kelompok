package com.ecommerce.serverapp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {

    @Id
    @Column(name = "id_user", length = 5)
    private Integer id;

    @Column(nullable = false, unique = true, length = 15)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id_user")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Detail_User detailuser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_tr_user_role", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Product> products;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id_user")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Cart cart;
}