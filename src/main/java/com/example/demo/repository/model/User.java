package com.example.demo.repository.model;

import com.example.demo.repository.CustomUserRoleConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "\"user\"")
public class User {

    public User(Integer id, String firstName, String lastName, List<PostEntity> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    Integer id;
    @Column(name = "first_name", nullable = false)
    String firstName;
    @Column(name = "last_name", nullable = false)
    String lastName;
    @Column(name = "role", nullable = false)
    @Convert(converter = CustomUserRoleConverter.class)
    UserRole role;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    List<PostEntity> posts;
}
