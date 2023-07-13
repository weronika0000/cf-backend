package com.codersfactory.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO createdArticle = articleService.createArticle(articleDTO);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
        ArticleDTO article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO updatedArticle = articleService.updateArticle(articleDTO);
        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<ArticleDTO>> searchArticle(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String author,
//            @RequestParam(required = false) String technology,
//            @RequestParam(required = false) String difficultyLevel,
//            @RequestParam(required = false) List<String> tags,
//            @RequestParam(required = false) @PageableDefault Pageable pageable
//            ) {
//        ArticleQuery query = ArticleQuery.builder()
//                .title(title)
//                .author(author)
//                .technology(technology)
//                .difficultyLevel(difficultyLevel)
//                .tags(tags)
//                .build();
//
//        Page<ArticleDTO> articles = articleService.searchArticle(query, pageable);
//        return new ResponseEntity<>(articles, HttpStatus.OK);
//    }

}
