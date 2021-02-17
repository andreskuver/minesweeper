package com.minesweeper.minesweeperapi.controller;

import com.minesweeper.minesweeperapi.domain.CellMark;
import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.request.UpdateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;
import com.minesweeper.minesweeperapi.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@Slf4j
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> getAll() {
        log.info("[action:getAll]");
        List<GameResponse> gameResponse = gameService.getAllGames();
        return ResponseEntity.ok(gameResponse);
    }

    @GetMapping(value = "/{gameId}", consumes = "application/json")
    public ResponseEntity<?> getById(@PathVariable Long gameId) {
        log.info("[action:getById]");
        GameResponse gameResponse = gameService.getGameById(gameId);
        return ResponseEntity.ok(gameResponse);
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> createGame(@RequestBody CreateGameRequest createGameRequest) {
        log.info("[action:createGame][cols:{}][rows:{}]", createGameRequest.getCols(), createGameRequest.getRows());
        GameResponse gameResponse = gameService.createGame(createGameRequest);
        return ResponseEntity.ok(gameResponse);
    }

    @PutMapping(value = "/{gameId}", consumes = "application/json")
    public ResponseEntity<?> updateGame(@PathVariable Long gameId,
                                        @RequestBody UpdateGameRequest updateGameRequest) {
        log.info("[action:updateGame][posX:{}][posY:{}]", updateGameRequest.getPosX(), updateGameRequest.getPosY());
        GameResponse gameResponse = gameService.updateGame(gameId, updateGameRequest);
        return ResponseEntity.ok(gameResponse);
    }

    @PutMapping(value = "/{gameId}/flag", consumes = "application/json")
    public ResponseEntity<?> updateCellWithRedFlag(@PathVariable Long gameId,
                                        @RequestBody UpdateGameRequest updateGameRequest) {
        log.info("[action:updateCellWithRedFlag][posX:{}][posY:{}]", updateGameRequest.getPosX(), updateGameRequest.getPosY());
        GameResponse gameResponse = gameService.markCell(gameId, updateGameRequest, CellMark.FLAG);
        return ResponseEntity.ok(gameResponse);
    }

    @PutMapping(value = "/{gameId}/question", consumes = "application/json")
    public ResponseEntity<?> updateCellWithQuestionMark(@PathVariable Long gameId,
                                        @RequestBody UpdateGameRequest updateGameRequest) {
        log.info("[action:updateCellWithQuestionMark][posX:{}][posY:{}]", updateGameRequest.getPosX(), updateGameRequest.getPosY());
        GameResponse gameResponse = gameService.markCell(gameId, updateGameRequest, CellMark.QUESTION);
        return ResponseEntity.ok(gameResponse);
    }
}
