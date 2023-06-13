package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.entity.Milestone;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MileStoneRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MileStoneRepository mileStoneRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    @DisplayName("저장")
    @Order(1)
    void save() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        Milestone milestone = Milestone.builder()
                .project(project)
                .name("qeqweq")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        mileStoneRepository.save(milestone);

        assertThat(testEntityManager.getId(milestone)).isEqualTo(milestone.getId());
    }

    @Test
    @DisplayName("읽기")
    @Order(2)
    void read() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        Milestone milestone = Milestone.builder()
                .project(project)
                .name("qeqweq")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        testEntityManager.persist(milestone);
        testEntityManager.flush();
        testEntityManager.clear();

        assertThat(mileStoneRepository.findById(milestone.getId())).contains(milestone);
    }

    @Test
    @DisplayName("수정")
    @Order(3)
    void update() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        Milestone milestone = Milestone.builder()
                .project(project)
                .name("qeqweq")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        testEntityManager.persist(milestone);
        testEntityManager.flush();
        testEntityManager.clear();

        UpdateMilestoneRequest updateMilestoneRequest = UpdateMilestoneRequest.builder()
                .name("test")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        milestone.modifyMilestone(updateMilestoneRequest);

        assertThat(testEntityManager.getId(milestone)).isEqualTo(milestone.getId());
    }

    @Test
    @DisplayName("삭제")
    @Order(4)
    void delete() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        Milestone milestone = Milestone.builder()
                .project(project)
                .name("qeqweq")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        testEntityManager.persist(milestone);
        testEntityManager.flush();
        testEntityManager.clear();

        mileStoneRepository.deleteById(milestone.getId());

        assertThat(mileStoneRepository.findById(milestone.getId())).isNotPresent();
    }

    @Test
    void findAllByProject_Id() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        Milestone milestone1 = Milestone.builder()
                .project(project)
                .name("qeqweq1")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        Milestone milestone2 = Milestone.builder()
                .project(project)
                .name("qeqweq2")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        testEntityManager.persist(milestone1);
        testEntityManager.persist(milestone2);

        assertThat(mileStoneRepository.findAllByProject_Id(project.getId()).size()).isEqualTo(2);
    }

    @Test
    void findMilestoneByProject_IdAndId() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(project);

        Milestone milestone1 = Milestone.builder()
                .project(project)
                .name("qeqweq1")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        testEntityManager.persist(milestone1);

        assertThat(mileStoneRepository
                .findMilestoneByProject_IdAndId(project.getId(), milestone1.getId()))
                .contains(milestone1);
    }
}