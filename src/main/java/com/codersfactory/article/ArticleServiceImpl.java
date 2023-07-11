package com.codersfactory.article;

import com.codersfactory.common.exception.ArticleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.title());
        article.setContent(articleDTO.content());
        article.setAuthor(articleDTO.author());
        article.setCreatedAt(Instant.now());

        Article savedArticle = articleRepository.save(article);

        return convertToDTO(savedArticle);
    }

    @Override
    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        return convertToDTO(article);
    }

    @Override
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

    @Override
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ArticleNotFoundException(id);
        }

        articleRepository.deleteById(id);
    }


    @Override
    public Page<ArticleDTO> searchArticle(ArticleQuery query, Pageable pageable) {
        return articleRepository.search(query, pageable)
                .map(this::convertToDTO);

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
