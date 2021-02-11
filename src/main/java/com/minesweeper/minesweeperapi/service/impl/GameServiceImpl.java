package com.minesweeper.minesweeperapi.service.impl;

import com.minesweeper.minesweeperapi.configuration.Config;
import com.minesweeper.minesweeperapi.domain.Game;
import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;
import com.minesweeper.minesweeperapi.exception.InvalidInputForCreateGameException;
import com.minesweeper.minesweeperapi.service.GameService;
import com.minesweeper.minesweeperapi.utils.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private Config config;

    @Autowired
    private GameServiceImpl(Config config) {
        this.config = config;
    }

    @Override
    public GameResponse createGame(CreateGameRequest createGameRequest) {
        // Validate rows and cols size
        validateInput(createGameRequest);

        // Create new Game
        Game game = Game.builder().id(1L).build();

        // Finally map the Game to a representational object
        return GameMapper.from(game);
    }

    private void validateInput(CreateGameRequest createGameRequest) {

        if (createGameRequest.getCols() > config.getColsMax() || createGameRequest.getCols() < config.getColsMin()
        || createGameRequest.getRows() > config.getRowsMax() || createGameRequest.getRows() < config.getRowsMin()) {
            throw new InvalidInputForCreateGameException("Rows or Cols size is invalid");
        }
    }
}
