package com.ulegalize.controller;

import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import com.ulegalize.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/comments")
@Slf4j
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getComments(){
        return commentService.getAllComments();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Comment addComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }


}