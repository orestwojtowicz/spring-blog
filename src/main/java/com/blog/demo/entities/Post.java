package com.blog.demo.entities;

import com.blog.demo.utills.BeanUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;



import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Lob
    private String postContent;

    // add post title
    @Column
    private String postTitle;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="user_entity_id")
    //@NotNull
    private User user;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @ToString.Exclude
    private Image image;

    @Column
    private LocalDate myDate;


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post")
    private Set<Comment> comments = new HashSet<>();

    public Post(String postContent, User user, Set<Comment> comments) {
        this.postContent = postContent;
        this.user = user;
        this.comments = comments;
    }

    public Post(String postContent, String postTitle) {
        this.postContent = postContent;
        this.postTitle = postTitle;
    }

}
