package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.entity.Milestone;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DataJpaTest
class MileStoneRepositoryTest {
    @Autowired
    TestEntityManager em;

    @Autowired
    MileStoneRepository mileStoneRepository;

    @Test
    void findAllById() {
        Project project = Project.builder()
                .name("test")
                .description("test")
                .state("보관")
                .createAt(LocalDateTime.now())
                .build();


        Milestone milestone = Milestone.builder()
                .project(project)
                .name("두레이 이해하기")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .build();

        mileStoneRepository.save(milestone);

        assertThat(mileStoneRepository.findAllByProject_Id(1)).hasSize(1);
    }
}