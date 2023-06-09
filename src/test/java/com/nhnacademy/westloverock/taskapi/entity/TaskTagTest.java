package com.nhnacademy.westloverock.taskapi.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@SpringBootTest
class TaskTagTest {

    private TaskTag taskTag;
    private Task task;
    private Tag tag;

    @BeforeEach
    void setUp() {
        task = new Task();
        tag = new Tag();
        TaskTag.Pk pk = new TaskTag.Pk(1, 1);
        taskTag = TaskTag.builder()
                .id(pk)
                .task(task)
                .tag(tag)
                .build();
    }

    @Test
    @DisplayName("작업 Getter 테스트")
    void getTaskTest() {
        assertEquals(task, taskTag.getTask());
    }

    @Test
    @DisplayName("태그 Getter 테스트")
    void getTagTest() {
        assertEquals(tag, taskTag.getTag());
    }

    @Test
    @DisplayName("아이디 Getter 테스트")
    void getIdTest() {
        assertNotNull(taskTag.getId());
    }

}