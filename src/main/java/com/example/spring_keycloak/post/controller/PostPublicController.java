package com.example.spring_keycloak.post.controller;

import com.example.spring_keycloak.post.dto.response.PostDetailResponse;
import com.example.spring_keycloak.post.entity.Post;
import com.example.spring_keycloak.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/public")
public class PostPublicController {

    @Autowired
    private PostService postService;

//    @GetMapping("/post/detail/{urlPost}")
//    public ResponseEntity<?> getPostDetail(@PathVariable String urlPost) {
//        Optional<Post> post = postService.getPostByUrl(urlPost);
//
//        if (post.isPresent()) {
//            return ResponseEntity.ok(post.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping(
            value = "/post/detail/{urlPost}",
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public PostDetailResponse getPostDetail(@PathVariable String urlPost) {
        return postService.getPostDetailByUrl(urlPost);
    }
}
