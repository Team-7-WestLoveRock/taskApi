package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findProjectById(Integer id);
}
