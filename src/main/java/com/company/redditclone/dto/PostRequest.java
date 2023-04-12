package com.company.redditclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private Long postId;
    private String subredditName;
    @NotBlank(message = "Post name can't be empty or null!")
    private String postName;
    private String url;
    private String description;
}
