package com.example.mini_project_crud_exercise.repository;

import com.example.mini_project_crud_exercise.domian.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
