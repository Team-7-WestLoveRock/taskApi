package com.nhnacademy.westloverock.taskapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@SpringBootTest
class MilestoneTest {

    private Milestone milestone;
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        milestone = Milestone.builder()
                .id(1)
                .project(project)
                .name("두레이 이해하기")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .build();
    }

    @Test
    @DisplayName("프로젝트 Getter 테스트")
    void getProjectTest() {
        assertEquals(project, milestone.getProject());
    }

    @Test
    @DisplayName("프로젝트 Setter 테스트")
    void setProjectTest() {
        Project newProject = new Project();
        milestone.setProject(newProject);
        assertEquals(newProject, milestone.getProject());
    }

    @Test
    @DisplayName("이름 Getter 테스트")
    void getNameTest() {
        assertEquals("두레이 이해하기", milestone.getName());
    }

    @Test
    @DisplayName("이름 Setter 테스트")
    void setNameTest() {
        milestone.setName("taskApi Entity 구현");
        assertEquals("taskApi Entity 구현", milestone.getName());
    }

    @Test
    @DisplayName("시작일 Getter 테스트")
    void getStartDateTest() {
        assertNotNull(milestone.getStartDate());
    }

    @Test
    @DisplayName("시작일 Setter 테스트")
    void setStartDateTest() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        milestone.setStartDate(newDate);
        assertEquals(newDate, milestone.getStartDate());
    }

    @Test
    @DisplayName("종료일 Getter 테스트")
    void getEndDateTest() {
        assertNotNull(milestone.getEndDate());
    }

    @Test
    @DisplayName("종료일 Setter 테스트")
    void setEndDateTest() {
        LocalDate newDate = LocalDate.now().plusDays(2);
        milestone.setEndDate(newDate);
        assertEquals(newDate, milestone.getEndDate());
    }
}
