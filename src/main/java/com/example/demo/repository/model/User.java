package com.example.demo.repository.model;

import com.example.demo.repository.CustomUserRoleConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "\"user\"")
public class User {

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    List<PostEntity> posts = new ArrayList<>();

    public void setPosts(List<PostEntity> posts) {
        this.posts = new ArrayList<>();
        if (posts != null) {
            posts.forEach(post -> {
                post.setUser(this);
                this.posts.add(post);
            });
        }
    }

}
