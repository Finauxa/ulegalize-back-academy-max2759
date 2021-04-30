package com.ulegalize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@Rollback
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected TestEntityManager testEntityManager;


    @Test
    public void test_A_getAllComments() throws Exception{

        Comment comment = new Comment();

        comment.setComment("test");
        comment.setEmail("max@test.be");
        comment.setGender("female");
        comment.setDate(LocalDateTime.now());

        testEntityManager.persist(comment);

        mockMvc.perform(get("/v1/comments"))
                .andExpect(jsonPath("$[0].comment", is(comment.getComment())))
                .andExpect(status().isOk());

    }

    @Test
    public void test_B_createComment() throws Exception{
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setComment("test");
        commentDTO.setEmail("max@test.be");
        commentDTO.setGender("Male");

        mockMvc.perform(post("/v1/comments")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isOk());

    }

    @Test
    public void test_C_deleteComment() throws Exception {
        Comment comment = new Comment();

        comment.setComment("Test delete");
        comment.setGender("Male");
        comment.setDate(LocalDateTime.now());
        comment.setEmail("testdel@delete.be");

        testEntityManager.persist(comment);

        mockMvc.perform(delete("/v1/comments/" + comment.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}