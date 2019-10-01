package com.blog.demo.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by damiass on Sep, 2019
 */
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;


    public Role(@NotNull String name) {
        this.name = name;

    }


}
