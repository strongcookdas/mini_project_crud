# ๐ข CRUD ์ ์

## ๋ชฉํ

<aside>
๐ก REST API๋ฅผ ์ดํดํ๊ณ  ์ด๋ฅผ ํ์ฉํ์ฌ CRUD ๊ฒ์ํ์ ์ ์ํ  ์ ์๋ค.

</aside>

## ๊ฐ์
- ํ๋ก์ ํธ ๋น๋
- ๊ฒ์๊ธ ์์ฑ ๊ธฐ๋ฅ ๊ตฌํ
- ๊ฒ์๊ธ ์ถ๋ ฅ ๊ธฐ๋ฅ ๊ตฌํ
- ๊ฒ์๊ธ ๋ฆฌ์คํธ ์ถ๋ ฅ ๊ธฐ๋ฅ ๊ตฌํ
- ๊ฒ์๊ธ ์์  ๊ธฐ๋ฅ ๊ตฌํ
- ๊ฒ์๊ธ ์ญ์  ๊ธฐ๋ฅ ๊ตฌํ
- ๊ฒ์๊ธ ๋๊ธ ๊ธฐ๋ฅ ๊ตฌํ
- ํ์ด์ง ๊ธฐ๋ฅ ๊ตฌํ
- DOCKER ๋ฐฐํฌ

## ํ๋ก์ ํธ ๋น๋

### 1. ํ๋ก์ ํธ ์์ฑ

![img.png](image/img.png)
![img_1.png](image/img_1.png)

### 2. yml ํ์ผ ์ค์ 

```yaml
#server ํฌํธ ์ง์ , ์ธ์ฝ๋ฉ ์ค์ 
server:
  port: 8082
  servlet:
    encoding:
      force-response: true

#jpa datasource ์ค์ 
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${DB_HOST}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
    
#jpa DB ์ค์ 
#ddl-auto ์ค์  ์ ์ฃผ์ํด์ผํ  ์ฌํญ์ ํ๋ฒ ์คํ ํ create๋ฅผ vaidate๋ update๋ก ๋ณ๊ฒฝ
#create ์ค์ ์ ์ฌ์คํ์ table ์ญ์ ํ ๋ค์ ์ฌ์์ฑํ๋ฏ๋ก ๋ฐ์ดํฐ๊ฐ ๋ ๋ผ๊ฐ๋ค.
  jpa:
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
    database: mysql
    hibernate:
      ddl-auto: create
```

- `show-sql: true` - ์ ์คํ๋๋์ง ๋ฏธ๋ฆฌ ์ ์ ์๋ค.
- `ddl-auto: create` - ์ฃผ์ํ์ฌ ์ฌ์ฉ, ์ฒ์์๋ง create ์ฌ์ฉํ๊ณ  ๋ค์๋ถํฐ๋ validate๋ update

### 3. ํ๊ฒฝ๋ณ์ ์ค์ 

![img_2.png](image/img_2.png)
ํ๊ฒฝ๋ณ์ ์ถ๊ฐํด์ค์ผ ํ๋ค.

- ํ๊ฒฝ๋ณ์ ์ถ๊ฐ
    - `DB_HOST`
    - `DB_USER`
    - `DB_PASSWORD`

## ๊ฒ์๊ธ ์์ฑ ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_3.png](image/img_3.png)

### 1. ๊ฒ์๊ธ ์์ฑ view ๊ตฌํ

<aside>
๐ก view๋ ๋ถํธ์คํธ๋ฉ๊ณผ mustache๋ก ๊ตฌํํ๋ค.
๋ถํธ์คํธ๋ฉ : [https://getbootstrap.com/](https://getbootstrap.com/)

</aside>

- header.mustache_๋ค๋น๊ฒ์ด์ ๋ฐ ์ถ๊ฐ

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Board</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Write</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
```

- footer.mustache

```html
<div class="mb-5 container-fluid">
    <hr>
    <p>@ jisuBoard <a href="#">Privacy</a> <a href="#">Terms</a></p>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>
```

- write.mustache_title ์์ฑ๋, content ์์ฑ๋, ์ ์ถ๋ฒํผ์ผ๋ก ๊ตฌ์ฑ

```html
{{>layouts/header}}
<form class="container" action = "/board/post" method="post">
    <div class="mb-3">
        <label class="form-label">Title</label>
        <input class="form-control" name="title">
    </div>
    <div class="mb-3">
        <label class="form-label">Content</label>
        <textarea class="form-control" name="content" style="height: 100px"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
{{>layouts/footer}}
```

### 2. view์์ controller๋ก ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ  DTO ํด๋์ค ๊ตฌํ

```java
package com.example.mini_project_crud_exercise.domian.dto;

import com.example.mini_project_crud_exercise.domian.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ArticleDto {
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(this.title, this.content);
    }
}
```

- DTO๋ ๊ณ์ธต ๊ฐ ๋ฐ์ดํฐ ๊ตํ์ด ์ด๋ฃจ์ด์ง ์ ์๋๋ก ๋์์ฃผ๋ ๊ฐ์ฒด์ด๋ค.
- view์์ controller๋ก ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ  ๋ ์ฌ์ฉํ๋ค.

### 3. controller์์ DB๋ก data๋ฅผ ์ ๋ฌํ  Entity ํด๋์ค ๊ตฌํ

```java
package com.example.mini_project_crud_exercise.domian.entity;

