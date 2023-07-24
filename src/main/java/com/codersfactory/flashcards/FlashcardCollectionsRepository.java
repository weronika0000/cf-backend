package com.codersfactory.flashcards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardCollectionsRepository extends JpaRepository<FlashcardCollection, Long> {
}
