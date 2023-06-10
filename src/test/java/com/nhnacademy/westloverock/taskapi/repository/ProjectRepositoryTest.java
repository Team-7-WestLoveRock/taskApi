package com.nhnacademy.westloverock.taskapi.repository;



import com.nhnacademy.westloverock.taskapi.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void testFindProjectById() {
        // given
        Project testProject = new Project();
        testProject.setName("Test Project");
        testProject.setDescription("This is a test project.");
        testProject.setCreateAt(LocalDateTime.now());
        testProject.setState("진행");
        entityManager.persistAndFlush(testProject);

        // when
        Optional<Project> foundProject = projectRepository.findProjectById(testProject.getId());

        // then
        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getName()).isEqualTo(testProject.getName());
        assertThat(foundProject.get().getDescription()).isEqualTo(testProject.getDescription());
    }
}
