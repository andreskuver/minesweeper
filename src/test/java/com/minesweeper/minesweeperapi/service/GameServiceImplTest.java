package com.minesweeper.minesweeperapi.service;

import com.minesweeper.minesweeperapi.configuration.Config;
import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.CellMark;
import com.minesweeper.minesweeperapi.domain.Game;
import com.minesweeper.minesweeperapi.domain.GameStatus;
import com.minesweeper.minesweeperapi.domain.repository.BoardRepository;
import com.minesweeper.minesweeperapi.domain.repository.GameRepository;
import com.minesweeper.minesweeperapi.dto.request.UpdateGameRequest;
import com.minesweeper.minesweeperapi.service.impl.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    private GameService gameService;

    @Mock
    private Config config;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void init() {
        gameService = new GameServiceImpl(config, boardRepository, gameRepository);
    }

    @Test
    public void testAddFlagToCoveredCell() {
        long gameId = 123L;
        UpdateGameRequest gameRequest = new UpdateGameRequest();
        gameRequest.setPosX(1);
        gameRequest.setPosY(1);

        Cell[][] cells = new Cell[2][2];
        Cell cell = new Cell();
        cells[1][1] = cell;

        Game game = new Game();
        game.setId(gameId);
        game.setCells(cells);

        when(gameRepository.findByIdAndStatus(gameId, GameStatus.PLAYING)).thenReturn(Optional.of(game));
        when(gameRepository.save(game)).thenReturn(game);

        gameService.markCell(gameId, gameRequest, CellMark.FLAG);

        assertEquals(cells[1][1].getFlag(), CellMark.FLAG);
    }

    @Test
    public void testAddFlagToUncoveredCell() {
        long gameId = 123L;
        UpdateGameRequest gameRequest = new UpdateGameRequest();
        gameRequest.setPosX(1);
        gameRequest.setPosY(1);

        Cell[][] cells = new Cell[2][2];
        Cell cell = new Cell();
        cell.setUncovered(true);
        cells[1][1] = cell;

        Game game = new Game();
        game.setId(gameId);
        game.setCells(cells);

        when(gameRepository.findByIdAndStatus(gameId, GameStatus.PLAYING)).thenReturn(Optional.of(game));

        gameService.markCell(gameId, gameRequest, CellMark.FLAG);

        assertNull(cells[1][1].getFlag());
    }

    @Test
    public void testAddQuestionToCoveredCell() {
        long gameId = 123L;
        UpdateGameRequest gameRequest = new UpdateGameRequest();
        gameRequest.setPosX(1);
        gameRequest.setPosY(1);

        Cell[][] cells = new Cell[2][2];
        Cell cell = new Cell();
        cells[1][1] = cell;

        Game game = new Game();
        game.setId(gameId);
        game.setCells(cells);

        when(gameRepository.findByIdAndStatus(gameId, GameStatus.PLAYING)).thenReturn(Optional.of(game));
        when(gameRepository.save(game)).thenReturn(game);

        gameService.markCell(gameId, gameRequest, CellMark.QUESTION);

        assertEquals(cells[1][1].getFlag(), CellMark.QUESTION);
    }

    @Test
    public void testAddQuestionToUncoveredCell() {
        long gameId = 123L;
        UpdateGameRequest gameRequest = new UpdateGameRequest();
        gameRequest.setPosX(1);
        gameRequest.setPosY(1);

        Cell[][] cells = new Cell[2][2];
        Cell cell = new Cell();
        cell.setUncovered(true);
        cells[1][1] = cell;

        Game game = new Game();
        game.setId(gameId);
        game.setCells(cells);

        when(gameRepository.findByIdAndStatus(gameId, GameStatus.PLAYING)).thenReturn(Optional.of(game));

        gameService.markCell(gameId, gameRequest, CellMark.QUESTION);

        assertNull(cells[1][1].getFlag());
    }

}
