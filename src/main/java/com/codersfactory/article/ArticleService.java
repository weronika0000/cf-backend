package com.codersfactory.article;

import com.codersfactory.common.exception.ArticleNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.title());
        article.setContent(articleDTO.content());
        article.setAuthor(articleDTO.author());
        article.setCreatedAt(Instant.now());

        Article savedArticle = articleRepository.save(article);

        return convertToDTO(savedArticle);
    }

    public ArticleDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        return convertToDTO(article);
    }

    public ArticleDTO updateArticle(ArticleDTO articleDTO) {
        Article article = articleRepository.findById(articleDTO.id())
                .orElseThrow(() -> new ArticleNotFoundException(articleDTO.id()));

        article.setTitle(articleDTO.title());
        article.setContent(articleDTO.content());
        article.setAuthor(articleDTO.author());
        article.setUpdatedAt(Instant.now());

        Article updatedArticle = articleRepository.save(article);

        return convertToDTO(updatedArticle);
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ArticleNotFoundException(id);
        }

        articleRepository.deleteById(id);
    }

    private ArticleDTO convertToDTO(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getContent(),
                article.getTechnology(),
                article.getDifficultyLevel()
        );
    }

}
