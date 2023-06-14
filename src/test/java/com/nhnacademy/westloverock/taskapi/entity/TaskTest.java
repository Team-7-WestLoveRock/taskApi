package com.nhnacademy.westloverock.taskapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@SpringBootTest
class TaskTest {

    private Task task;
    private Project project;
    private Milestone milestone;

    @BeforeEach
    void setUp() {
        project = new Project();
        milestone = new Milestone();
        task = Task.builder()
                .id(1)
                .project(project)
                .title("두레이 작업")
                .registerUserId("seohyun")
                .expirationDate(LocalDateTime.now().plusDays(1))
                .content("작업 내용입니다")
                .priority("매우 높음")
                .milestone(milestone)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("프로젝트 Getter 테스트")
    void getProjectTest() {
        assertEquals(project, task.getProject());
    }

    @Test
    @DisplayName("제목 Getter 테스트")
    void getTitleTest() {
        assertEquals("두레이 작업", task.getTitle());
    }

    @Test
    @DisplayName("등록자 ID Getter 테스트")
    void getRegisterUserIdTest() {
        assertEquals("seohyun", task.getRegisterUserId());
    }

    @Test
    @DisplayName("만료일 Getter 테스트")
    void getExpirationDateTest() {
        assertNotNull(task.getExpirationDate());
    }

    @Test
    @DisplayName("내용 Getter 테스트")
    void getContentTest() {
        assertEquals("작업 내용입니다", task.getContent());
    }

    @Test
    @DisplayName("우선순위 Getter 테스트")
    void getPriorityTest() {
        assertEquals("매우 높음", task.getPriority());
    }

    @Test
    @DisplayName("마일스톤 Getter 테스트")
    void getMilestoneTest() {
        assertEquals(milestone, task.getMilestone());
    }

    @Test
    @DisplayName("작성일 Getter 테스트")
    void getCreatedAtTest() {
        assertNotNull(task.getCreatedAt());
    }
}
