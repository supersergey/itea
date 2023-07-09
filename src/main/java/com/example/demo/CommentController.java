package com.example.demo;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Comments;
import com.example.demo.SortOder;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
public class CommentController {
    private static final Random RND = new Random();

    @GetMapping("/api/posts/{postId}/comments")
    public List<Comments> getComments(@PathVariable int postId,
                                      @RequestParam(defaultValue = "10") int limit,
                                      @RequestParam(defaultValue = "DESC") SortOder sort)
    {
        System.out.println(postId);
        System.out.println(limit);
        System.out.println(sort);
        return List.of(
                new Comments("author1", "body1", Calendar.getInstance().getTime()),
                new Comments("author2", "body2", Calendar.getInstance().getTime())
        );
    }

    @PostMapping("/api/comments")
    public String createComments(@RequestBody Comments comment)
    {
        System.out.println(comment);
        return String.valueOf(RND.nextInt());
    }

    @PutMapping("api/comments/{commentId}")
    public Comments updateComments(@RequestBody Comments comment, @PathVariable int commentId)
    {
        Comments updateComment = new Comments("Taras Kovalenko", "Good job", Calendar.getInstance().getTime());

        updateComment.setBody(comment.getBody());

        System.out.println(updateComment);
        return updateComment;
    }
}
