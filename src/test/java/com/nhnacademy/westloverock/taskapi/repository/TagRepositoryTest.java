package com.nhnacademy.westloverock.taskapi.repository;


import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@ActiveProfiles("dev")
@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void findTagByIdTest() {
        Project project = new Project(1, "Test Project","This is a test project.", "진행", LocalDateTime.now());
        project = projectRepository.save(project);

        Tag tag = new Tag("Tag name", "Tag color", project);
        tag = tagRepository.save(tag);

        Optional<Tag> foundTag = tagRepository.findTagById(tag.getId());

        assertThat(foundTag).isNotEmpty();
        assertThat(foundTag.get()).isEqualTo(tag);
    }

    @Test
    public void findByProjectIdTest() {
        Project project = new Project(1, "Test Project","This is a test project.", "진행", LocalDateTime.now());
        project = projectRepository.save(project);

        Tag tag1 = new Tag("Tag name1", "Tag color1", project);
        Tag tag2 = new Tag("Tag name2", "Tag color2", project);

        tag1 = tagRepository.save(tag1);
        tag2 = tagRepository.save(tag2);

        List<Tag> foundTags = tagRepository.findByProjectId(project.getId());

        assertThat(foundTags).hasSize(2);
        assertThat(foundTags).containsExactlyInAnyOrder(tag1, tag2);
    }
}