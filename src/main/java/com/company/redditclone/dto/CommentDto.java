package com.company.redditclone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    @NotBlank
    private Long postId;
    @NotBlank
    private String text;
    private String username;
}
