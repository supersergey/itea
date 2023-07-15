package com.example.demo;

import com.example.demo.dto.Post;
import com.example.demo.dto.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.PostRepositoryImpl;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepositoryImpl;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

//        PostRepository postRepository = new PostRepositoryImpl();
//
//        int postId1 = postRepository.save(2, new Post("post1", "post1"));
//        int postId2 = postRepository.save(2, new Post("post2", "post2"));
//        int postId3 = postRepository.save(3, new Post("post3", "post3"));
//        int postId4 = postRepository.save(3, new Post("post4", "post4"));
//        int postId5 = postRepository.save(4, new Post("post5", "post5"));
//        int postId6 = postRepository.save(4, new Post("post6", "post6"));
//        int postId7 = postRepository.save(5, new Post("post7", "post7"));

//        postRepository.delete(2, postId1);
//        postRepository.delete(3, postId4);
//        postRepository.delete(4, postId5);

//        System.out.println(postRepository.getByUserId(2) + "\n");
//        System.out.println(postRepository.getByUserId(3) + "\n");
//        System.out.println(postRepository.getByUserId(4) + "\n");
//        System.out.println(postRepository.getByUserId(5) + "\n");
//
//        System.out.println(postRepository.countByUserId(2));
//        System.out.println(postRepository.countByUserId(3));
//        System.out.println(postRepository.countByUserId(4));
//        System.out.println(postRepository.countByUserId(5));
    }
}