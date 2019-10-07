package com.blog.demo.services;

import com.blog.demo.entities.Image;
import com.blog.demo.repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by damiass on Oct, 2019
 */
@Service
@Slf4j
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public int saveImage(Image model) {
        try {
            imageRepository.save(model);
            return 1;
        } catch (Exception e) {
            log.info("BLAD W SERWISIE " + e.getMessage());
            return 0;
        }
    }
    public Image getImages(Long id) {
        Optional findById = imageRepository.findById(id);
        if (findById.isPresent()) {
            Image getImageDetails = (Image) findById.get();

            return getImageDetails;
        } else {
            return null;
        }
    }
}
