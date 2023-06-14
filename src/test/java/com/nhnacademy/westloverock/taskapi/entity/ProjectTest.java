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
    private Milestone milestone;

    @BeforeEach
    void setUp() {
        milestone = new Milestone();
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

//    @Test
//    @DisplayName("제목 Setter 테스트")
//    void setNameTest() {
//        project.setName("업데이트 두레이 미니 프로젝트");
//        assertEquals("업데이트 두레이 미니 프로젝트", project.getName());
//    }

    @Test
    @DisplayName("상태 Getter 테스트")
    void getStateTest() {
        assertEquals("진행", project.getState());
    }

//    @Test
//    @DisplayName("상태 Setter 테스트")
//    void setStateTest() {
//        String expectedState = "완료";
//        project.setState(expectedState);
//        assertEquals(expectedState, project.getState());
//    }

    @Test
    @DisplayName("작성일 Getter 테스트")
    void getCreateAtTest() {
        assertNotNull(project.getCreateAt());
    }

//    @Test
//    @DisplayName("작성일 Setter 테스트")
//    void setCreateAtTest() {
//        LocalDateTime expectedDate = LocalDateTime.now().plusDays(1);
//        project.setCreateAt(expectedDate);
//        assertEquals(expectedDate, project.getCreateAt());
//    }

    @Test
    @DisplayName("마일스톤 Getter 테스트")
    void getMilestoneTest() {
        assertNotNull(project.getMilestone());
    }

//    @Test
//    @DisplayName("마일스톤 Setter 테스트")
//    void setMilestoneTest() {
//        Milestone expectedMilestone = new Milestone();
//        project.setMilestone(expectedMilestone);
//        assertEquals(expectedMilestone, project.getMilestone());
//    }

    @Test
    @DisplayName("권한설정 Getter 테스트")
    void getProjectAuthoritiesTest() {
        assertNotNull(project.getProjectAuthorities());
    }

//    @Test
//    @DisplayName("권한설정 Setter 테스트")
//    void setProjectAuthoritiesTest() {
//        List<ProjectAuthority> expectedAuthorities = new ArrayList<>();
//        ProjectAuthority authority = new ProjectAuthority();
//        expectedAuthorities.add(authority);
//        project.setProjectAuthorities(expectedAuthorities);
//        assertTrue(project.getProjectAuthorities().contains(authority));
//    }

    @Test
    @DisplayName("태그 Getter 테스트")
    void getTagsTest() {
        assertNotNull(project.getTags());
    }

//    @Test
//    @DisplayName("태그 Setter 테스트")
//    void setTagsTest() {
//        List<Tag> expectedTags = new ArrayList<>();
//        Tag tag = new Tag();
//        expectedTags.add(tag);
//        project.setTags(expectedTags);
//        assertTrue(project.getTags().contains(tag));
//    }

    @Test
    @DisplayName("작업 Getter 테스트")
    void getTasksTest() {
        assertNotNull(project.getTasks());
    }

//    @Test
//    @DisplayName("작업 Setter 테스트")
//    void setTasksTest() {
//        List<Task> expectedTasks = new ArrayList<>();
//        Task task = new Task();
//        expectedTasks.add(task);
//        project.setTasks(expectedTasks);
//        assertTrue(project.getTasks().contains(task));
//    }

}
