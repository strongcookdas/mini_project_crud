package com.example.mini_project_crud_exercise.controller;

import com.example.mini_project_crud_exercise.domian.dto.ArticleDto;
import com.example.mini_project_crud_exercise.domian.entity.Article;
import com.example.mini_project_crud_exercise.repository.ArticleRepository;
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

    public BoardController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String writeArticleForm() {
        return "write";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "error";
        else {
            model.addAttribute("article", optArticle.get());
            return "show";
        }
    }

    @GetMapping("")
    public String listArticle(Model model){
        List<Article> list = articleRepository.findAll();
        model.addAttribute("articles",list);
        return "list";
    }

    @GetMapping("/{id}/edit")
    public String editArticleForm(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "error";
        else {
            model.addAttribute("article", optArticle.get());
            return "edit";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rtts) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "error";
        else {
            articleRepository.deleteById(optArticle.get().getId());
            rtts.addAttribute("msg",optArticle.get().getId()+"번째 게시글이 삭제되었습니다.");
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
}
