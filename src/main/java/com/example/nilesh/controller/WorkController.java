package com.example.nilesh.controller;

import com.example.nilesh.entity.Work;
import com.example.nilesh.repository.WorkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work")
public class WorkController {

    private final WorkRepository repo;

    public WorkController(WorkRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Work> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Work add(@RequestBody Work work) {
        return repo.save(work);
    }

    @PutMapping("/{id}")
    public Work update(@PathVariable Long id, @RequestBody Work work) {
        Work old = repo.findById(id).orElseThrow();

        old.setRole(work.getRole());
        old.setCompany(work.getCompany());
        old.setDuration(work.getDuration());
        old.setDescription(work.getDescription());

        return repo.save(old);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Work deleted successfully";
    }
}
