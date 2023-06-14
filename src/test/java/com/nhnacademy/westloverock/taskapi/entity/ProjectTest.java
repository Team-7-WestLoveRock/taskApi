package com.nhnacademy.westloverock.taskapi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@SpringBootTest
class ProjectTest {
    private Project project;
    @BeforeEach
    void setUp() {
        project = Project.builder()
                .id(1)
                .name("두레이 미니 프로젝트")
                .description("김서현,김정민,추만석의 두레이 미니프로젝트 입니다.")
                .state("진행")
                .createAt(LocalDateTime.now())
                .milestone(new ArrayList<>())
                .projectAuthorities(new ArrayList<>())
                .tags(new ArrayList<>())
                .tasks(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("제목 Get 테스트")
    void getNameTest() {
        assertEquals("두레이 미니 프로젝트", project.getName());
    }

    @Test
    @DisplayName("상태 Getter 테스트")
    void getStateTest() {
        assertEquals("진행", project.getState());
    }

    @Test
    @DisplayName("작성일 Getter 테스트")
    void getCreateAtTest() {
        assertNotNull(project.getCreateAt());
    }

    @Test
    @DisplayName("마일스톤 Getter 테스트")
    void getMilestoneTest() {
        assertNotNull(project.getMilestone());
    }

    @Test
    @DisplayName("권한설정 Getter 테스트")
    void getProjectAuthoritiesTest() {
        assertNotNull(project.getProjectAuthorities());
    }


    @Test
    @DisplayName("태그 Getter 테스트")
    void getTagsTest() {
        assertNotNull(project.getTags());
    }

    @Test
    @DisplayName("작업 Getter 테스트")
    void getTasksTest() {
        assertNotNull(project.getTasks());
    }

}
