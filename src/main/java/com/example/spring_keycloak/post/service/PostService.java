package com.example.spring_keycloak.post.service;

import com.example.spring_keycloak.post.dto.response.CategoryResponse;
import com.example.spring_keycloak.post.dto.response.PostDetailResponse;
import com.example.spring_keycloak.post.entity.Post;
import com.example.spring_keycloak.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

//    public Optional<Post> getPostByUrl(String urlPost) {
//        return postRepository.findByUrlPost(urlPost);
//    }

    public PostDetailResponse getPostDetailByUrl(String urlPost) {

        Post post = postRepository
                .findPostDetailByURL(urlPost)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return PostDetailResponse.builder()
                .postId(post.getPostId())
                .titleVi(post.getPostTitlVi())
                .titleEn(post.getPostTitlEn())
                .url(post.getPostUrl())

                .previewVi(post.getPostPreVi())
                .previewEn(post.getPostPreEn())

                .contentVi(post.getPostContVi())
                .contentEn(post.getPostContEn())

                .postDate(post.getPostDate())
                .thumbnail(post.getPostThumbnail())
                .tag(post.getPostTag())

                .author(post.getPostAuthor())
                .views(post.getPostViews())
                .popular(post.getPostPopular())

                .createdAt(post.getCreDt())
                .modifiedAt(post.getModDt())

                .category(
                        CategoryResponse.builder()
                                .id(post.getCategory().getCatId())
                                .titleVi(post.getCategory().getCatPostTitlVi())
                                .titleEn(post.getCategory().getCatPostTitlEn())
                                .url(post.getCategory().getCatPostUrl())
                                .build()
                )
                .build();
    }
}
