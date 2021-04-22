package com.ulegalize.service.impl;

import com.ulegalize.converter.CommentDTOToCommentConverter;
import com.ulegalize.converter.CommentToCommentDTOConverter;
import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import com.ulegalize.repository.CommentRepository;
import com.ulegalize.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private CommentToCommentDTOConverter commentToCommentDTOConverter;
    private final CommentDTOToCommentConverter commentDTOToCommentConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentToCommentDTOConverter commentToCommentDTOConverter, CommentDTOToCommentConverter commentDTOToCommentConverter) {
        this.commentRepository = commentRepository;
        this.commentToCommentDTOConverter = commentToCommentDTOConverter;
        this.commentDTOToCommentConverter = commentDTOToCommentConverter;
    }

    public List<CommentDTO> getAllComments(){
        log.info("Début getAllComments()");
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = commentToCommentDTOConverter.convertToList(commentList);

        return commentDTOList;
    }


    public CommentDTO createComment(CommentDTO commentDTO){

        log.info("Début createComment()");

        Comment comment = commentDTOToCommentConverter.apply(commentDTO);

        LocalDateTime lt = LocalDateTime.now();
        comment.setDate(lt);

        commentRepository.save(comment);

        return commentToCommentDTOConverter.apply(comment);
    }
}
