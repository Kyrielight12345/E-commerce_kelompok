package com.ecommerce.serverapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_detail_user")
public class Detail_User {

    @Id
    @Column(name = "id_user", length = 5)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 25)
    private String address;

    @Column(nullable = false, unique = true, length = 25)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    @OneToOne(mappedBy = "detailuser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

}
