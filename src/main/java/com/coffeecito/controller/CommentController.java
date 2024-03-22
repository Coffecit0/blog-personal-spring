package com.coffeecito.controller;

import com.coffeecito.entity.CommentEntity;
import com.coffeecito.entity.PostEntity;
import com.coffeecito.entity.UserEntity;
import com.coffeecito.service.CommentService;
import com.coffeecito.service.PostService;
import com.coffeecito.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/addComment")
    public String addComment(@RequestParam("postId") Long postId, CommentEntity comment, HttpSession session) {

        UserEntity user = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString())).get();
        PostEntity post = postService.getPostById(postId).orElseThrow(() -> new IllegalArgumentException("¡Invalid post id!"));

        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);

        commentService.createComment(comment);
        return "redirect:/post/postPage/" + postId;
    }

    @GetMapping("/edit/{id}")
    public String editComment(@PathVariable Long id ,Model model) {
        CommentEntity comment = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("¡Invalid comment id!"));
        model.addAttribute("comment",comment);
        return "/posts/update-comment";
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam("commentId") Long id, CommentEntity comment) {
        CommentEntity commentDB = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("¡Invalid comment id!"));
        commentService.updateComment(id, comment);
        return "redirect:/post/postPage/" + commentDB.getPost().getId();
    }

    @GetMapping("/cancel/{id}")
    public String cancelEditComment(@PathVariable Long id) {
        CommentEntity comment = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("¡Invalid comment id!"));
        return "redirect:/post/postPage/" + comment.getPost().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        CommentEntity comment = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("¡Invalid comment id!"));
        commentService.deleteComment(id);
        return "redirect:/post/postPage/" + comment.getPost().getId();
    }
}
