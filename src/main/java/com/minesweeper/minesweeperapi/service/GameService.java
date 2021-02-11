package com.minesweeper.minesweeperapi.service;

import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;

public interface GameService {

    /**
     * Create a new Game for the given request properties
     */
    GameResponse createGame(CreateGameRequest createGameRequest);
}
