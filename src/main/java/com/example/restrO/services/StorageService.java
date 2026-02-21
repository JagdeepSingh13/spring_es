package com.example.restrO.services;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface StorageService {

    String store(MultipartFile file, String filename);

    Optional<Resource> loadAsResource(String id);

}
