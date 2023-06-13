package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.CreateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.entity.Milestone;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.repository.MileStoneRepository;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;

    public List<MilestoneResponseDto> findAllMilestone(Integer projectId) {
        return mileStoneRepository.findAllByProject_Id(projectId);
    }

    public void createMilestone(Integer projectId, CreateMilestoneRequest createMilestoneRequest) {
        Project project = projectRepository
                .findProjectById(projectId)
                .orElseThrow(() ->
                        new NoSuchElementException("아이디에 해당하는 프로젝트 없음"));
        Milestone milestone = Milestone.builder()
                .project(project)
                .name(createMilestoneRequest.getName())
                .startDate(createMilestoneRequest.getStartDate())
                .endDate(createMilestoneRequest.getEndDate())
                .build();
        mileStoneRepository.save(milestone);
    }

    public void updateMilestone(Integer projectId, Integer milestoneId, UpdateMilestoneRequest updateMilestoneRequest) {
        Milestone milestone = mileStoneRepository
                .findMilestoneByProject_IdAndId(projectId, milestoneId)
                .orElseThrow(() ->
                        new NoSuchElementException("해당하는 프로젝트id와 milestoneId와 일치하는 milestone이 없습니다."));

        milestone.modifyMilestone(updateMilestoneRequest);

        mileStoneRepository.save(milestone);
    }

    public void deleteMilestone(Integer projectId, Integer milestoneId) {
        Milestone milestone = mileStoneRepository
                .findMilestoneByProject_IdAndId(projectId, milestoneId)
                .orElseThrow(() ->
                        new NoSuchElementException("해당하는 프로젝트id와 milestoneId와 일치하는 milestone이 없습니다."));

        mileStoneRepository.delete(milestone);
    }
}
