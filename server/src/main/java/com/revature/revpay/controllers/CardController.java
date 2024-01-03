package com.revature.revpay.controllers;

import com.revature.revpay.entities.Card;
import com.revature.revpay.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        return new ResponseEntity<>(cardService.save(card), HttpStatus.OK);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable String id) {
        cardService.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
