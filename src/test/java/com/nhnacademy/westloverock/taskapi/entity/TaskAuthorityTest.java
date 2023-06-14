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
class TaskAuthorityTest {

    private TaskAuthority taskAuthority;
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        TaskAuthority.Pk pk = new TaskAuthority.Pk(1, "seohyun");
        taskAuthority = TaskAuthority.builder()
                .id(pk)
                .task(task)
                .authority("Admin")
                .build();
    }

    @Test
    @DisplayName("작업 Getter 테스트")
    void getTaskTest() {
        assertEquals(task, taskAuthority.getTask());
    }

    @Test
    @DisplayName("권한 Getter 테스트")
    void getAuthorityTest() {
        assertEquals("Admin", taskAuthority.getAuthority());
    }

    @Test
    @DisplayName("아이디 Getter 테스트")
    void getIdTest() {
        assertNotNull(taskAuthority.getId());
    }

}