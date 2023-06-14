//package com.nhnacademy.westloverock.taskapi.entity;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ActiveProfiles("dev")
//@SpringBootTest
//class CommentTest {
//
//    private Comment comment;
//    private Task task;
//
//    @BeforeEach
//    void setUp() {
//        task = new Task();
//        comment = Comment.builder()
//                .id(1)
//                .task(task)
//                .userId("seohyun")
//                .content("댓글 테스트")
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//    }
//
//    @Test
//    @DisplayName("작업 Getter 테스트")
//    void getTaskTest() {
//        assertEquals(task, comment.getTask());
//    }
//
////    @Test
////    @DisplayName("작업 Setter 테스트")
////    void setTaskTest() {
////        Task newTask = new Task();
////        comment.setTask(newTask);
////        assertEquals(newTask, comment.getTask());
////    }
//
//    @Test
//    @DisplayName("유저Id Getter 테스트")
//    void getUserIdTest() {
//        assertEquals("seohyun", comment.getUserId());
//    }
//
////    @Test
////    @DisplayName("유저Id Setter 테스트")
////    void setUserIdTest() {
////        comment.setUserId("manseok");
////        assertEquals("manseok", comment.getUserId());
////    }
//
//    @Test
//    @DisplayName("내용 Getter 테스트")
//    void getContentTest() {
//        assertEquals("댓글 테스트", comment.getContent());
//    }
//
////    @Test
////    @DisplayName("내용 Setter 테스트")
////    void setContentTest() {
////        comment.setContent("댓글 테스트 업데이트");
////        assertEquals("댓글 테스트 업데이트", comment.getContent());
////    }
//
//    @Test
//    @DisplayName("작성일 Getter 테스트")
//    void getCreatedAtTest() {
//        assertNotNull(comment.getCreatedAt());
//    }
//
////    @Test
////    @DisplayName("작성일 Setter 테스트")
////    void setCreatedAtTest() {
////        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
////        comment.setCreatedAt(newDate);
////        assertEquals(newDate, comment.getCreatedAt());
////    }
//
//    @Test
//    @DisplayName("수정일 Getter 테스트")
//    void getUpdatedAtTest() {
//        assertNotNull(comment.getUpdatedAt());
//    }
//
////    @Test
////    @DisplayName("수정일 Setter 테스트")
////    void setUpdatedAtTest() {
////        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
////        comment.setUpdatedAt(newDate);
////        assertEquals(newDate, comment.getUpdatedAt());
////    }
//}
