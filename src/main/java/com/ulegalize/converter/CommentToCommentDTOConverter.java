package com.ulegalize.converter;

import com.ulegalize.dto.CommentDTO;
import com.ulegalize.model.Comment;
import com.ulegalize.utils.SuperConverter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTOConverter implements SuperConverter<Comment, CommentDTO> {

    @Override
    public CommentDTO apply(Comment comment) {

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setGender(comment.getGender());
        commentDTO.setComment(comment.getComment());

        return commentDTO;
    }
}
