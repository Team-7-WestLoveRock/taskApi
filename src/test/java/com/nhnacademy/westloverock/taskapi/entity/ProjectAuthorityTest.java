package com.nhnacademy.westloverock.taskapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@SpringBootTest
class ProjectAuthorityTest {

    private ProjectAuthority projectAuthority;
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        ProjectAuthority.Pk pk = new ProjectAuthority.Pk(1, "seohyun");
        projectAuthority = ProjectAuthority.builder()
                .id(pk)
                .project(project)
                .authority("Admin")
                .build();
    }

    @Test
    @DisplayName("프로젝트 Getter 테스트")
    void getProjectTest() {
        assertEquals(project, projectAuthority.getProject());
    }

    @Test
    @DisplayName("프로젝트 Setter 테스트")
    void setProjectTest() {
        Project newProject = new Project();
        projectAuthority.setProject(newProject);
        assertEquals(newProject, projectAuthority.getProject());
    }

    @Test
    @DisplayName("권한 Getter 테스트")
    void getAuthorityTest() {
        assertEquals("Admin", projectAuthority.getAuthority());
    }

    @Test
    @DisplayName("권한 Setter 테스트")
    void setAuthorityTest() {
        projectAuthority.setAuthority("User");
        assertEquals("User", projectAuthority.getAuthority());
    }

    @Test
    @DisplayName("아이디 Getter 테스트")
    void getIdTest() {
        assertNotNull(projectAuthority.getId());
    }

    @Test
    @DisplayName("아이디 Setter 테스트")
    void setIdTest() {
        ProjectAuthority.Pk newPk = new ProjectAuthority.Pk(2, "jeongmin");
        projectAuthority.setId(newPk);
        assertEquals(newPk, projectAuthority.getId());
    }
    @Test
    @DisplayName("PK Equals and HashCode 테스트")
    void pkEqualsAndHashCodeTest() {
        ProjectAuthority.Pk pk1 = new ProjectAuthority.Pk(1, "seohyun");
        ProjectAuthority.Pk pk2 = new ProjectAuthority.Pk(1, "seohyun");

        assertTrue(pk1.equals(pk2) && pk2.equals(pk1));
        assertEquals(pk1.hashCode(), pk2.hashCode());
    }

    @Test
    @DisplayName("AllArgsConstructor 테스트")
    void allArgsConstructorTest() {
        ProjectAuthority.Pk pk = new ProjectAuthority.Pk(1, "seohyun");
        Project newProject = new Project();

        ProjectAuthority newProjectAuthority = new ProjectAuthority(pk, newProject, "Admin");

        assertEquals(pk, newProjectAuthority.getId());
        assertEquals(newProject, newProjectAuthority.getProject());
        assertEquals("Admin", newProjectAuthority.getAuthority());
    }

    @Test
    @DisplayName("NoArgsConstructor 테스트")
    void noArgsConstructorTest() {
        ProjectAuthority newProjectAuthority = new ProjectAuthority();

        assertNull(newProjectAuthority.getId());
        assertNull(newProjectAuthority.getProject());
        assertNull(newProjectAuthority.getAuthority());
    }
}
