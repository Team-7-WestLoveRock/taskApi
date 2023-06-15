package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.CommentRegisterDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentUpdateDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentWrittenDateResponseDto;
import com.nhnacademy.westloverock.taskapi.entity.Comment;
import com.nhnacademy.westloverock.taskapi.entity.Task;
import com.nhnacademy.westloverock.taskapi.repository.CommentRepository;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import com.nhnacademy.westloverock.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    public List<CommentResponseDto> findCommentList(Integer taskId) {
        return commentRepository.findAllByTask_Id(taskId);
    }

    @Transactional
    public CommentWrittenDateResponseDto createComment(CommentRegisterDto commentRegisterDto) {
        Task task = taskRepository.findById(commentRegisterDto.getTaskId()).orElseThrow(() ->
                new NoSuchElementException("task 없음"));

        Comment comment = Comment.builder()
                .content(commentRegisterDto.getContent())
                .userId(commentRegisterDto.getUserId())
                .task(task)
                .writtenDate(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        return commentRepository.findCommentWrittenDateById(comment.getId()).orElseThrow(() ->
                new NoSuchElementException("comment 없음"));
    }

    @Transactional
    public CommentWrittenDateResponseDto updateComment(CommentUpdateDto commentUpdateDto) {

        Comment comment = commentRepository.findById(commentUpdateDto.getCommentId())
                .orElseThrow(() ->
                        new NoSuchElementException("comment 없음"));

        comment.modifyComment(commentUpdateDto.getContent());
        return commentRepository.findCommentWrittenDateById(comment.getId()).orElseThrow(() ->
                new NoSuchElementException("comment 없음"));
    }
    @Transactional
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

}
