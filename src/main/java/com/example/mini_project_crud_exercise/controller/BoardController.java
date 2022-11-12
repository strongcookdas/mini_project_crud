package com.example.mini_project_crud_exercise.controller;

import com.example.mini_project_crud_exercise.domian.dto.ArticleDto;
import com.example.mini_project_crud_exercise.domian.dto.CommentDto;
import com.example.mini_project_crud_exercise.domian.entity.Article;
import com.example.mini_project_crud_exercise.domian.entity.Comment;
import com.example.mini_project_crud_exercise.repository.ArticleRepository;
import com.example.mini_project_crud_exercise.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public BoardController(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/new")
    public String writeArticleForm() {
        return "articles/write";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        List<Comment> listComment = commentRepository.findByArticleId(id);
        if (optArticle.isEmpty())
            return "articles/error";
        else {
            model.addAttribute("article", optArticle.get());
            model.addAttribute("comments",listComment);
            return "articles/show";
        }
    }

    @GetMapping("")
    public String listArticle(Model model){
        List<Article> list = articleRepository.findAll();
        model.addAttribute("articles",list);
        return "articles/list";
    }

    @GetMapping("/{id}/edit")
    public String editArticleForm(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "articles/error";
        else {
            model.addAttribute("article", optArticle.get());
            return "articles/edit";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rtts) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "articles/error";
        else {
            articleRepository.deleteById(optArticle.get().getId());
            commentRepository.deleteAllByArticleId(id);
            rtts.addFlashAttribute("msg",optArticle.get().getId()+"번째 게시글이 삭제되었습니다.");
            return "redirect:/board";
        }
    }

    @PostMapping("/post")
    public String saveArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article article = articleDto.toEntity();
        articleRepository.save(article);
        return "redirect:/board/" + article.getId();
    }

    @PostMapping("/{id}/upadate")
    public String updateArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article article = articleDto.toEntity();
        articleRepository.save(article);
        return "redirect:/board/" + article.getId();
    }

    @PostMapping(value = "/comment/post")
    public String saveComment(CommentDto commentDto){
        log.info(commentDto.toString());
        Comment comment = commentDto.toEntity();
        commentRepository.save(comment);
        return "redirect:/board/"+commentDto.getArticleId();
    }
}
