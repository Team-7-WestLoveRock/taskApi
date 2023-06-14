package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.dto.CommentResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentWrittenDateResponseDto;
import com.nhnacademy.westloverock.taskapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<CommentResponseDto> findAllByTask_Id(Integer taskId);
    Optional<CommentWrittenDateResponseDto> findCommentWrittenDateById(Integer commentId);


}