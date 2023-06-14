package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.dto.CommentResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentUpdateDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentWrittenDateResponseDto;
import com.nhnacademy.westloverock.taskapi.entity.Comment;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Task;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

@ActiveProfiles("dev")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    Project project;
    Task task;


    @BeforeEach
    void setUp() {
        project = Project.builder()
                .name("test")
                .description("test")
                .state("활성")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        task = Task.builder()
                .project(project)
                .title("test")
                .registerUserId("test")
                .expirationDate(LocalDateTime.now())
                .content("test")
                .priority("우선")
                .createdAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(task);
    }

    @Test
    @Transactional
    void save() {
        Comment comment = Comment.builder()
                .task(task)
                .userId("test")
                .content("i'm hungry")
                .writtenDate(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        assertThat(testEntityManager.getId(comment)).isEqualTo(comment.getId());
    }

    @Test
    @Transactional
    void read() {
        Comment comment = Comment.builder()
                .task(task)
                .userId("test")
                .content("i'm hungry")
                .writtenDate(LocalDateTime.now())
                .build();

        testEntityManager.persist(comment);
        testEntityManager.flush();
        testEntityManager.clear();

        assertThat(commentRepository.findById(comment.getId()).get().getId()).isEqualTo(comment.getId());
        assertThat(commentRepository.findById(comment.getId()).get().getContent()).isEqualTo(comment.getContent());
        assertThat(commentRepository.findById(comment.getId()).get().getTask().getId()).isEqualTo(comment.getTask().getId());
        assertThat(commentRepository.findById(comment.getId()).get().getWrittenDate()).isEqualTo(comment.getWrittenDate());
        assertThat(commentRepository.findById(comment.getId()).get().getUserId()).isEqualTo(comment.getUserId());
    }

    @Test
    @Transactional
    void update() {
        Comment comment = Comment.builder()
                .task(task)
                .userId("test")
                .content("i'm hungry")
                .writtenDate(LocalDateTime.now())
                .build();

        testEntityManager.persist(comment);

        comment.modifyComment("you can do it");
        testEntityManager.flush();

        assertThat(commentRepository.findById(comment.getId()).get().getUserId()).isEqualTo(comment.getUserId());
        assertThat(commentRepository.findById(comment.getId()).get().getContent()).isEqualTo(comment.getContent());
        assertThat(commentRepository.findById(comment.getId()).get().getWrittenDate()).isEqualTo(comment.getWrittenDate());
        assertThat(commentRepository.findById(comment.getId()).get().getTask().getId()).isEqualTo(comment.getTask().getId());
        assertThat(commentRepository.findById(comment.getId()).get().getId()).isEqualTo(comment.getId());
    }

    @Test
    @Transactional
    void delete() {
        Comment comment = Comment.builder()
                .task(task)
                .userId("test")
                .content("i'm hungry")
                .writtenDate(LocalDateTime.now())
                .build();

        testEntityManager.persist(comment);

        commentRepository.delete(comment);
        assertThat(commentRepository.findById(comment.getId())).isNotPresent();
    }

    @Test
    @Transactional
    void findAllByTask_Id() {
        Comment comment = Comment.builder()
                .task(task)
                .userId("test")
                .content("i'm hungry")
                .writtenDate(LocalDateTime.now())
                .build();

        Comment comment2 = Comment.builder()
                .task(task)
                .userId("test2")
                .content("i'm hungry2")
                .writtenDate(LocalDateTime.now())
                .build();

        testEntityManager.persist(comment);
        testEntityManager.persist(comment2);

        assertThat(commentRepository.findAllByTask_Id(task.getId())).hasSize(2);
    }

    @Test
    @Transactional
    void findCommentWrittenDateById() {
        Comment comment = Comment.builder()
                .task(task)
                .userId("test")
                .content("i'm hungry")
                .writtenDate(LocalDateTime.now())
                .build();

        testEntityManager.persist(comment);
        testEntityManager.flush();
        testEntityManager.clear();

        assertThat(commentRepository.findCommentWrittenDateById(comment.getId()).get().getWrittenDate()).isEqualTo(comment.getWrittenDate());
    }
}
