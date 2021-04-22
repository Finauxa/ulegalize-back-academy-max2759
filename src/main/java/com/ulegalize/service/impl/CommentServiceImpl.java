package com.ulegalize.service.impl;

import com.ulegalize.converter.CommentToCommentDTOConverter;
import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import com.ulegalize.repository.CommentRepository;
import com.ulegalize.service.ICommentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommentServiceImpl implements ICommentService {



    private final CommentRepository commentRepository;
    private CommentToCommentDTOConverter commentToCommentDTOConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentToCommentDTOConverter commentToCommentDTOConverter) {
        this.commentRepository = commentRepository;
        this.commentToCommentDTOConverter = commentToCommentDTOConverter;
    }

//    public List<Comment> getAllComments()
//    {
//        //new commentDTO
//        return commentRepository.findAll();
//    }


    public List<CommentDTO> getAllComments(){
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = commentToCommentDTOConverter.convertToList(commentList);

        return commentDTOList;
    }

    public Comment createComment(Comment comment){

        LocalDateTime lt = LocalDateTime.now();
        comment.setDate(lt);
        return commentRepository.save(comment);
    }
}
