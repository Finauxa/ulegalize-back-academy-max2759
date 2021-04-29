package com.ulegalize.controller;

import com.ulegalize.dto.CommentDTO;
import com.ulegalize.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"} )
@RestController
@RequestMapping("v1/comments")
@Slf4j
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getComments(){
        log.debug("getComments() from CommentController");
        return commentService.getAllComments();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO){
        log.debug("addComment() from CommentController", commentDTO);

        return commentService.createComment(commentDTO);
    }

    @DeleteMapping(value = "/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        log.debug("deleteComment() from CommentController");

        commentService.deleteComment(commentId);
    }


}