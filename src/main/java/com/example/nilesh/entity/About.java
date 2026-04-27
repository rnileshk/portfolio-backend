package com.example.nilesh.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String role;

    @Column(length = 3000)
    private String bio;

    private String location;
    private String email;
    private String phone;
    private String resumeUrl;
    private String profileImageUrl;
}