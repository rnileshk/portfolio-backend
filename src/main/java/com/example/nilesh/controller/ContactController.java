package com.example.nilesh.controller;

import com.example.nilesh.entity.ContactMessage;
import com.example.nilesh.repository.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping
    public ContactMessage sendMessage(@RequestBody ContactMessage message) {
        return contactRepository.save(message);
    }

    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return "Message deleted successfully";
    }
}