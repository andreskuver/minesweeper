package com.minesweeper.minesweeperapi.repository;

import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.repository.impl.BoardRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class BoardRepositoryImplTest {

    private BoardRepositoryImpl boardRepository = new BoardRepositoryImpl();

    @Test
    public void testInitializeBoardShouldReturnColsXRowsSizedBoard() {
        int cols = 10;
        int rows = 11;
        Cell[][] board = boardRepository.initializeBoard(cols, rows);
        assertEquals(rows, board.length);
        assertEquals(cols, board[0].length);
    }

    @Test
    public void testInitializeBoardShouldReturnAllCellsUncovered() {
        int cols = 10;
        int rows = 11;
        Cell[][] board = boardRepository.initializeBoard(cols, rows);
        assertEquals(rows, board.length);
        assertEquals(cols, board[0].length);
        Arrays.stream(board).forEach(row -> Arrays.stream(row).forEach(cell -> assertFalse(cell.isUncovered())));
    }
}
