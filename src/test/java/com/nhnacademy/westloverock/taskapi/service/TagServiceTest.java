package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Tag;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import com.nhnacademy.westloverock.taskapi.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import com.nhnacademy.westloverock.taskapi.dto.TagUpdateRequest;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TagService tagService;

    private Tag tag;
    private TagDto tagDto;
    private TagUpdateRequest tagUpdateRequest;

    @BeforeEach
    public void setup() {
        Project project = new Project(1, "Test Project","This is a test project.", "진행", LocalDateTime.now());
        tag = new Tag(1, project, "Test Tag", "Blue");

        tagDto = new TagDto();
        tagDto.setId(1);
        tagDto.setName("Test Tag");
        tagDto.setColor("Blue");

        tagUpdateRequest = new TagUpdateRequest();
        tagUpdateRequest.setName("Updated Test Tag");
        tagUpdateRequest.setColor("Red");
    }

    @Test
    void createTagTest() {
        TagDto tagDto = new TagDto();
        tagDto.setName("Test Tag");
        tagDto.setColor("Blue");

        given(tagRepository.save(any(Tag.class))).willReturn(tag);
        given(projectRepository.findById(anyInt())).willReturn(Optional.of(new Project()));

        TagDto createdTag = tagService.createTag(1, tagDto);

        assertEquals(createdTag.getName(), tagDto.getName());
        assertEquals(createdTag.getColor(), tagDto.getColor());
    }

    @Test
    void findAllTagsTest() {
        given(tagRepository.findAll()).willReturn(Arrays.asList(tag));

        List<TagDto> tagDtos = tagService.findAllTags();
        assertEquals(1, tagDtos.size());
        assertEquals(tagDto.getName(), tagDtos.get(0).getName());
    }

    @Test
    void findTagByIdTest() {
        given(tagRepository.findTagById(1)).willReturn(Optional.of(tag));

        TagDto foundTagDto = tagService.findTagById(1);
        assertEquals(foundTagDto.getName(), tagDto.getName());
    }

    @Test
    void updateTagTest() {
        TagUpdateRequest tagUpdateRequest = new TagUpdateRequest();
        tagUpdateRequest.setName("New Tag");
        tagUpdateRequest.setColor("green");
        Project project = new Project(1, "Test Project","This is a test project.", "진행", LocalDateTime.now());

        Tag tag = new Tag("Old Tag", "red", project);
        given(tagRepository.findById(1)).willReturn(Optional.of(tag));

        tagService.updateTag(1, tagUpdateRequest);

        ArgumentCaptor<Tag> captor = ArgumentCaptor.forClass(Tag.class);
        verify(tagRepository).save(captor.capture());

        Tag updatedTag = captor.getValue();
        assertEquals("New Tag", updatedTag.getName());
        assertEquals("green", updatedTag.getColor());
    }


    @Test
    void deleteTagTest() {
        tagService.deleteTag(1);
        verify(tagRepository).deleteById(1);
    }
}
