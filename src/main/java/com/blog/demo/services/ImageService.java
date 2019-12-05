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

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
            this.imageRepository = imageRepository;
    }


    public int saveImage(Image image) {
        try {
            imageRepository.save(image);
            return 1;
        } catch (Exception e) {
            log.info("error while saving image " + e.getMessage());
            return 0;
        }
    }
    public Image getImage(Long id) {
        Optional<Image> findById = imageRepository.findById(id);
        if (findById.isPresent()) {
            Image getImageDetails =  findById.get();
            return getImageDetails;
        } else {

            return null;
        }
    }
}
