package com.example.demo.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    Integer id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    UserRole type;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Post> posts;
}
