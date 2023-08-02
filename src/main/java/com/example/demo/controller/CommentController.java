package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.controller.dto.Comment;
import com.example.demo.controller.dto.SortOrder;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
public class CommentController {
    private static final Random RND = new Random();

    @GetMapping("/api/posts/{postId}/comments")
    public List<Comment> getComments(@PathVariable int postId,
                                     @RequestParam(defaultValue = "10") int limit,
                                     @RequestParam(defaultValue = "DESC") SortOrder sort) {
        System.out.println(postId);
        System.out.println(limit);
        System.out.println(sort);
        return List.of(
                new Comment("author1", "body1", Calendar.getInstance().getTime(), 101),
                new Comment("author2", "body2", Calendar.getInstance().getTime(), 102)
        );
    }

    @PostMapping("/api/comments")
    public String createComment(@RequestBody @Valid Comment comment) {
        System.out.println(comment);
        return String.valueOf(RND.nextInt());
    }

    /*@PutMapping("api/comments/{id}")
    public Comment updateComment(@RequestBody Comment comment, @PathVariable int id)
    {
        Comment updateComment = new Comment("Taras Kovalenko", "Good job", Calendar.getInstance().getTime(), 101);

        updateComment.setBody(comment.getBody());

        System.out.println(updateComment);
        System.out.println(id);
        return updateComment;
    }*/

    @PutMapping("api/comments")
    public Comment updateComment2(@RequestBody @Valid Comment comment) {
        Comment updateComment = new Comment("Taras Kovalenko",
                "Good job",
                Calendar.getInstance().getTime(),
                555);

        updateComment.setBody(comment.getBody());

        System.out.println(updateComment);
        System.out.println(comment.getId());
        return updateComment;
    }
}
