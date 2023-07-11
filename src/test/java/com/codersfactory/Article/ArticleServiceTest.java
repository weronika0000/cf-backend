package com.codersfactory.Article;

import com.codersfactory.article.Article;
import com.codersfactory.article.ArticleDTO;
import com.codersfactory.article.ArticleRepository;
import com.codersfactory.article.ArticleService;
import com.codersfactory.common.exception.ArticleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.codersfactory.common.entity.DifficultyLevel;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateArticle() {
        ArticleDTO inputDto = new ArticleDTO(null, "Title", "Author", "Content", "Technology", DifficultyLevel.ADVANCED);
        Article savedArticle = new Article(1L, "Title", "Author", "Content", "Technology", DifficultyLevel.ADVANCED, Instant.now(), null, null, null, null);
        when(articleRepository.save(any(Article.class))).thenReturn(savedArticle);

        ArticleDTO resultDto = articleService.createArticle(inputDto);

        assertEquals(1L, resultDto.id());
        assertEquals("Title", resultDto.title());
        assertEquals("Author", resultDto.author());
        assertEquals("Content", resultDto.content());
        assertEquals("Technology", resultDto.technology());
        assertEquals(DifficultyLevel.ADVANCED, resultDto.difficultyLevel());
    }

    @Test
    void testGetArticle() {
        Article article = new Article(1L, "Title", "Author", "Content", "Technology", DifficultyLevel.ADVANCED, Instant.now(), null, null, null, null);
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        ArticleDTO resultDto = articleService.getArticle(1L);

        assertEquals(1L, resultDto.id());
        assertEquals("Title", resultDto.title());
        assertEquals("Author", resultDto.author());
        assertEquals("Content", resultDto.content());
        assertEquals("Technology", resultDto.technology());
        assertEquals(DifficultyLevel.ADVANCED, resultDto.difficultyLevel());
    }

    @Test
    void testUpdateArticle() {
        ArticleDTO inputDto = new ArticleDTO(1L, "Updated Title", "Updated Author", "Updated Content", "Updated Technology", DifficultyLevel.ADVANCED);
        Article updatedArticle = new Article(1L, "Updated Title", "Updated Author", "Updated Content", "Updated Technology", DifficultyLevel.ADVANCED, Instant.now(), Instant.now(), null, null, null);
        when(articleRepository.findById(1L)).thenReturn(Optional.of(new Article()));
        when(articleRepository.save(any(Article.class))).thenReturn(updatedArticle);

        ArticleDTO resultDto = articleService.updateArticle(inputDto);

        assertEquals(1L, resultDto.id());
        assertEquals("Updated Title", resultDto.title());
        assertEquals("Updated Author", resultDto.author());
        assertEquals("Updated Content", resultDto.content());
        assertEquals("Updated Technology", resultDto.technology());
        assertEquals(DifficultyLevel.ADVANCED, resultDto.difficultyLevel());
    }

    @Test
    void testDeleteArticle() {
        when(articleRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> articleService.deleteArticle(1L));

        verify(articleRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteArticleNotFound() {
        when(articleRepository.existsById(1L)).thenReturn(false);

        assertThrows(ArticleNotFoundException.class, () -> articleService.deleteArticle(1L));

        verify(articleRepository, times(0)).deleteById(anyLong());
    }

}
