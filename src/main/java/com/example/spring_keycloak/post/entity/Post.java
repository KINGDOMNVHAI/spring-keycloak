package com.example.spring_keycloak.post.entity;

import com.example.spring_keycloak.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "post_titl_vi", nullable = false)
    private String postTitlVi;

    @Column(name = "post_titl_en")
    private String postTitlEn;

    @Column(name = "post_url", nullable = false, unique = true)
    private String postUrl;

    @Column(name = "post_pre_vi", nullable = false)
    private String postPreVi;

    @Column(name = "post_pre_en")
    private String postPreEn;

    @Column(name = "post_cont_vi", nullable = false, columnDefinition = "TEXT")
    private String postContVi;

    @Column(name = "post_cont_en", columnDefinition = "TEXT")
    private String postContEn;

    @Column(name = "post_date", nullable = false)
    private String postDate;

    @Column(name = "post_thumbnail")
    private String postThumbnail;

    @Column(name = "post_tag")
    private String postTag;

    @Column(name = "post_author", nullable = false)
    private String postAuthor;

    @Column(name = "post_views", nullable = false)
    private Integer postViews;

    @Column(name = "post_enable", nullable = false)
    private Boolean postEnable;

    @Column(name = "post_login_req", nullable = false)
    private Boolean postLoginReq;

    @Column(name = "post_popular", nullable = false)
    private Boolean postPopular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id_cat", nullable = false)
    private CategoryPost category;
}