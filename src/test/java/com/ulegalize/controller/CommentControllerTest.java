package com.ulegalize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulegalize.dto.CommentDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        CommentDTO comment = new CommentDTO();

        comment.setId(1);
        comment.setComment("test");
        comment.setEmail("max@test.be");
        comment.setGender("female");


        mockMvc.perform(get("/v1/comments"))
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
}