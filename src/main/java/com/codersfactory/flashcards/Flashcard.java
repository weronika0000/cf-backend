package com.codersfactory.flashcards;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Flashcard extends CrudEntity<Flashcard>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String front;
    private String back;
    @ManyToOne
    @JoinColumn(name = "flashcardCollection.id")
    private FlashcardCollection flashcardCollection;

}
