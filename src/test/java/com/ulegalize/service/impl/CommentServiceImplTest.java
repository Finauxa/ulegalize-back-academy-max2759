package com.ulegalize.service.impl;

import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@Rollback
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

   @Test
    public void test_A_getAllComments(){
       Comment comment = new Comment();

       comment.setEmail("Test@test.be");
       comment.setGender("Male");
       comment.setComment("Test");

       List<CommentDTO> commentDTOList = commentService.getAllComments();

       assertNotNull(commentDTOList);
       assertTrue(commentDTOList.size() > 0);
   }

   @Test
    public void test_B_createComment(){
       CommentDTO comment = new CommentDTO();

       comment.setEmail("Test@test.be");
       comment.setGender("Male");
       comment.setComment("Test");

       CommentDTO addComment = commentService.createComment(comment);
       assertNotNull(addComment);
   }


}