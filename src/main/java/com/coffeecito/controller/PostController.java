package com.coffeecito.controller;

import com.coffeecito.entity.CommentEntity;
import com.coffeecito.entity.PostEntity;
import com.coffeecito.entity.UserEntity;
import com.coffeecito.service.PostService;
import com.coffeecito.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("posts",postService.getAllPost());
        return "/posts/home";
    }

    @GetMapping("/new")
    public String newPostPage() {
        return "/posts/create-post";
    }

    @PostMapping("/create")
    public String createPost(PostEntity post, HttpSession session) {
        post.setCreatedAt(LocalDateTime.now());

        UserEntity user = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString())).get();
        post.setUser(user);

        postService.createPost(post);
        return "redirect:/post/home";
    }

    @GetMapping("/postPage/{id}")
    public String postPage(@PathVariable Long id ,Model model) {
        PostEntity post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("¡Invalid post id!"));
        List<CommentEntity> comments = post.getComments();

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "/posts/post-page";
    }

    @GetMapping("/mine")
    public String myPosts(Model model, HttpSession session) {
        Long userId = Long.parseLong(session.getAttribute("user_session_id").toString());
        List<PostEntity> posts = postService.getPostByUserId(userId);

        model.addAttribute("posts", posts);
        return "/posts/my-post";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id ,Model model) {
        PostEntity post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("¡Invalid post id!"));
        model.addAttribute("post",post);
        return "/posts/update-post";
    }

    @PostMapping("/update")
    public String updatePost(@RequestParam("idPost") Long id, PostEntity post) {
        postService.updatePost(id, post);
        return "redirect:/post/mine";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return "redirect:/post/mine";
    }

    @GetMapping("/search")
    public String searchPosts(@RequestParam("title") String title, Model model) {
        List<PostEntity> posts = postService.searchPostByTitle(title);
        model.addAttribute("posts", posts);
        return "/posts/home";
    }
}
