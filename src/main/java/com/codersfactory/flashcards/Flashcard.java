package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String front;
    private String back;
    @ManyToOne
    @JoinColumn(name = "flashcardCollection.id")
    private FlashcardCollection flashcardCollection;

    public Flashcard(CreateFlashcardDto dto, FlashcardCollection collection) {
        this.back = dto.back();
        this.front = dto.front();
        this.flashcardCollection = collection;
    }
}
