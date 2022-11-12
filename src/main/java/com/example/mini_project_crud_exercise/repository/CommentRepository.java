package com.example.mini_project_crud_exercise.repository;

import com.example.mini_project_crud_exercise.domian.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByArticleId(Long articleId);
    @Transactional
    void deleteAllByArticleId(Long articleId);
}
