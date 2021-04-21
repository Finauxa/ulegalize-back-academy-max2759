package com.ulegalize.controller;

import com.ulegalize.model.Comment;
import com.ulegalize.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@Slf4j
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getComments(){
        return commentService.getAllComments();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Comment addComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }


}