import com.example.mini_project_crud_exercise.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board")
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
```

- ์ฃผ์ ์ด๋ธํ์ด์
    - `@Entity` : JPA์์ DB์์ ๋ค๋ฃฐ ์ ๋ณด๋ฅผ ๊ฐ์ฒด๋ก ๊ด๋ฆฌํ๋ค.
    - `@Table` : DB์ ์ด๋ค TABLE๊ณผ mappingํ  ๊ฒ์ธ์ง ์ ํ๋ค.
    - `@Id` : Entity ์ด๋ธํ์ด์์ ์ฌ์ฉํ๋ฉด ๋ฐ๋์ ์ถ๊ฐํด์ผ ํ๋ ์ด๋ธํ์ด์์ด๊ณ , ํด๋น ์ด๋ธํ์ด์์ ์ถ๊ฐํ ๋ณ์๋ Primary Key๋ฅผ ์๋ฏธํ๋ค.
    - `@GeneratedValue(strategy = GenerationType.IDENTITY)`
        - DB์์ ์๋์ผ๋ก Id๋ฅผ ์์ฑํ  ์ ์๋๋ก ๊ธฐ๋ณธ ํค ์์ฑ์ ์์ํ๋ค.
        - strategy์ ๋ฐ๋ผ์ ์ค์ ์ ๋ฌ๋ฆฌ ํ  ์ ์๋ค.
        - ์ฐธ๊ณ  ๋งํฌ : [https://gmlwjd9405.github.io/2019/08/12/primary-key-mapping.html](https://gmlwjd9405.github.io/2019/08/12/primary-key-mapping.html)

### 4. Entity์ DB์ ์ํธ์์ฉ์ ํ  ์ ์๋ ์ธํฐํ์ด์ค ๊ตฌํ

```java
package com.example.mini_project_crud_exercise.repository;

import com.example.mini_project_crud_exercise.domian.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
```

- JPA๋ฅผ ์ฌ์ฉํ  ์ ์๋ JpaRepository๋ฅผ ์์์ ๋ฐ๋๋ค.

### 5. ๊ฒ์๊ธ ์์ฑ API๋ฅผ ๊ตฌํ_Controller ํด๋์ค

```java
package com.example.mini_project_crud_exercise.controller;

