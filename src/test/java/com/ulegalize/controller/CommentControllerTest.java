package com.ulegalize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulegalize.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void test_A_getAllComments() throws Exception{
        mockMvc.perform(get("/v1/comments"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_B_createComment() throws Exception{
        Comment comment = new Comment();

        comment.setComment("test");
        comment.setEmail("max@test.be");
        comment.setGender("Male");

        mockMvc.perform(post("/v1/comments")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk());

    }
}