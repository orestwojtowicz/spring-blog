package com.blog.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by damiass on Oct, 2019
 */
@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Lob // for storing large text objects
    @Column(name = "Image")
    private byte[] image; // store image as byte array



    public Image() {

    }
    public Image(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }


}
