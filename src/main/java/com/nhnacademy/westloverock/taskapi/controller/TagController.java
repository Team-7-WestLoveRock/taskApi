package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.dto.TagUpdateRequest;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<TagDto> createTag(@PathVariable int projectId, @RequestBody TagDto tagDto) {
        TagDto createdTag = tagService.createTag(projectId, tagDto);
        return ResponseEntity.ok(createdTag);
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> findAllTags() {
        return ResponseEntity.ok(tagService.findAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto>  findTagById(@PathVariable int id) {
        return ResponseEntity.ok(tagService.findTagById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable int id, @RequestBody TagUpdateRequest newTagData) {
        TagDto updatedTag = tagService.updateTag(id, newTagData);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }
}
