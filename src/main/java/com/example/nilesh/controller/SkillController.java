package com.example.nilesh.controller;

import com.example.nilesh.entity.Skill;
import com.example.nilesh.repository.SkillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {

        Skill oldSkill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));

        oldSkill.setName(skill.getName());
        oldSkill.setCategory(skill.getCategory());
        oldSkill.setIcon(skill.getIcon());

        return skillRepository.save(oldSkill);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Long id) {

        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill not found with id: " + id);
        }

        skillRepository.deleteById(id);
        return "Skill deleted successfully";
    }
}
