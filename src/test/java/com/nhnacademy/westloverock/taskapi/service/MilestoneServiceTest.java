package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.CreateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.entity.Milestone;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.repository.MileStoneRepository;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.setMaxLengthForSingleLineDescription;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MilestoneServiceTest {

    @Mock
    MileStoneRepository mileStoneRepository;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    MilestoneService milestoneService;

    @Test
    @DisplayName("모든 마일스톤 조회")
    void findAllMilestone() {
        MilestoneResponseDto milestoneResponseDto = MilestoneResponseDto.builder()
                .name("qqq")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        List<MilestoneResponseDto> milestoneResponseDtoList = new ArrayList<>();
        milestoneResponseDtoList.add(milestoneResponseDto);

        when(mileStoneRepository.findAllByProject_Id(any()))
                .thenReturn(milestoneResponseDtoList);

        assertThat(milestoneService.findAllMilestone(any()).size()).isEqualTo(1);
    }

    @Test
    @DisplayName("마일스톤 저장 - 정상")
    void createMilestone() {
        Project project = Project.builder()
                .name("qwe")
                .description("asdasd")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        when(projectRepository.findProjectById(any())).thenReturn(Optional.ofNullable(project));

        CreateMilestoneRequest createMilestoneRequest = CreateMilestoneRequest.builder()
                .name("qqqqq")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        milestoneService.createMilestone(project.getId(), createMilestoneRequest);
        verify(mileStoneRepository).save(any(Milestone.class));
    }


    @Test
    @DisplayName("마일스톤 저장 - 실패")
    void createMilestoneNotFoundMilestone() {

        when(projectRepository.findProjectById(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                milestoneService.createMilestone(1, new CreateMilestoneRequest()));

    }
    @Test
    @DisplayName("마일스톤 수정 - 정상")
    void updateMilestone() {
        Project project = Project.builder()
                .name("project")
                .description("project")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        Milestone milestone = Milestone.builder()
                .project(project)
                .name("milestone")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        when(mileStoneRepository.findMilestoneByProject_IdAndId(any(), any()))
                .thenReturn(Optional.of(milestone));


        UpdateMilestoneRequest updateMilestoneRequest = UpdateMilestoneRequest.builder()
                .name("updateMilestone")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        milestoneService.updateMilestone(project.getId(), milestone.getId(), updateMilestoneRequest);

        verify(mileStoneRepository).findMilestoneByProject_IdAndId(any(), any());
        assertThat(milestone.getName()).isEqualTo("updateMilestone");
    }

    @Test
    @DisplayName("마일스톤 수정 - 실패")
    void updateMilestoneNoSuchElement() {
        when(mileStoneRepository.findMilestoneByProject_IdAndId(any(), any()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                milestoneService.updateMilestone(1, 1, new UpdateMilestoneRequest()));
    }

    @Test
    @DisplayName("마일스톤 삭제 - 성공")
    void deleteMilestone() {
        Project project = Project.builder()
                .name("project")
                .description("project")
                .state("종료")
                .createAt(LocalDateTime.now())
                .build();

        Milestone milestone = Milestone.builder()
                .project(project)
                .name("milestone")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        when(mileStoneRepository.findMilestoneByProject_IdAndId(any(), any())).thenReturn(Optional.of(milestone));

        milestoneService.deleteMilestone(project.getId(), milestone.getId());

        assertThat(mileStoneRepository.findById(milestone.getId())).isNotPresent();
    }

    @Test
    @DisplayName("마일스톤 삭제 - 실패")
    void deleteMilestoneNoSuchElement() {

        when(mileStoneRepository.findMilestoneByProject_IdAndId(any(), any())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
                milestoneService.deleteMilestone(1, 1));
    }
}