import com.example.mini_project_crud_exercise.domian.dto.ArticleDto;
import com.example.mini_project_crud_exercise.domian.entity.Article;
import com.example.mini_project_crud_exercise.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final ArticleRepository articleRepository;

    public BoardController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String writeArticleForm(){
        return "write";
    }

    @PostMapping("/post")
    public String saveArticle(ArticleDto articleDto){
        log.info(articleDto.toString());
        Article article = articleDto.toEntity();
        articleRepository.save(article);
        return "";
    }
}
```

- `articleRepository`
    - ์คํ๋ง ๋ถํธ๊ฐ articleRepository์ ๊ตฌํ์ฒด๋ฅผ ์์ฑํ์ฌ ์์กด๊ด๊ณ๋ฅผ ๋งบ์ด์ค๋ค.
    - ๋ฐ๋ผ์ ์ง์  articleRepository์ ๊ตฌํ์ฒด๋ฅผ ์์ฑํ์ง ์์๋ JpaRepository์ ๊ธฐ๋ฅ์ ์ฌ์ฉํ  ์ ์๋ค.
- `writeArticleForm`
    - GET REST API๋ก ๋งคํ๋ url๋ก ์ด๋ํ๋ฉด ์์์ ๊ตฌํํ write ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฐํํ๋ค.
- `saveArticle`
    - write ํํ๋ฆฟ์์ ์ ์ถ๋ฒํผ์ ํด๋ฆญํ๋ฉด form์์ ์์ฑํ ๋ฐ์ดํฐ๋ post๋ฐฉ์์ผ๋ก ํด๋น url๋ก ๋งคํ๋ ๋ฉ์๋๋ก ์ด๋ํ๋ค.
    - POST REST API๋ก ๋งคํ๋ ๋ฉ์๋์์ dto๋ฅผ entity๋ก ๋ณํํ์ฌ entity๋ฅผ save ๋ฉ์๋๋ก db์ insertํ๋ค.

<aside>
โ **๊ตณ์ด DTO๋ฅผ ENTITY๋ก ๋ณํํ์ฌ ์ฌ์ฉํ๋ ์ด์ **

view์ form ๋ฐ์ดํฐ ํ์์ ๋ณ๊ฒฝ๋  ๊ฐ๋ฅ์ฑ์ด ๋๋ค. ๋ฐ๋ผ์ DTO๋ฅผ ๋ณ๊ฒฝํ  ์ํฉ์ด ๋ง์์ง ์๋ ์๋ค.

DTO๋ฅผ ENTITY๋์  ์ฌ์ฉํ๋ค๋ฉด ๋ณ๊ฒฝ๋ form์ ๋ฐ๋ผ DB์ ์ ๊ทผํ  ๋ฐ์ดํฐ๋ ํ์์ด ๋ฌ๋ผ์ง ์ ์๋ค.(ex. title, content ๋ฟ๋ง์ด ์๋๋ผ email ์์ฑ๋๋ ์ถ๊ฐ๋๋ ์ํฉ)
DB์ ๋ฐ์ดํฐ ํ์์ ํ์ด๋ธ์ด ๋ณ๊ฒฝ๋์ง ์๋ ํ ๊ณ ์ ๋์ด์๊ธฐ ๋๋ฌธ์ DB์ ์ ๊ทผํ๋ ๋ฐ์ดํฐ ํ์์ด ๋ฌ๋ผ์ง๋ฉด ๋ฌธ์ ๊ฐ ์๊ธด๋ค.

๊ฒฐ๋ก ์ ์ผ๋ก ์์ ๊ฐ์ ๋ฌธ์ ๋ฅผ ๊ฒช์ง ์๊ธฐ ์ํด DTO์ ENTITY๋ฅผ ์ฌ์ฉํ์ฌ DTO๋ก view์์ controller๋ก ๋ฐ์ดํฐ๋ฅผ ๋ฐ๊ณ  ํด๋น ๋ฐ์ดํฐ๋ฅผ DBํ์์ ๋ง๋๋ก ENTITY๋ก ๋ณํํ์ฌ DB์ ์ ๊ทผํ๋ค.

</aside>

## ๊ฒ์๊ธ ์ถ๋ ฅ ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_4.png](image/img_4.png)

### 1. ๊ฒ์๊ธ ์ถ๋ ฅ view ๊ตฌํ

- error.mustache_๊ฒ์๊ธ์ด ์์ ๊ฒฝ์ฐ ์ถ๋ ฅํ  view

```html
{{>layouts/header}}
<div>error</div>
{{>layouts/footer}}
```

- show.mustache_table ํํ๋ก 1๊ฐ์ ๊ฒ์๊ธ์ ์ถ๋ ฅ

```html
{{>layouts/header}}
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    {{#article}}
    <tr>
        <td>{{id}}</td>
        <td>{{title}}</td>
        <td>{{content}}</td>
    </tr>
    {{/article}}
    </tbody>
</table>
{{>layouts/footer}}
```

### 2. ๊ฒ์๊ธ์ ์ถ๋ ฅํ๋ API ๋ฉ์๋_Controller ํด๋์ค์ ์ผ๋ถ

```java
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
```

- `showArticle`
    - url์ ์๋ id๋ฅผ `@PathVariable` ๋ก ๋ฐ๋๋ค.
    - findById๋ก DB์์ ํด๋น Id๋ฅผ ๊ฐ์ง๊ณ  ์๋ row ๋ฐ์ดํฐ๋ฅผ ๋ฐ์์จ๋ค.
    - if๋ฌธ์ผ๋ก ๋ฐ์ดํฐ๊ฐ ์๋์ง ์๋์ง ์ฒดํฌํ๋ค.
        - ์์ผ๋ฉด model ๊ฐ์ฒด๋ฅผ ํ์ฉํ์ฌ view์ article๋ณ์์ ํด๋น Article๊ฐ์ฒด๋ฅผ ๋๊ธฐ๊ณ , show  ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฐํํ๋ค.
        - ์์ผ๋ฉด error ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฐํํ๋ค.

## ๊ฒ์๊ธ ๋ฆฌ์คํธ ์ถ๋ ฅ ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_5.png](image/img_5.png)

### 1. ๊ฒ์๊ธ ๋ฆฌ์คํธ ์ถ๋ ฅ view ๊ตฌํ

- list.mustache_tableํ์์ผ๋ก ๋ฆฌ์คํธ๋ฅผ ์ถ๋ ฅ, title์ ํด๋ฆญํ๋ฉด show๋ก ๊ฒ์๊ธ ์ถ๋ ฅ

```html
{{>layouts/header}}
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    {{#articles}}
    <tr>
        <td>{{id}}</td>
        td><a href="/board/{{id}}">{{title}}</a></td>
        <td>{{content}}</td>
    </tr>
    {{/articles}}
    </tbody>
</table>
{{>layouts/footer}}
```

### 2. ๊ฒ์๊ธ ๋ฆฌ์คํธ๋ฅผ ์ถ๋ ฅํ๋ API ๊ตฌํ_Controller ํด๋์ค

```java
    @GetMapping("")
    public String listArticle(Model model){
        List<Article> list = articleRepository.findAll();
        model.addAttribute("articles",list);
        return "list";
    }
```

- `listArticle`
    - findAll๋ก ํ์ด๋ธ์ ์๋ ๋ชจ๋  ๋ฐ์ดํฐ๋ฅผ ๋ฆฌ์คํธ ํ์์ผ๋ก ๋ฐํํ๋ค.
    - model ๊ฐ์ฒด๋ก view์ articles ๋ณ์์ list๋ฅผ ์ ๋ฌํ๋ค.
    - list ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฐํํ๋ค.

## ๊ฒ์๊ธ ์์  ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_6.png](image/img_6.png)

21๋ฒ ๊ฒ์๋ฌผ์ ์์ ๋ฒํผ์ ๋๋ฅธ ํ ํ๋ฉด

### 1. ๊ฒ์๊ธ ์์  ๊ธฐ๋ฅ view ๊ตฌํ

- show.mustache_tableํ๊ทธ ์๋ ๋ฒํผ ์ถ๊ฐ

```java
<a href = "/board/{{id}}/edit" class="btn btn-success">Edit</a>
<a href = "/board">back</a>
```

- edit.mustache_id๊ฐ์ hidden์ผ๋ก ์ฒ๋ฆฌํ๋ค.

```html
{{>layouts/header}}
{{#article}}
<form class="container" action = "/board/{{id}}/upadate" method="post">
    <input type="hidden" name="id" value="{{id}}">
    <div class="mb-3">
        <label class="form-label">Title</label>
        <input class="form-control" name="title" value={{title}}>
    </div>
    <div class="mb-3">
        <label class="form-label">Content</label>
        <textarea class="form-control" name="content" style="height: 100px">{{content}}</textarea>
    </div>
    <button type="submit" class="btn btn-primary">edit</button>
</form>
{{/article}}
{{>layouts/footer}}
```

### 2. view์์ Controller๋ก data๋ฅผ ์ ๋ฌํ  DTO ํด๋์ค ์์ 

```java
package com.example.mini_project_crud_exercise.domian.dto;

import com.example.mini_project_crud_exercise.domian.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ArticleDto {

    private Long id;
    private String title;
    private String content;

    public Article toEntity(){
        return new Article( this.id, this.title, this.content);
    }
}
```

- Id๊ฐ ์์ด์ผ ์์ ํ  ๊ฒ์๊ธ์ id๋ฅผ ๋๊ฒจ์ค ์ ์๋ค.
- Article ์์ฑ์์ ๋งค๊ฐ๋ณ์์ id๊ฐ์ ๋๊ฒจ์ค๋ค.
    - Entity์ id๊ฐ์ ํตํด์ DB์ update๋ฅผ ํ  ์ ์๋ค.

### 3. Controller์์ DB๋ก ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ  Entity ํด๋์ค ์์ 

```java
package com.example.mini_project_crud_exercise.domian.entity;

import com.example.mini_project_crud_exercise.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
}
```

- DTO ํด๋์ค์์ ๋๊ฒจ์ฃผ๋ id๊ฐ์ ๋ฐ์์ค๊ธฐ ์ํด `@AllArgsConstructor` ์ด๋ธํ์ด์์ ์ถ๊ฐํ์ฌ ๋ชจ๋  ํ๋๊ฐ์ ์ด๊ธฐํ ํด์ฃผ๋ ์์ฑ์๋ฅผ ์ถ๊ฐํ๋ค.

### 4. ๊ฒ์๊ธ ์์  API๋ฅผ ๊ตฌํ_Controller ํด๋์ค

```java
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
```

- `editArticleForm`
    - show์์ ์์  ๋ฒํผ์ ํด๋ฆญํ๋ฉด ํด๋น ๊ฒ์๋ฌผ์ id๋ฅผ ๋ฐ์์จ๋ค.
    - id๋ฅผ ๋๊ธด findById๋ฅผ ํตํด ํด๋น ๊ฒ์๋ฌผ์ ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ ธ์จ๋ค
        - ๋ฐ์ดํฐ๊ฐ ์กด์ฌํ๋ค๋ฉด edit ํํ๋ฆฟ์ article ๋ณ์์ ๋ฐ์ดํฐ๋ฅผ ๋๊ฒจ์ค๋ค.
        - edit ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฆฌํดํ๋ค.
    - ๋ฐ์ดํฐ๊ฐ ์์ผ๋ฉด error ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฆฌํดํ๋ค.

```java
@PostMapping("/{id}/upadate")
    public String updateArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article article = articleDto.toEntity();
        articleRepository.save(article);
        return "redirect:/board/" + article.getId();
    }
```

- `updateArticle`
    - edit ํ์ด์ง์์ ์์  ๋ฒํผ์ ๋๋ฅด๋ฉด ํด๋น API๋ก ์ด๋ํ๋ค.
    - edit ํ์ด์ง์์ ์์ ํ ๋ฐ์ดํฐ๋ฅผ DTO๊ฐ์ฒด๋ก ๋ฐ๋๋ค.
    - DTO๊ฐ์ฒด๋ฅผ ENTITY๋ก ๋ณํํ๋ค.
    - ENTITY๋ฅผ save ๋ฉ์๋์ ๋๊ฒจ์ค update๋ฅผ ํ๋ค.
        - save ๋ฉ์๋๋ primary key ๊ฐ ์กด์ฌํ์ง ์์ ๊ฒฝ์ฐ insertํ๋ค.
        - ์๋ ๊ฒฝ์ฐ๋ update๋ฅผ ํ๋ค.
    - ๋ฆฌ๋ค์ด๋ ํธ๋ฅผ ์ฌ์ฉํ์ฌ ์์ ๋ ๊ฒ์๋ฌผ ์ถ๋ ฅ ํ์ด์ง์ url์ ๋ฐํํ๋ค.

## ๊ฒ์๊ธ ์ญ์  ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_7.png](image/img_7.png)
![img_8.png](image/img_8.png)

### 1. ๊ฒ์๊ธ ์ญ์  ๊ธฐ๋ฅ view ๊ตฌํ

- show.mustache_์ญ์ ๋ฒํผ ์ถ๊ฐ

```html
{{>layouts/header}}
<div class="container">
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    {{#article}}
    <tr>
        <td>{{id}}</td>
        <td>{{title}}</td>
        <td>{{content}}</td>
    </tr>
    {{/article}}
    </tbody>
</table>
    <a href = "/board/{{id}}/edit" class="btn btn-success">Edit</a>
    <a href = "/board/{{id}}/delete" class="btn btn-danger">Delete</a>
    <a href = "/board">back</a>
</div>

{{>layouts/footer}}
```

- list.mustache_์ญ์ ์ ๋จ๋ ์๋ฆผ์ฐฝ ์ถ๊ฐ

```html
{{>layouts/header}}
{{#msg}}
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        {{msg}}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
{{/msg}}
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    {{#articles}}
    <tr>
        <td>{{id}}</td>
        <td><a href="/board/{{id}}">{{title}}</a></td>
        <td>{{content}}</td>
    </tr>
    {{/articles}}
    </tbody>
</table>
{{>layouts/footer}}
```

### 2. ๊ฒ์๊ธ ์ญ์  API ๊ตฌํ_Controller ํด๋์ค

```java
@GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rtts) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "error";
        else {
            articleRepository.deleteById(optArticle.get().getId());
            rtts.addFlashAttribute("msg",optArticle.get().getId()+"๋ฒ์งธ ๊ฒ์๊ธ์ด ์ญ์ ๋์์ต๋๋ค.");
            return "redirect:/board";
        }
    }
```

- `deleteArticle`
    - PathVariable๋ก id๊ฐ์ ๋ฐ์ ํด๋น ์์ด๋์ ๋ฐ์ดํฐ๋ฅผ ๋ฐ์์จ๋ค.
    - ๋ฐ์ดํฐ์ ์กด์ฌ ์ฌ๋ถ๋ฅผ ํ์ธํ๋ค.
        - ๋ฐ์ดํฐ๊ฐ ์์ผ๋ฉด /board๋ก ๋ฆฌ๋ค์ด๋ ํธํ๋ค.
        - ๋ฐ์ดํฐ๊ฐ ์์ผ๋ฉด error ํํ๋ฆฟ์ ์ด๋ฆ์ ๋ฐํํ๋ค.

## ๊ฒ์๊ธ ๋๊ธ ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_9.png](image/img_9.png)

### 1. ๊ฒ์๊ธ ๋๊ธ view ๊ตฌํ

- show.mustache_๋๊ธ ์์ฑํผ, ๋๊ธ ๋ฆฌ์คํธ ํ์ด๋ธ ์ถ๊ฐ

```html
{{>layouts/header}}
<div class="container">
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    {{#article}}
    <tr>
        <td>{{id}}</td>
        <td>{{title}}</td>
        <td>{{content}}</td>
    </tr>
    {{/article}}
    </tbody>
</table>
    <a href = "/board/{{id}}/edit" class="btn btn-success">Edit</a>
    <a href = "/board/{{id}}/delete" class="btn btn-danger">Delete</a>
    <a href = "/board">back</a>
</div>
<hr>
<!-- comment -->
<div class="container">
    <h2>Comment</h2>
    <form class="container" action = "/board/comment/post" method="post">
        <input type="hidden" name="articleId" value={{id}}>
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input class="form-control" name="name">
        </div>
        <div class="mb-3">
            <label class="form-label">Comment</label>
            <input class="form-control" name="comment">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="/board" >back</a>
    </form>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Comment</th>
        </tr>
        </thead>
        <tbody class="table">
        {{#comments}}
            <tr>
                <td>{{name}}</td>
                <td>{{comment}}</td>
            </tr>
        {{/comments}}
        </tbody>
    </table>
</div>
{{>layouts/footer}}
```

### 2. view์์ controller๋ก ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ  DTO ๊ตฌํ

```java
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
```

### 3. controller์์ DB๋ก ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ  Entity ๊ตฌํ

```java
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
```

### 4. Entity์ DB ์ํธ์์ฉ์ ํ  ์ ์๋ JPA ์ธํฐํ์ด์ค ๊ตฌํ

```java
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
```

- `findByArticleId`
    - ๋งค๊ฐ๋ณ์๋ก ๋๊ธด articleId๋ฅผ ๊ฐ์ง ๋๊ธ์ ๋ชจ๋ ๊ฐ์ ธ์ค๋ ๋ฉ์๋
- `deleteAllByArticleId`
    - ๋งค๊ฐ๋ณ์๋ก ๋๊ธด articleId๋ฅผ ๊ฐ์ง ๋๊ธ์ ๋ชจ๋ ์ญ์ ํ๋ ๋ฉ์๋

### 5. ๊ฒ์๊ธ ๋๊ธ ๊ธฐ๋ฅ API ๊ตฌํ

```java
@PostMapping(value = "/comment/post")
    public String saveComment(CommentDto commentDto){
        log.info(commentDto.toString());
        Comment comment = commentDto.toEntity();
        commentRepository.save(comment);
        return "redirect:/board/"+commentDto.getArticleId();
    }
```

- `saveComment`
    - view์์ ์ ๋ฌ๋ฐ์ dto๋ฅผ entity๋ก ๋ณํํ์ฌ DB์์ ์ฅ

```java
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
```

- `showArticle` ์์ 
    - ํด๋น articleId๋ฅผ findByArticleId ๋ฉ์๋์ ๋งค๊ฐ๋ณ์๋ก ๋๊ฒจ ๊ฐ์ articleId๋ฅผ ๊ฐ์ง ๋๊ธ ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ ธ์ list์ ๋์ํ๋ค.
    - show ํํ๋ฆฟ์ list๋ฅผ ๋๊ฒจ์ค๋ค.

```java
@GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rtts) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "articles/error";
        else {
            articleRepository.deleteById(optArticle.get().getId());
            commentRepository.deleteAllByArticleId(id);
            rtts.addFlashAttribute("msg",optArticle.get().getId()+"๋ฒ์งธ ๊ฒ์๊ธ์ด ์ญ์ ๋์์ต๋๋ค.");
            return "redirect:/board";
        }
    }
```

- `deleteArticle` ์์ 
    - ๊ฒ์๊ธ์ด ์ญ์ ๋๋ค๋ฉด ํด๋น ๊ฒ์๊ธ์ ๋๊ธ๋ค๋ ์ญ์ ๋์ด์ผ ํ๋ค.
    - ํด๋น path variable์ธ id๋ฅผ `deleteAllByArticleId` ์ ๋๊ฒจ id๋ฅผ ๊ฐ์ง๊ณ  ์๋ ๋๊ธ์ ์ญ์ ํ๋ค.

## ํ์ด์ง ๊ธฐ๋ฅ ๊ตฌํ

### 0. ๊ตฌํ ํ ๋ชจ์ต

![img_10.png](image/img_10.png)

### 1. ํ์ด์ง ๊ธฐ๋ฅ view ๊ตฌํ

- list.mustache_์๋ ์ฝ๋๋ฅผ ์ถ๊ฐ

```html
<ul class="pagination justify-content-center">
   <li class="page-item"><a class="page-link" href="?page={{previous}}">Previous</a></li>
   <li class="page-item"><a class="page-link" href="?page={{next}}">Next</a></li>
</ul>
```

### 2. Controller์์ ํ์ด์ง ๊ธฐ๋ฅ ๊ตฌํ

```java
@GetMapping("")
    public String listArticle(Model model, Pageable pageable){
        Page<Article> list = articleRepository.findAll(pageable);
        model.addAttribute("articles",list);
        model.addAttribute("previous",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        return "articles/list";
    }
```

- jpa์์ ์ ๊ณตํ๋ Pageable ์ธํฐํ์ด์ค ๊ตฌํ์ฒด๋ฅผ ์ฌ์ฉํ๋ค.

## Docker ๋ฐฐํฌ

### 1. jar ํ์ผ ๋ง๋ค๊ธฐ

![img_11.png](image/img_11.png)

- ์ค๋ฅธ์ชฝ Gradle์ ๋ค์ด๊ฐ์ build์ assemble์ ํด๋ฆญํ๋ฉด .jarํ์ผ์ ์์ฑํ๋ค.
- jarํ์ผ
-

![img_12.png](image/img_12.png)

### 2. Dockerfile์ ๋ง๋ค๊ธฐ

- ๋ฃจํธ ๋๋ ํ ๋ฆฌ์ Dockerfile์ ์์ฑํ๋ค.

```java
FROM gradle:7.4-jdk11-alpine as builder
WORKDIR /build

# ๊ทธ๋๋ค ํ์ผ์ด ๋ณ๊ฒฝ๋์์ ๋๋ง ์๋กญ๊ฒ ์์กดํจํค์ง ๋ค์ด๋ก๋ ๋ฐ๊ฒํจ.
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# ๋น๋ ์ด๋ฏธ์ง์์ ์ ํ๋ฆฌ์ผ์ด์ ๋น๋
COPY . /build
RUN gradle build -x test --parallel

# APP
FROM openjdk:11.0-slim
WORKDIR /app

# ๋น๋ ์ด๋ฏธ์ง์์ jar ํ์ผ๋ง ๋ณต์ฌ
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# root ๋์  nobody ๊ถํ์ผ๋ก ์คํ
USER nobody
ENTRYPOINT [                                                \
   "java",                                                 \
   "-jar",                                                 \
   "-Djava.security.egd=file:/dev/./urandom",              \
   "-Dsun.net.inetaddr.ttl=0",                             \
   "app.jar"              \
]
```

### 3. git์ commit and push

![img_13.png](image/img_13.png)

### 4. ์๋ฒ์์ git clone

![img_14.png](image/img_14.png)

### 5. ํ๋ก์ ํธ ๋๋ ํ ๋ฆฌ๋ก ์ด๋ ํ docker ๋น๋

- `cd ํ๋ก์ ํธ๋ช`
- `docker build -t ํ๋ก์ ํธ๋ช .` โ .๊ผญ ์ถ๊ฐํด์ค์ผ ๋น๋๊ฐ ๋๋ค.

### 6. ๋์ปค์์ .jarํ์ผ ์คํ

- `docker run -p 8080:8080 -e DB_HOST=jdbc:mysql://..... -e DB_USER=... -e DB_PASSWORD=... -d ์์ฑ๋ ๋์ปค ์ด๋ฏธ์ง ๋ช`
- EC2 ์ธ์คํด์ค URL:ํฌํธ๋ฒํธ/board ๋ผ๊ณ  ์น ๋ธ๋ผ์ฐ์ ์ ๊ฒ์ํ๋ฉด ์๋น์ค๊ฐ ์คํ๋๋ค.
- [http://ec2-13-125-202-224.ap-northeast-2.compute.amazonaws.com:8080/board](http://ec2-13-125-202-224.ap-northeast-2.compute.amazonaws.com:8080/board)

## ํ๋ก์ ํธ๋ฅผ ์งํํ๋ฉด์ ๋ฐ์ํ  ์ ์๋ ๋ฌธ์ 

### DB ์ ์์ด ์๋๋ ๋ฌธ์ 

![img_15.png](image/img_15.png)

- ์์ธ
    - ๋ก๊ทธ์ธ ์ ๋ณด๊ฐ ์๋ชป๋ ๊ฒฝ์ฐ (ํ๊ฒฝ๋ณ์ ์๋ ฅ์ ์๋ชปํ ๊ฒฝ์ฐ)
    - DB ํฌํธ๋ฒํธ๋ฅผ ์๋ชป ์ ์ ๊ฒฝ์ฐ
- ํด๊ฒฐ
    - DB์ค์ ์ ๋ค์ ํ์ธํ๋ค.
    - ์ฐธ๊ณ  : [https://changun516.tistory.com/55](https://changun516.tistory.com/55)

### ์์ฑ์ ์ค๋ฒ๋ก๋ฉ์ ์ฐ์ ์์ ๋ฌธ์ 

```java
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticleDto {
```

- ์์ธ
    - AllArgsConstructor์ NoArgsConstructor ๋๋ค ์ถ๊ฐํ  ๊ฒฝ์ฐ ์ฐ์ ์์๋ NoArgsConstructor๋ค.
    - PostMaping์ ๋ฐ์ดํฐ๋ฅผ ๋๊ฒจ์ฃผ๊ธฐ ์ํด Dto ๊ฐ์ฒด๋ฅผ ์์ฑํ๋ ค๊ณ  ์์ฑ์๋ฅผ ๋ถ๋ฅธ๋ค.
    - ์ฐ์ ์์๋ก NoArgsConstructor๋ฅผ ํธ์ถํ๊ณ  ๊ฐ์ Setter๋ก Mappingํ๋ ค๊ณ ํ๋๋ฐ Setter๋ฅผ ์ถ๊ฐํ์ง ์๋๋ค๋ฉด ์๋์ ๊ฐ์ ์๋ฌ๊ฐ ๋ฐ์ํ๋ค.
    - No key, method or field with name 'title' on line 14
    - null๊ฐ์ด ๋ค์ด๊ฐ๋ค.
- ํด๊ฒฐ
    - Setter๋ฅผ ์ถ๊ฐํด์ฃผ๋ฉด ๋๋ค.
    - ์ฐธ๊ณ  : [https://steady-coding.tistory.com/489](https://steady-coding.tistory.com/489)

### ๊ฒ์๊ธ ์ญ์  ๊ธฐ๋ฅ ๊ตฌํ์ ์ญ์  ๋ฉ์ธ์ง๊ฐ ๋จ์ง ์์ ๋ฌธ์ 

```java
@GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty())
            return "error";
        else {
            String msg = optArticle.get().getId()+"๋ฒ์งธ ๊ฒ์๊ธ์ด ์ญ์ ๋์ต๋๋ค.";
            articleRepository.deleteById(optArticle.get().getId());
            model.addAttribute("msg",msg);
            return "redirect:/board";
        }
    }
```

- ์์ธ
    - ์ญ์  ๋ฉ์ธ์ง๋ฅผ model.addattribute๋ก view๋ก ์ ๋ฌํ๊ณ  ์๋ค.
    - ํ์ง๋ง ํด๋น ๋ฉ์๋์ ๋ฐํ ๊ฐ์ผ๋ก redirect๋ฅผ ์ฌ์ฉํด view๊ฐ ์๋ url์ ๋ฐํํ๊ณ  ์๋ค.
    - model์ view์ data๋ฅผ ๋๊ธธ ๋ ์ฌ์ฉํ๋ ๊ฐ์ฒด๋ค.
- ํด๊ฒฐ
    - RedirectAttributes ํด๋์ค๋ฅผ ์ฌ์ฉํ๋ฉด ๋๋ค.
    - RedirectAttributes๋ redirect ์ ๋ฐ์ดํฐ๋ฅผ ์ ๋ฌํ  ์ ์๋ ๊ฐ์ฒด์ด๋ค.
    - post๋ฐฉ์์ผ๋ก ๋ฐ์ดํฐ๋ฅผ ๋๊ฒจ์ฃผ๋ `addFlashAttribute` ๋ฉ์๋๋ฅผ ์ฌ์ฉํด์ผํ๋ค.
- ์ฐธ๊ณ  ์๋ฃ
- [https://iamdaeyun.tistory.com/entry/์คํ๋ง-์ปจํธ๋กค๋ฌ](https://iamdaeyun.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC)
- [https://mine-it-record.tistory.com/416](https://mine-it-record.tistory.com/416)

### ๋๊ธ ์ญ์  ๊ตฌํ ์ ์๋ฌ

![img_16.png](image/img_16.png)

- ์์ธ
    - CommentRepository์์ delelteAllByArticleId๋ฅผ ์ ์ธํ์ ๋ Transactional ์ด๋ธํ์ด์์ ๋ถ์ด์ง ์์์ ์๊ธด ๋ฌธ์ 
    - ํธ๋์ญ์์ด๋
        - ๊ฑฐ๋๋ผ๋ ์๋ฏธ๋ก DB์์ ํ ์์์ด ๋ง๋ฌด๋ฆฌ๋์ง ์์ผ๋ฉด ์์ํ๋ก ๋์๊ฐ๋ ๊ฒ์ ๋งํ๋ค.
        - ์ญ์ ์ ์คํจ๋ฅผ ํ๋ฉด ์ญ์  ์ ์ผ๋ก ๋์๊ฐ๋ค.
        - delete๋ฅผ ์ ์ธํ  ๋๋ ํธ๋์ญ์์ ์ ์ฉํด์ผํ๋ค.
- ํด๊ฒฐ
    - @Transactional๋ฅผ ์ถ๊ฐํด์ค๋ค.
- ์ฐธ๊ณ 
    - [https://yoonho-devlog.tistory.com/61](https://yoonho-devlog.tistory.com/61)

### ๋์ปค ๋น๋ ์ ์ฉ๋ ์ด์

![img_18.png](image/img_18.png)

- ์์ธ
    - aws ์ธ์คํด์ค์ ์ฉ๋์ด ๋ถ์กฑ
- ํด๊ฒฐ
    - `docker system df` : docker์ ์ฉ๋์ ํ์ธํ๋ค.
    - `docker image prune` : ์ฌ์ฉํ์ง ์๋ image๋ฅผ ์ญ์ ํ๋ค.
    - `docker container prune` : ์คํ ์ค์ด์ง ์์ ์ปจํ์ด๋๋ค์ ์ญ์ ํ  ์ ์๋ค.
    - `docker system prune -a` : ์ด๋ฏธ์ง, ์ปจํ์ด๋, ๋คํธ์ํฌ ๋ฑ ํ๋ฒ์ ์ญ์ ํ  ์ ์๋ค.
    - `docker volume prune` : ์๋ ๋ช๋ น์ด๋ก Volumes์ ์ญ์ ํ๋ค.
- ์ฐธ๊ณ 
    - [https://engineer-mole.tistory.com/256](https://engineer-mole.tistory.com/256)