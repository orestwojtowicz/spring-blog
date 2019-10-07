package com.blog.demo.entities;

import javax.persistence.*;

/**
 * Created by damiass on Oct, 2019
 */
@Entity
@Table(name = "image")
public class Image {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Name")
    private String name;

    @Lob
    @Column(name = "Image")
    private byte[] image;

    public Image() {

    }
    public Image(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }





}
