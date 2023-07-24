package com.example.demo.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(allocationSize = 1, name = "post_seq", sequenceName = "post_id_seq")
    Integer id;
    @Column
    String title;
    @Column
    String body;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
