package com.ulegalize.service.impl;

import com.ulegalize.model.Comment;
import com.ulegalize.repository.CommentRepository;
import com.ulegalize.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments()
    {
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment){

        LocalDateTime lt = LocalDateTime.now();
        comment.setDate(lt);
        return commentRepository.save(comment);
    }
}
