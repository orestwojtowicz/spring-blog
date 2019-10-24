package com.blog.demo.services;

import com.blog.demo.entities.Image;
import com.blog.demo.entities.User;
import com.blog.demo.repositories.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by damiass on Oct, 2019
 */
@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;
    @InjectMocks
    private ImageService imageService;

    private Image image;



    @BeforeEach
    void setUp() {
        image = new Image();

    }

    @Test
    @DisplayName("Saved successfully")
    void saveImage() {
        when(imageRepository.save(any(Image.class))).thenReturn(image);
        Image savedImage = imageRepository.save(image);
        assertNotNull(savedImage);
        verify(imageRepository, times(1)).save(any(Image.class));
        int savedSuccessfully = imageService.saveImage(image);
        assertEquals(1, savedSuccessfully);

    }


    @Test
    void getImage() {

        when(imageRepository.findById(any(Long.class))).thenReturn(Optional.of(image));
        Optional<Image> imageFound = imageRepository.findById(image.getId());

        Image imageDetails = imageFound.get();
        assertNotNull(imageDetails);
        assertNotNull(imageFound);



    }
}

























