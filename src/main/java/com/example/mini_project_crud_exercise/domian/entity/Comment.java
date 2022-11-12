package com.example.mini_project_crud_exercise.domian.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;
    private String name;
    private String comment;

    public Comment(Long articleId, String name, String comment){
        this.articleId = articleId;
        this.name = name;
        this.comment = comment;
    }
}
