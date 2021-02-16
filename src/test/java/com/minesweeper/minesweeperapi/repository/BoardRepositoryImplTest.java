package com.minesweeper.minesweeperapi.repository;

import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.repository.impl.BoardRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void testAddMinesToBoardCells() {
        int cols = 10;
        int rows = 11;
        int mines = 5;

        Cell[][] board = boardRepository.initializeBoard(cols, rows);
        boardRepository.addMinesToBoardCells(board, mines);

        List<Cell> cellsWithMines = Arrays.stream(board).flatMap(row -> Arrays.stream(row).filter(Cell::isMine))
                .collect(Collectors.toList());

        assertEquals(mines, cellsWithMines.size());
    }

    @Test
    public void testAdjacentMinesCount() {
        int cols = 10;
        int rows = 11;
        int mines = 5;

        Cell[][] board = boardRepository.initializeBoard(cols, rows);
        boardRepository.addMinesToBoardCells(board, mines);

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (board[x][y].isMine()) {
                    assertAdjacentMines(board, x, y);
                }
            }
        }
    }

    ///
    /// Utils fot test
    ///
    private void assertAdjacentMines(Cell[][] board, int x, int y) {
        assertAdjacent(board, x - 1, y - 1);
        assertAdjacent(board, x - 1, y);
        assertAdjacent(board,x - 1, y + 1);
        assertAdjacent(board, x,y - 1);
        assertAdjacent(board, x, y + 1);
        assertAdjacent(board, x + 1, y - 1);
        assertAdjacent(board, x + 1, y);
        assertAdjacent(board,x + 1, y + 1);
    }

   private void assertAdjacent(Cell[][] board, int x, int y) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
            assertTrue(board[x][y].getAdjacentMines() > 0);
        }
   }
}
