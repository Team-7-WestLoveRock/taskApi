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

//    @Test
//    @DisplayName("프로젝트 Setter 테스트")
//    void setProjectTest() {
//        Project newProject = new Project();
//        task.setProject(newProject);
//        assertEquals(newProject, task.getProject());
//    }

    @Test
    @DisplayName("제목 Getter 테스트")
    void getTitleTest() {
        assertEquals("두레이 작업", task.getTitle());
    }

//    @Test
//    @DisplayName("제목 Setter 테스트")
//    void setTitleTest() {
//        task.setTitle("두레이 작업 수정");
//        assertEquals("두레이 작업 수정", task.getTitle());
//    }

    @Test
    @DisplayName("등록자 ID Getter 테스트")
    void getRegisterUserIdTest() {
        assertEquals("seohyun", task.getRegisterUserId());
    }

//    @Test
//    @DisplayName("등록자 ID Setter 테스트")
//    void setRegisterUserIdTest() {
//        task.setRegisterUserId("manseok");
//        assertEquals("manseok", task.getRegisterUserId());
//    }

    @Test
    @DisplayName("만료일 Getter 테스트")
    void getExpirationDateTest() {
        assertNotNull(task.getExpirationDate());
    }

//    @Test
//    @DisplayName("만료일 Setter 테스트")
//    void setExpirationDateTest() {
//        LocalDateTime newDate = LocalDateTime.now().plusDays(2);
//        task.setExpirationDate(newDate);
//        assertEquals(newDate, task.getExpirationDate());
//    }

    @Test
    @DisplayName("내용 Getter 테스트")
    void getContentTest() {
        assertEquals("작업 내용입니다", task.getContent());
    }

//    @Test
//    @DisplayName("내용 Setter 테스트")
//    void setContentTest() {
//        task.setContent("수정된 작업 내용입니다");
//        assertEquals("수정된 작업 내용입니다", task.getContent());
//    }

    @Test
    @DisplayName("우선순위 Getter 테스트")
    void getPriorityTest() {
        assertEquals("매우 높음", task.getPriority());
    }

//    @Test
//    @DisplayName("우선순위 Setter 테스트")
//    void setPriorityTest() {
//        task.setPriority("낮음");
//        assertEquals("낮음", task.getPriority());
//    }

    @Test
    @DisplayName("마일스톤 Getter 테스트")
    void getMilestoneTest() {
        assertEquals(milestone, task.getMilestone());
    }

//    @Test
//    @DisplayName("마일스톤 Setter 테스트")
//    void setMilestoneTest() {
//        Milestone newMilestone = new Milestone();
//        task.setMilestone(newMilestone);
//        assertEquals(newMilestone, task.getMilestone());
//    }

    @Test
    @DisplayName("작성일 Getter 테스트")
    void getCreatedAtTest() {
        assertNotNull(task.getCreatedAt());
    }

//    @Test
//    @DisplayName("작성일 Setter 테스트")
//    void setCreatedAtTest() {
//        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(1);
//        task.setCreatedAt(newCreatedAt);
//        assertEquals(newCreatedAt, task.getCreatedAt());
//    }
}
