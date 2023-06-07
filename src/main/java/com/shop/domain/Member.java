package com.shop.domain;

import com.shop.dto.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "member")
public class Member {
    @Id
    @Column(name ="member_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Cart> cartList = new ArrayList<>();
    public void craeteMember(String ... member){
        this.memberId = member[0];
        this.name = member[1];
        this.password = member[2];
        this.role = Role.USER;
    }
}
