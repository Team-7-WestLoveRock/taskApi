package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.dto.TagUpdateRequest;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Tag;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import com.nhnacademy.westloverock.taskapi.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    ModelMapper modelMapper = new ModelMapper();


    public TagService(TagRepository tagRepository, ProjectRepository projectRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public TagDto createTag(int projectId, TagDto tagDto) {
        if (StringUtils.isEmpty(tagDto.getName()) || StringUtils.isEmpty(tagDto.getColor())) {
            throw new IllegalArgumentException("태그는 이름과 색상이 필요합니다.");
        }

        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if(projectOptional.isEmpty()) {
            throw new IllegalArgumentException("프로젝트 id : " + projectId + "번을 찾을 수 없습니다.");
        }

        Project project = projectOptional.get();
        Tag tag = new Tag(tagDto.getName(), tagDto.getColor(), project);
        Tag savedTag = tagRepository.save(tag);

        tagDto.setId(savedTag.getId());
        tagDto.setProjectId(project.getId());

        return tagDto;
    }

    public List<TagDto> findAllTags() {
        return tagRepository.findAll().stream()
                .map(Tag::toDto)
                .collect(Collectors.toList());
    }

    public TagDto findTagById(int id) {
        return tagRepository.findTagById(id)
                .map(Tag::toDto)
                .orElseThrow(() -> new IllegalArgumentException("태그 id : " + id + "번을 찾을 수 없습니다."));
    }

    public void deleteTag(int id) {
        tagRepository.deleteById(id);
    }


    public void updateTag(int id, TagUpdateRequest newTagData) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("태그 id : " + id + "번을 찾을 수 없습니다."));

        if (newTagData.getName() != null && !newTagData.getName().isEmpty()) {
            tag.update(newTagData.getName(), newTagData.getColor());
        }

        tagRepository.save(tag);
    }

    public List<TagDto> findByProjectId(int projectId) {
        List<Tag> tags = tagRepository.findByProjectId(projectId);
        return tags.stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }
}