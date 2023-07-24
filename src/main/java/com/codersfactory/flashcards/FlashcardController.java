package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
@AllArgsConstructor
class FlashcardController {

    final FlashcardsService flashcardsService;
    final FlashcardCollectionsService collectionsService;

    @GetMapping("/collection/{id}")
    ResponseEntity<FlashcardCollection> findCollectionById(@PathVariable Long id) {
        return new ResponseEntity<>(collectionsService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long id) {
        return new ResponseEntity<>(flashcardsService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/collection")
    ResponseEntity<FlashcardCollection> createFlashcardCollection(@RequestBody CreateFlashcardCollectionDto dto) {
        return new ResponseEntity<>(collectionsService.saveByDTO(dto), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    ResponseEntity<FlashcardCollectionDto> saveFlashcards(@PathVariable Long id,
                                                          @RequestBody List<CreateFlashcardDto> flashcards) {
        return new ResponseEntity<>(collectionsService
                .addCards(id, flashcards), HttpStatus.OK);
    }
}
