package com.blog.demo.repositories;

import com.blog.demo.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by damiass on Oct, 2019
 */
public interface ImageRepository extends JpaRepository<Image, Long> {


    List<Image> findAll();

}
