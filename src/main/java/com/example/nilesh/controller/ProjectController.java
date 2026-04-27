package com.example.nilesh.controller;

import com.example.nilesh.entity.Project;
import com.example.nilesh.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {

        Project oldProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        oldProject.setTitle(project.getTitle());
        oldProject.setDescription(project.getDescription());
        oldProject.setTechStack(project.getTechStack());
        oldProject.setImageUrl(project.getImageUrl());
        oldProject.setGithubUrl(project.getGithubUrl());
        oldProject.setLiveUrl(project.getLiveUrl());

        return projectRepository.save(oldProject);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "Project deleted successfully";
    }
}