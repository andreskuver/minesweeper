package com.minesweeper.minesweeperapi.utils;

import com.minesweeper.minesweeperapi.domain.Game;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;

/**
 * Mapper to easily the transformation from domain to representational objects
 */
public class GameMapper {

    /**
     * Receive and domain entity Game and map it
     * to an representational object GameResponse
     */
    public static GameResponse from(Game game) {
        GameResponse gameResponse = new GameResponse();
        gameResponse.setId(game.getId());
        gameResponse.setStatus(game.getStatus());
        gameResponse.setCells(game.getCells());
        return gameResponse;
    }
}
