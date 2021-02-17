package com.minesweeper.minesweeperapi.service;

import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.request.UpdateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;

public interface GameService {

    /**
     * Create a new Game for the given request properties
     */
    GameResponse createGame(CreateGameRequest createGameRequest);

    /**
     * Update an existing Game with the given request properties
     */
    GameResponse updateGame(Long gameId, UpdateGameRequest updateGameRequest);
}
