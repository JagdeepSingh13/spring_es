package com.example.restrO.services.impl;

import com.example.restrO.exceptions.StorageException;
import com.example.restrO.services.StorageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("could not init storage location", e);
        }
    }

//    to store files on disk
    @Override
    public String store(MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("cannot save empty file");
            }

            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String finalFileName = filename + "." + extension;

            Path destFile = rootLocation
                    .resolve(Paths.get(finalFileName))
                    .normalize()
                    .toAbsolutePath();

            if (!destFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("cannot store file outside specified directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return finalFileName;
        } catch(IOException e) {
            throw new StorageException("failed to store file", e);
        }
    }

    @Override
    public Optional<Resource> loadAsResource(String id) {
        return Optional.empty();
    }
}
