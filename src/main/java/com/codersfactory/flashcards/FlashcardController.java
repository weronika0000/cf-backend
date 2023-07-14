package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
record FlashcardController(FlashcardsService flashcardsService, FlashcardCollectionsService collectionsService) {

    @GetMapping("/collection/{id}")
    ResponseEntity<FlashcardCollection> findCollectionById(@PathVariable Long id) {
        return new ResponseEntity<>(collectionsService.findById(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long id) {
        return new ResponseEntity<>(flashcardsService.findById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/collection")
    ResponseEntity<FlashcardCollection> createCollection(@RequestBody CreateFlashcardCollectionDto dto) {
        return new ResponseEntity<>(collectionsService.saveByDto(dto), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{id}")
    ResponseEntity<FlashcardCollectionDto> saveFlashcards(@PathVariable Long id,
                                                          @RequestBody List<CreateFlashcardDto> flashcards) {
        return new ResponseEntity<>(collectionsService
                .addCards(id, flashcards), HttpStatusCode.valueOf(200));
    }
}
