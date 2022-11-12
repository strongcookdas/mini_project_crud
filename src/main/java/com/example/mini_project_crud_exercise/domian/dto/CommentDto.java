package com.example.mini_project_crud_exercise.domian.dto;

import com.example.mini_project_crud_exercise.domian.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CommentDto {

    private Long articleId;
    private String name;
    private String comment;

    public Comment toEntity(){
        return new Comment(this.articleId,this.name,this.comment);
    }
}
