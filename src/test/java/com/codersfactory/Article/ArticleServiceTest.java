package com.codersfactory.Article;

import com.codersfactory.article.Article;
import com.codersfactory.article.ArticleDTO;
import com.codersfactory.article.ArticleRepository;
import com.codersfactory.article.ArticleServiceImpl;
import com.codersfactory.common.exception.ArticleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    private final ArticleDTO inputDto = new ArticleDTO(1l, "Title",
            "Author", "Content", "Technology", DifficultyLevel.ADVANCED);

    private final Article savedArticle = new Article(1L, "Title",
            "Author", "Content", "Technology",
            DifficultyLevel.ADVANCED, Instant.now(), Instant.now(),
            null, null, null);

    private final Article updatedArticle = new Article(1L, "Updated Title",
            "Updated Author", "Updated Content", "Updated Technology",
            DifficultyLevel.ADVANCED, Instant.now(), Instant.now(),
            null, null, null);

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("When creating a valid article, then it should return the created article")
    @Test
    void whenCreatingValidArticle_thenItShouldReturnCreatedArticle() {
        // Given
        when(articleRepository.save(any(Article.class))).thenReturn(savedArticle);

        // When
        ArticleDTO resultDto = articleService.createArticle(inputDto);

        // Then
        assertEquals(1L, resultDto.id());
        assertEquals("Title", resultDto.title());
        assertEquals("Author", resultDto.author());
        assertEquals("Content", resultDto.content());
        assertEquals("Technology", resultDto.technology());
        assertEquals(DifficultyLevel.ADVANCED, resultDto.difficultyLevel());
    }

    @DisplayName("When creating an invalid article, then it should throw an exception")
    @Test
    void whenCreatingInvalidArticle_thenItShouldThrowException() {
        // Given
        when(articleRepository.save(any(Article.class))).thenThrow(new ArticleNotFoundException());

        // When & Then
        assertThrows(ArticleNotFoundException.class, () -> articleService.createArticle(inputDto));
    }

    @DisplayName("When getting an existing article by ID, then it should return the correct article")
    @Test
    void whenGettingExistingArticleByID_thenItShouldReturnCorrectArticle() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.of(savedArticle));

        // When
        ArticleDTO resultDto = articleService.getArticle(1L);

        // Then
        assertEquals(1L, resultDto.id());
        assertEquals("Title", resultDto.title());
        assertEquals("Author", resultDto.author());
        assertEquals("Content", resultDto.content());
        assertEquals("Technology", resultDto.technology());
        assertEquals(DifficultyLevel.ADVANCED, resultDto.difficultyLevel());
    }

    @DisplayName("When getting a non-existing article by ID, then it should throw an exception")
    @Test
    void whenGettingNonExistingArticleByID_thenItShouldThrowException() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ArticleNotFoundException.class, () -> articleService.getArticle(1L));
    }

    @DisplayName("When updating an existing article, then it should return the updated article")
    @Test
    void whenUpdatingExistingArticle_thenItShouldReturnUpdatedArticle() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.of(new Article()));
        when(articleRepository.save(any(Article.class))).thenReturn(updatedArticle);

        // When
        ArticleDTO resultDto = articleService.updateArticle(inputDto);

        // Then
        assertEquals(1L, resultDto.id());
        assertEquals("Updated Title", resultDto.title());
        assertEquals("Updated Author", resultDto.author());
        assertEquals("Updated Content", resultDto.content());
        assertEquals("Updated Technology", resultDto.technology());
        assertEquals(DifficultyLevel.ADVANCED, resultDto.difficultyLevel());
    }

    @DisplayName("When updating a non-existing article, then it should throw an exception")
    @Test
    void whenUpdatingNonExistingArticle_thenItShouldThrowException() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ArticleNotFoundException.class, () -> articleService.updateArticle(inputDto));
    }

    @DisplayName("When deleting an existing article, then it should not throw exception")
    @Test
    void whenDeletingExistingArticle_thenItShouldNotThrowException() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(true);

        // When & Then
        assertDoesNotThrow(() -> articleService.deleteArticle(1L));
        verify(articleRepository, times(1)).deleteById(1L);
    }

    @DisplayName("When deleting a non-existing article, then it should throw ArticleNotFoundException")
    @Test
    void whenDeletingNonExistingArticle_thenItShouldThrowArticleNotFoundException() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(ArticleNotFoundException.class, () -> articleService.deleteArticle(1L));
        verify(articleRepository, times(0)).deleteById(anyLong());
    }

}
