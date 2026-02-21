package com.example.restrO.controllers;

import com.example.restrO.domain.dtos.PhotoDto;
import com.example.restrO.domain.entities.Photo;
import com.example.restrO.mappers.PhotoMapper;
import com.example.restrO.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public PhotoDto uploadPhoto(@RequestParam("file")MultipartFile file) {
        Photo photo = photoService.uploadPhoto(file);
        return photoMapper.toDto(photo);
    }

}
