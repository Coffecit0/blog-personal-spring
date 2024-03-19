package com.coffeecito.service;

import com.coffeecito.entity.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostEntity> getAllPost();
    Optional<PostEntity> getPostById(Long id);
    List<PostEntity> getPostByUserId(Long userId);
    void createPost(PostEntity post);
    void updatePost(Long id, PostEntity post);
    void deletePostById(Long id);
    List<PostEntity> searchPostByTitle(String title);
}
