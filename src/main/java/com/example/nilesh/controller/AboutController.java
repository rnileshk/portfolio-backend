package com.example.nilesh.controller;

import com.example.nilesh.entity.About;
import com.example.nilesh.repository.AboutRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/about")
public class AboutController {

    private final AboutRepository aboutRepository;

    public AboutController(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    @GetMapping
    public About getAbout() {
        return aboutRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new About());
    }

    @PostMapping
    public About saveAbout(@RequestBody About about) {

        About existingAbout = aboutRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new About());

        existingAbout.setFullName(about.getFullName());
        existingAbout.setRole(about.getRole());
        existingAbout.setBio(about.getBio());
        existingAbout.setLocation(about.getLocation());
        existingAbout.setEmail(about.getEmail());
        existingAbout.setPhone(about.getPhone());
        existingAbout.setResumeUrl(about.getResumeUrl());
        existingAbout.setProfileImageUrl(about.getProfileImageUrl());

        return aboutRepository.save(existingAbout);
    }
}