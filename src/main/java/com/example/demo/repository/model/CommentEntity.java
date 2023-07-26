package com.example.demo.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    Integer id;
    @Column(nullable = false)
    String body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    PostEntity postEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
