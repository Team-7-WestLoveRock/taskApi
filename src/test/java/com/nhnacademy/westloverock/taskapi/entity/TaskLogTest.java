package com.nhnacademy.westloverock.taskapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@SpringBootTest
class TaskLogTest {

    private TaskLog taskLog;
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        LocalDate updateDate = LocalDate.now();
        TaskLog.Pk pk = new TaskLog.Pk(updateDate, 1);
        taskLog = TaskLog.builder()
                .id(pk)
                .task(task)
                .build();
    }

    @Test
    @DisplayName("작업 Getter 테스트")
    void getTaskTest() {
        assertEquals(task, taskLog.getTask());
    }
    @Test
    @DisplayName("작업 Setter 테스트")
    void setTaskTest() {
        Task newTask = new Task();
        taskLog.setTask(newTask);
        assertEquals(newTask, taskLog.getTask());
    }

    @Test
    @DisplayName("아이디 Getter 테스트")
    void getIdTest() {
        assertNotNull(taskLog.getId());
    }

    @Test
    @DisplayName("아이디 Setter 테스트")
    void setIdTest() {
        TaskLog.Pk newPk = new TaskLog.Pk(LocalDate.now(), 2);
        taskLog.setId(newPk);
        assertEquals(newPk, taskLog.getId());
    }
}
