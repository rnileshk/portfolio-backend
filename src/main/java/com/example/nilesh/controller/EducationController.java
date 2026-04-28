package com.example.nilesh.controller;

import com.example.nilesh.entity.Education;
import com.example.nilesh.repository.EducationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationRepository repo;

    public EducationController(EducationRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Education> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Education add(@RequestBody Education education) {
        return repo.save(education);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education) {
        Education old = repo.findById(id).orElseThrow();

        old.setDegree(education.getDegree());
        old.setInstitute(education.getInstitute());
        old.setDuration(education.getDuration());
        old.setDescription(education.getDescription());

        return repo.save(old);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Education deleted successfully";
    }
}
