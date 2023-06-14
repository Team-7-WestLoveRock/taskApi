package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.CommentRegisterDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentUpdateDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentWrittenDateResponseDto;
import com.nhnacademy.westloverock.taskapi.entity.Comment;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Task;
import com.nhnacademy.westloverock.taskapi.repository.CommentRepository;
import com.nhnacademy.westloverock.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    TaskRepository taskRepository;
    @Mock
    CommentRepository commentRepository;
    @InjectMocks
    CommentService commentService;
    @Test
    @DisplayName("commentList 조회 - 정상")
    void findCommentList() {
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .userId("test")
                .content("test1")
                .writtenDate(LocalDateTime.now())
                .build();

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        commentResponseDtoList.add(commentResponseDto);

        when(commentService.findCommentList(anyInt())).thenReturn(commentResponseDtoList);

        assertThat(commentService.findCommentList(anyInt())).hasSize(1);
    }

    @Test
    @DisplayName("comment 생성 시 comment 작성일 조회 - 실패")
    void createCommentNoSuchElement() {
        when(taskRepository.findById(any())).thenReturn(Optional.empty());

        CommentRegisterDto commentRegisterDto = CommentRegisterDto.builder()
                .taskId(1)
                .userId("test")
                .writtenDate(LocalDateTime.now())
                .content("test")
                .build();
        assertThrows(NoSuchElementException.class, () ->
                commentService.createComment(commentRegisterDto));
    }

    @Test
    @DisplayName("comment 생성 시 comment 작성일 조회 - 성공")
    void createComment() {
        Project project = Project.builder()
                .name("test")
                .createAt(LocalDateTime.now())
                .state("실행")
                .description("test")
                .build();

        Task task = Task.builder()
                .project(project)
                .createdAt(LocalDateTime.now())
                .title("test")
                .priority("높음")
                .expirationDate(LocalDateTime.now())
                .registerUserId("test1")
                .content("test")
                .build();

        when(taskRepository.findById(any())).thenReturn(Optional.of(task));

        CommentRegisterDto commentRegisterDto = CommentRegisterDto.builder()
                .userId("test")
                .content("test")
                .writtenDate(LocalDateTime.now())
                .taskId(task.getId())
                .build();

        CommentWrittenDateResponseDto commentWrittenDateResponseDto =
                CommentWrittenDateResponseDto.builder()
                        .writtenDate(LocalDateTime.now())
                        .build();

        when(commentRepository.findCommentWrittenDateById(any())).thenReturn(Optional.of(commentWrittenDateResponseDto));
        assertThat(commentService.createComment(commentRegisterDto).getWrittenDate()).isEqualTo(commentWrittenDateResponseDto.getWrittenDate());
        verify(commentRepository).save(any(Comment.class));

    }

    @Test
    @DisplayName("comment 갱신 시 comment 작성일 조회 - 실패")
    void updateCommentNoSuchElement() {
        when(commentRepository.findById(any())).thenReturn(Optional.empty());

        CommentUpdateDto commentUpdateDto = mock(CommentUpdateDto.class);

        assertThrows(NoSuchElementException.class, () ->
                commentService.updateComment(commentUpdateDto));
    }
    @Test
    @DisplayName("comment 갱신 시 comment 작성일 조회 - 성공")
    void updateComment() {

        Task task = mock(Task.class);

        Comment comment = Comment.builder()
                .task(task)
                .writtenDate(LocalDateTime.now())
                .content("test")
                .userId("test")
                .build();

        when(commentRepository.findById(any())).thenReturn(Optional.of(comment));

        CommentWrittenDateResponseDto commentWrittenDateResponseDto =
                CommentWrittenDateResponseDto.builder()
                        .writtenDate(LocalDateTime.now())
                        .build();

        when(commentRepository.findCommentWrittenDateById(any())).thenReturn(Optional.of(commentWrittenDateResponseDto));

        CommentUpdateDto commentUpdateDto = CommentUpdateDto.builder()
                .commentId(1)
                .content("null")
                .build();

        assertThat(commentService.updateComment(commentUpdateDto).getWrittenDate()).isEqualTo(commentWrittenDateResponseDto.getWrittenDate());
    }

    @Test
    @DisplayName("comment 삭제 - 성공")
    void deleteComment() {
        commentService.deleteComment(anyInt());
        verify(commentRepository, times(1)).deleteById(anyInt());
    }
}