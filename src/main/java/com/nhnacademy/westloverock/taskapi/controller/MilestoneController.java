package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.CreateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project/api/projects")
@RequiredArgsConstructor
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("/{projectId}/milestones")
    public HttpEntity<List<MilestoneResponseDto>> findAllMilestone(@PathVariable Integer projectId) {
        return new ResponseEntity<>(milestoneService.findAllMilestone(projectId), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/milestone")
    public HttpEntity<Void> createMilestone(@PathVariable Integer projectId, @RequestBody @Valid CreateMilestoneRequest createMilestoneRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("요청 형식이 잘못됨");
        }

        milestoneService.createMilestone(projectId, createMilestoneRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}/milestone/{milestoneId}")
    public HttpEntity<Void> updateMilestone(@PathVariable(value = "projectId") Integer projectId,
                                            @PathVariable(value = "milestoneId") Integer milestoneId,
                                            @RequestBody @Valid UpdateMilestoneRequest updateMilestoneRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("요청 형식이 잘못됨");
        }

        milestoneService.updateMilestone(projectId, milestoneId, updateMilestoneRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/milestone/{milestoneId}")
    public HttpEntity<Void> deleteMilestone(@PathVariable(value = "milestoneId") Integer milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
