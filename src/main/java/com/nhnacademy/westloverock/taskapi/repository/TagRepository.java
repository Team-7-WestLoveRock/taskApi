package com.nhnacademy.westloverock.taskapi.repository;

import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findTagById(Integer id);
    List<Tag> findByProjectId(int projectId);

}