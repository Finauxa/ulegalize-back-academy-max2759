package com.ulegalize.service.impl;

import com.ulegalize.converter.CommentDTOToCommentConverter;
import com.ulegalize.converter.CommentToCommentDTOConverter;
import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import com.ulegalize.repository.CommentRepository;
import com.ulegalize.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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

    public void deleteComment(int id){
        Optional<Comment> commentById = commentRepository.findById(id);

        if(commentById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }

        commentRepository.delete(commentById.get());
    }


    public CommentDTO updateComment(CommentDTO commentDTO){

        if (commentDTO == null) {
            log.warn("comment is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comment is not found");
        }

        if (commentDTO.getId() == 0) {
            log.warn("comment is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comment is not found");
        }

        log.debug("comment: {}, commentId: {}", commentDTO, commentDTO.getId());

        Optional<Comment> commentById = commentRepository.findById(commentDTO.getId());

        if (commentById.isEmpty()) {
            log.warn("comment is not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "comment is not found");
        }

        Comment comment = commentDTOToCommentConverter.apply(commentDTO);

        LocalDateTime lt = LocalDateTime.now();
        comment.setDate(lt);

        commentRepository.save(comment);

        return commentDTO;

    }

}
