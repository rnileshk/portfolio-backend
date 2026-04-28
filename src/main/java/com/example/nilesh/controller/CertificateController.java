package com.example.nilesh.controller;

import com.example.nilesh.entity.Certificate;
import com.example.nilesh.repository.CertificateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateRepository repo;

    public CertificateController(CertificateRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Certificate> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Certificate add(@RequestBody Certificate certificate) {
        return repo.save(certificate);
    }

    @PutMapping("/{id}")
    public Certificate update(@PathVariable Long id, @RequestBody Certificate certificate) {
        Certificate old = repo.findById(id).orElseThrow();

        old.setTitle(certificate.getTitle());
        old.setIssuer(certificate.getIssuer());
        old.setDate(certificate.getDate());
        old.setImageUrl(certificate.getImageUrl());
        old.setLink(certificate.getLink());

        return repo.save(old);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Certificate deleted successfully";
    }
}
