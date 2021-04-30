package com.ulegalize.converter;

import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import com.ulegalize.utils.SuperConverter;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentConverter implements SuperConverter<CommentDTO, Comment> {

    @Override
    public Comment apply(CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getComment());
        comment.setEmail(commentDTO.getEmail());
        comment.setGender(commentDTO.getGender());

        return comment;
    }
}
