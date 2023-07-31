package com.example.demo.repository.model;

import com.example.demo.controller.dto.Post;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    private Integer id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}