package com.example.spring_keycloak.post.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.*;

import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDetailResponse {

    private Integer postId;
    private String titleVi;
    private String titleEn;
    private String url;

    private String previewVi;
    private String previewEn;

    private String contentVi;
    private String contentEn;

    private String postDate;
    private String thumbnail;
    private String tag;

    private String author;
    private Integer views;

    private Boolean popular;

    private CategoryResponse category;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
