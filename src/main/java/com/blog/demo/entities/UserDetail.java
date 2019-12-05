package com.blog.demo.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String aboutUser;

    @ToString.Exclude
    @OneToOne(mappedBy = "userDetail")
    private User user;

    public UserDetail(String aboutUser) {
        this.aboutUser = aboutUser;
    }
}
