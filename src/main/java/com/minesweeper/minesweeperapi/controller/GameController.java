package com.minesweeper.minesweeperapi.controller;

import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;
import com.minesweeper.minesweeperapi.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@Slf4j
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> createGame(@RequestBody CreateGameRequest createGameRequest) {
        log.info("[action:createGame][cols:{}][rows:{}]", createGameRequest.getCols(), createGameRequest.getRows());
        GameResponse gameResponse = gameService.createGame(createGameRequest);
        return ResponseEntity.ok(gameResponse);
    }

    @PutMapping(value = "/{gameId}", consumes = "application/json")
    public ResponseEntity<?> updateGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(String.format("ok handle action for game %d", gameId));
    }
}
