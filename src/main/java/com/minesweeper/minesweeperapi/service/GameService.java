package com.minesweeper.minesweeperapi.service;

import com.minesweeper.minesweeperapi.domain.CellMark;
import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.request.UpdateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;

import java.util.List;

public interface GameService {

    /**
     * Get all games
     */
    List<GameResponse> getAllGames();

    /**
     * Get game by Id
     */
    GameResponse getGameById(long gameId);

    /**
     * Create a new Game for the given request properties
     */
    GameResponse createGame(CreateGameRequest createGameRequest);

    /**
     * Update an existing Game with the given request properties
     */
    GameResponse updateGame(Long gameId, UpdateGameRequest updateGameRequest);

    /**
     * Mark a cell with a Flag or Question
     */
    GameResponse markCell(Long gameId, UpdateGameRequest updateGameRequest, CellMark cellMark);
}
