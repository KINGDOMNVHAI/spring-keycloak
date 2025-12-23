package com.example.spring_keycloak.post.repository;

import com.example.spring_keycloak.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
       SELECT p FROM Post p
       where p.postUrl = :url
         and p.postEnable = true
    """)
    Optional<Post> findPostDetailByURL(@Param("url") String url);

}