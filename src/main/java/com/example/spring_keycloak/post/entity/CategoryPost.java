package com.example.spring_keycloak.post.entity;

import com.example.spring_keycloak.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "category_post")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CategoryPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "cat_post_titl_vi", nullable = false)
    private String catPostTitlVi;

    @Column(name = "cat_post_titl_en", nullable = false)
    private String catPostTitlEn;

    @Column(name = "cat_post_url", nullable = false, unique = true)
    private String catPostUrl;

    @Column(name = "cat_post_enable", nullable = false)
    private Boolean catPostEnable;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Post> posts;
}