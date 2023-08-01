package com.example.demo.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_id_seq", allocationSize = 1)
    Integer id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "postEntity", fetch = FetchType.EAGER)
    List<CommentEntity> comments;
}
