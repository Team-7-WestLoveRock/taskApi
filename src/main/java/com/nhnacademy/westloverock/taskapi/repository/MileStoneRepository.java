package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MileStoneRepository extends JpaRepository<Milestone, Integer> {
    List<MilestoneResponseDto> findAllByProject_Id(Integer projectId);

    Optional<Milestone> findMilestoneByProject_IdAndId(Integer projectId, Integer milestoneId);

    List<Milestone> findByProjectId(int projectId);
}