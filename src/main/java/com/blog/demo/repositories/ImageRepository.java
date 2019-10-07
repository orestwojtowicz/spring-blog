package com.blog.demo.repositories;

import com.blog.demo.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by damiass on Oct, 2019
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
