package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.*;
import com.nhnacademy.westloverock.taskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/project/api/projects")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @GetMapping("/{projectId}/task/{taskId}/comments")
    public HttpEntity<List<CommentResponseDto>> findCommentList(@PathVariable Integer projectId,
                                                                @PathVariable Integer taskId) {
        return new ResponseEntity<>(commentService.findCommentList(taskId), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/task/{taskId}/comment")
    public HttpEntity<CommentWrittenDateResponseDto> createComment(@PathVariable Integer projectId,
                                                                   @PathVariable Integer taskId,
                                                                   @RequestBody @Valid CommentContentRequest commentContentRequest,
                                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("요청값이 잘못됨");
        }

        CommentRegisterDto commentRegisterDto = CommentRegisterDto.builder()
                .content(commentContentRequest.getContent())
                .userId(commentContentRequest.getUserId())
                .taskId(taskId)
                .writtenDate(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(commentService.createComment(commentRegisterDto), HttpStatus.CREATED);
    }

    @PostMapping("/{projectId}/task/{taskId}/comment/{commentId}")
    public HttpEntity<CommentWrittenDateResponseDto> updateComment(@PathVariable Integer projectId,
                                                                   @PathVariable Integer taskId,
                                                                   @PathVariable Integer commentId,
                                                                   @RequestBody @Valid CommentContentRequest commentContentRequest,
                                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("요청값 오류");
        }

        CommentUpdateDto commentUpdateDto = CommentUpdateDto.builder()
                .commentId(commentId)
                .content(commentContentRequest.getContent())
                .build();

        return new ResponseEntity<>(commentService.updateComment(commentUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/task/{taskId}/comment/{commentId}")
    public HttpEntity<Void> deleteComment(@PathVariable Integer projectId,
                                          @PathVariable Integer taskId,
                                          @PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
