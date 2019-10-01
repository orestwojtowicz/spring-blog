package com.blog.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="comment")
@ToString
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String commentContent;

    // many comments can have one user
    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    @ToString.Exclude
    private User user;

    // many comments can have one post
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(@NotNull String commentContent) {
        this.commentContent = commentContent;
    }
}
