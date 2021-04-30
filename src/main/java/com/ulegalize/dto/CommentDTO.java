package com.ulegalize.dto;

import com.ulegalize.model.Comment;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDTO implements Serializable {

    private int id;
    private String email;
    private String comment;
    private String gender;


}
