package com.codersfactory.flashcards;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlashcardCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "flashcardCollection", cascade = CascadeType.ALL)
    private Set<Flashcard> flashcards = new HashSet<>();

    public void addCard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public int getSize() {
        return flashcards.size();
    }
}
