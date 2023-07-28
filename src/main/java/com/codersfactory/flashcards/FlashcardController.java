package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
@AllArgsConstructor
class FlashcardController {

    final FlashcardsService flashcardsService;
    final FlashcardCollectionsService collectionsService;

    @GetMapping("/collection/{id}")
    ResponseEntity<FlashcardCollectionDto> findCollectionById(@PathVariable Long id) {
        return new ResponseEntity<>(collectionsService.getDtoById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<FlashcardDto> getFlashcardById(@PathVariable Long id) {
        return new ResponseEntity<>(flashcardsService.getDtoById(id), HttpStatus.OK);
    }

    @PostMapping("/collection")
    ResponseEntity<FlashcardCollectionDto> createFlashcardCollection(@RequestBody CreateFlashcardCollectionDto dto,
                                                                  @CurrentSecurityContext
                                                                          (expression = "authentication?.name") String name) {
        return new ResponseEntity<>(collectionsService.saveAndGetDTO(dto, name), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    ResponseEntity<FlashcardCollectionDto> saveFlashcards(@PathVariable Long id,
                                                          @RequestBody List<CreateFlashcardDto> flashcards,
                                                          @CurrentSecurityContext
                                                                  (expression = "authentication?.name") String name) {
        return new ResponseEntity<>(collectionsService
                .addCards(id, flashcards, name), HttpStatus.OK);
    }

    @DeleteMapping ("/collection/{id}")
    ResponseEntity<HttpStatus> deleteFlashcardCollectionById(@PathVariable Long id,
                                                          @CurrentSecurityContext
                                                                  (expression = "authentication?.name") String name) {
        collectionsService.deleteById(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    ResponseEntity<HttpStatus> deleteFlashcardById(@PathVariable Long id,
                                                             @CurrentSecurityContext
                                                                     (expression = "authentication?.name") String name) {
        flashcardsService.deleteById(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
