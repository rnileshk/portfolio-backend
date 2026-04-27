package com.example.nilesh.controller;

import com.example.nilesh.Service.CloudinaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private final CloudinaryService cloudinaryService;

    public FileUploadController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/image")
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) {

        String imageUrl = cloudinaryService.uploadImage(file);

        return Map.of(
                "message", "Image uploaded successfully",
                "imageUrl", imageUrl
        );
    }
}