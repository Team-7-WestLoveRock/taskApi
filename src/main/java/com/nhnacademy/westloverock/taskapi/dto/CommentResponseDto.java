package com.nhnacademy.westloverock.taskapi.dto;

import com.nhnacademy.westloverock.taskapi.entity.Comment;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    private int id;
    private String userId;

    private String content;
    private LocalDateTime writtenDate;
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writtenDate = comment.getWrittenDate();
    }
}
