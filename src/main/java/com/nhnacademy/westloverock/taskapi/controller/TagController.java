package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.dto.TagUpdateRequest;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/api/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping("/{projectId}")
    public ResponseEntity<TagDto> createTag(@PathVariable int projectId, @RequestBody TagDto tagDto) {
        try {
            TagDto createdTag = tagService.createTag(projectId, tagDto);
            return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<Void> updateTag(@PathVariable int id, @RequestBody TagUpdateRequest newTagData) {
        try {
            tagService.updateTag(id, newTagData);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }
}
