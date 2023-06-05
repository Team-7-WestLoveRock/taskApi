package com.nhnacademy.westloverock.taskapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("dev")
@SpringBootTest
class TagTest {

    private Tag tag;
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        tag = Tag.builder()
                .id(1)
                .project(project)
                .name("태그")
                .color("#EBEDFF")
                .build();
    }

    @Test
    @DisplayName("프로젝트 Getter 테스트")
    void getProjectTest() {
        assertEquals(project, tag.getProject());
    }

    @Test
    @DisplayName("프로젝트 Setter 테스트")
    void setProjectTest() {
        Project newProject = new Project();
        tag.setProject(newProject);
        assertEquals(newProject, tag.getProject());
    }

    @Test
    @DisplayName("이름 Getter 테스트")
    void getNameTest() {
        assertEquals("태그", tag.getName());
    }

    @Test
    @DisplayName("이름 Setter 테스트")
    void setNameTest() {
        tag.setName("태그2");
        assertEquals("태그2", tag.getName());
    }

    @Test
    @DisplayName("색상 Getter 테스트")
    void getColorTest() {
        assertEquals("#EBEDFF", tag.getColor());
    }

    @Test
    @DisplayName("색상 Setter 테스트")
    void setColorTest() {
        tag.setColor("#FFFCEB");
        assertEquals("#FFFCEB", tag.getColor());
    }
}
