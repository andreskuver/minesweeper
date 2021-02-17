package com.minesweeper.minesweeperapi.domain.repository;

import com.minesweeper.minesweeperapi.domain.Cell;

public interface BoardRepository {

    Cell[][] initializeBoard(int cols, int rows);

    void addMinesToBoardCells(Cell[][] boardCells, int mines);

    boolean allCellsAreUncovered(Cell[][] boardCells);
}
