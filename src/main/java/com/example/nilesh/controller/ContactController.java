package com.example.nilesh.controller;

import com.example.nilesh.entity.ContactMessage;
import com.example.nilesh.repository.ContactRepository;
import com.example.nilesh.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    public ContactController(ContactRepository contactRepository, EmailService emailService) {
        this.contactRepository = contactRepository;
        this.emailService = emailService;
    }

    @PostMapping
    public ContactMessage sendMessage(@RequestBody ContactMessage message) {
        ContactMessage savedMessage = contactRepository.save(message);

        try {
            emailService.sendContactEmails(savedMessage);
        } catch (Exception e) {
            System.out.println("EMAIL ERROR: " + e.getMessage());
        }

        return savedMessage;
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
