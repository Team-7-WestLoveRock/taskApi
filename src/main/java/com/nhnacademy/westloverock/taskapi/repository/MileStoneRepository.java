package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.entity.Milestone;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MileStoneRepository extends JpaRepository<Milestone, Integer> {
    List<MilestoneResponseDto> findAllByProject_Id(Integer projectId);

    Optional<Milestone> findMilestoneByProject_IdAndId(Integer projectId, Integer milestoneId);
}