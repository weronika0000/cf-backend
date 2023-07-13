package com.codersfactory.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

//    Page<Article> findAll(ArticleQuery query, Pageable pageable);

//    default Page<Article> search (ArticleQuery articleQuery, Pageable pageable ){
//        return findAll(Specification.where(Specs.byTitle(articleQuery.getTitle()))
//                .and(Specs.byAuthor(articleQuery.getAuthor()))
//                .and(Specs.byTechnology(articleQuery.getTechnology()))
//                .and(Specs.byDifficultyLevel(articleQuery.getDifficultyLevel()))
//                .and(Specs.byTags(articleQuery.getTags())), pageable);
//
//    }
}

interface Specs {
        static Specification<Article> byTitle(String title) {
            return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
        }

        static Specification<Article> byAuthor(String author) {
            return (root, query, builder) -> builder.like(root.get("author"), "%" + author + "%");
        }

        static Specification<Article> byTechnology(String technology) {
            return (root, query, builder) -> builder.like(root.get("technology"), "%" + technology + "%");
        }

        static Specification<Article> byDifficultyLevel(String difficultyLevel) {
            return (root, query, builder) -> builder.like(root.get("difficultyLevel"), "%" + difficultyLevel + "%");
        }

        static Specification<Article> byTag(String tag) {
            return (root, query, builder) -> builder.like(root.get("tag"), "%" + tag + "%");
        }

        static Specification<Article> byTags (List <String> tags) {
            return (root, query, builder) -> builder.like(root.get("tags"), "%" + tags + "%");
        }
}


