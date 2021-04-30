package com.ulegalize.service.impl;

import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@Rollback
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

   @Autowired
   protected TestEntityManager testEntityManager;

   @Test
    public void test_A_getAllComments(){
      CommentDTO commentDTO = new CommentDTO();

      commentDTO.setComment("test");
      commentDTO.setEmail("test@test.be");
      commentDTO.setGender("Female");

      List<CommentDTO> commentDTOList = commentService.getAllComments();

      assertNotNull(commentDTOList);

   }

   @Test
    public void test_B_createComment(){
       CommentDTO commentDTO = new CommentDTO();

       commentDTO.setEmail("Test@test.be");
       commentDTO.setGender("Male");
       commentDTO.setComment("Test");

       CommentDTO addComment = commentService.createComment(commentDTO);

       assertNotNull(addComment);
       assertEquals(addComment.getComment(), "Test");
   }

   @Test
   public void test_C_deleteCommentById(){

      Comment comment = new Comment();

      comment.setEmail("delete@test.be");
      comment.setGender("Male");
      comment.setComment("Test del");
      comment.setDate(LocalDateTime.now());

      testEntityManager.persist(comment);

      commentService.deleteComment(comment.getId());

      Comment commentDeleted = testEntityManager.find(Comment.class, comment.getId());

      assertNull(commentDeleted);
   }


}