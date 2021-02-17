package com.minesweeper.minesweeperapi.service.impl;

import com.minesweeper.minesweeperapi.configuration.Config;
import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.CellMark;
import com.minesweeper.minesweeperapi.domain.Game;
import com.minesweeper.minesweeperapi.domain.GameStatus;
import com.minesweeper.minesweeperapi.domain.repository.BoardRepository;
import com.minesweeper.minesweeperapi.domain.repository.GameRepository;
import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.request.UpdateGameRequest;
import com.minesweeper.minesweeperapi.dto.response.GameResponse;
import com.minesweeper.minesweeperapi.exception.GameNotExistsException;
import com.minesweeper.minesweeperapi.exception.InvalidInputForCreateGameException;
import com.minesweeper.minesweeperapi.service.GameService;
import com.minesweeper.minesweeperapi.utils.GameMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private Config config;

    private BoardRepository boardRepository;

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(Config config,
                            BoardRepository boardRepository,
                            GameRepository gameRepository
                            ) {
        this.config = config;
        this.boardRepository = boardRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public GameResponse createGame(CreateGameRequest createGameRequest) {
        // Validate rows and cols size
        validateInput(createGameRequest);

        Cell[][] boardCells = boardRepository.initializeBoard(createGameRequest.getCols(), createGameRequest.getRows());
        boardRepository.addMinesToBoardCells(boardCells, createGameRequest.getMines());

        // Create new Game
        Game game = Game.builder()
                .status(GameStatus.PLAYING)
                .cells(boardCells)
                .build();

        gameRepository.save(game);

        // Finally map the Game to a representational object
        return GameMapper.from(game);
    }

    public GameResponse updateGame(Long gameId, UpdateGameRequest updateGameRequest) {
        Optional<Game> optionalGame = gameRepository.findByIdAndStatus(gameId, GameStatus.PLAYING);

        if (!optionalGame.isPresent()) {
            throw new GameNotExistsException(String.format("Game %d not exists or is finished", gameId));
        }

        Game game = optionalGame.get();
        Cell cell = game.getCells()[updateGameRequest.getPosX()][updateGameRequest.getPosY()];
        cell.setUncovered(true);

        if (cell.isMine()) {
            game.setStatus(GameStatus.DEFEAT);
            log.info("[action:updateGame][message:DEFEAT]");
        } else {

            boolean isTheGameFinishedWithVictory = isVictory(game.getCells());
            if (isTheGameFinishedWithVictory) {
                game.setStatus(GameStatus.VICTORY);
                log.info("[action:updateGame][message:VICTORY]");
            }
        }

        gameRepository.save(game);

        // Finally map the Game to a representational object
        return GameMapper.from(game);
    }

    public GameResponse markCell(Long gameId, UpdateGameRequest updateGameRequest, CellMark cellMark) {
        Optional<Game> optionalGame = gameRepository.findByIdAndStatus(gameId, GameStatus.PLAYING);

        if (!optionalGame.isPresent()) {
            throw new GameNotExistsException(String.format("Game %d not exists or is finished", gameId));
        }

        Game game = optionalGame.get();
        Cell cell = game.getCells()[updateGameRequest.getPosX()][updateGameRequest.getPosY()];

        // Only mark the cell if it is covered
        if (!cell.isUncovered()) {
            cell.setFlag(cellMark);

            gameRepository.save(game);
        }

        // Finally map the Game to a representational object
        return GameMapper.from(game);
    }

    private void validateInput(CreateGameRequest createGameRequest) {
        int reqCols = createGameRequest.getCols();
        int reqRows = createGameRequest.getRows();
        if (reqCols > config.getColsMax() || reqCols< config.getColsMin()
        || reqRows > config.getRowsMax() || reqRows < config.getRowsMin()) {
            throw new InvalidInputForCreateGameException("Rows or Cols size is invalid");
        }

        int reqMines = createGameRequest.getMines();
        if (reqMines < 1 || reqMines > reqCols * reqRows) {
            throw new InvalidInputForCreateGameException("Mines number is invalid");
        }
    }

    private boolean isVictory(Cell[][] boardCells) {
        return boardRepository.allCellsAreUncovered(boardCells);
    }
}
