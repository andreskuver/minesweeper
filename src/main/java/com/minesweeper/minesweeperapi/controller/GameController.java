package com.minesweeper.minesweeperapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> createGame() {
        return ResponseEntity.ok("ok create game");
    }

    @PutMapping(value = "/{gameId}", consumes = "application/json")
    public ResponseEntity<?> updateGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(String.format("ok handle action for game %d", gameId));
    }
}
