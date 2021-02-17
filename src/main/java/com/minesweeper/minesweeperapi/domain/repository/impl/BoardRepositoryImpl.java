package com.minesweeper.minesweeperapi.domain.repository.impl;

import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
public class BoardRepositoryImpl implements BoardRepository {

    public Cell[][] initializeBoard(int cols, int rows) {

        return IntStream
                .range(0, rows)
                .mapToObj(i ->
                        IntStream.range(0, cols)
                                .mapToObj(j -> new Cell())
                                .toArray(Cell[]::new)
                )
                .toArray(Cell[][]::new);
    }

    public void addMinesToBoardCells(Cell[][] boardCells, int mines) {
        int rows = boardCells.length;
        int cols = boardCells[0].length;

        int boardMines = 0;
        Random randGenerator = new Random();
        do {
            int x = randGenerator.nextInt(rows);
            int y = randGenerator.nextInt(cols);
            if (!boardCells[x][y].isMine()) {
                boardCells[x][y].setMine(true);
                updateAdjacentMines(boardCells, x, y);
                boardMines++;
            }
        } while (boardMines < mines);
    }

    public void updateAdjacentMines(Cell[][] boardCells, int x, int y) {
        incrementAdjacentMines(boardCells, x - 1, y - 1);
        incrementAdjacentMines(boardCells, x - 1, y);
        incrementAdjacentMines(boardCells,x - 1, y + 1);
        incrementAdjacentMines(boardCells, x,y - 1);
        incrementAdjacentMines(boardCells, x, y + 1);
        incrementAdjacentMines(boardCells, x + 1, y - 1);
        incrementAdjacentMines(boardCells, x + 1, y);
        incrementAdjacentMines(boardCells,x + 1, y + 1);
    }

    public boolean allCellsAreUncovered(Cell[][] boardCells) {
        for (Cell[] row : boardCells) {
            for (Cell cell : row) {
                if (!cell.isMine() && !cell.isUncovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void uncoverNeighbours(Cell[][] boardCells, int posX, int posY) {
        int maxRows = boardCells.length;
        int maxCols = boardCells[0].length;

        if (posX >= 0 && posX < maxCols && posY >= 0 && posY < maxRows) {

            Cell cell = boardCells[posY][posX];

            // Only continue uncovering neighbours if there is not adjacent mines and
            // the cell is covered
            if (cell.getAdjacentMines() == 0 && !cell.isUncovered()) {

                // Uncover the cell
                boardCells[posY][posX].setUncovered(true);

                /*
                    Explore all the cells in the 8 adjacent
                    cells

                        N.W   N   N.E
                          \   |   /
                           \  |  /
                        W----Cell----E
                             / | \
                           /   |  \
                        S.W    S   S.E

                    N -->  North        (row-1, col)
                    S -->  South        (row+1, col)
                    E -->  East         (row, col+1)
                    W -->  West         (row, col-1)
                    N.E--> North-East   (row-1, col+1)
                    N.W--> North-West   (row-1, col-1)
                    S.E--> South-East   (row+1, col+1)
                    S.W--> South-West   (row+1, col-1)
                */

                uncoverNeighbours(boardCells, posX + 1, posY);
                uncoverNeighbours(boardCells, posX + 1, posY + 1);
                uncoverNeighbours(boardCells, posX + 1, posY - 1);
                uncoverNeighbours(boardCells, posX - 1, posY);
                uncoverNeighbours(boardCells, posX - 1, posY + 1);
                uncoverNeighbours(boardCells, posX - 1, posY - 1);
                uncoverNeighbours(boardCells, posX, posY - 1);
                uncoverNeighbours(boardCells, posX, posY + 1);
            }
        }
    }

    private void incrementAdjacentMines(Cell[][] board, int x, int y) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
            int adjacentMines = board[x][y].getAdjacentMines() + 1;
            board[x][y].setAdjacentMines(adjacentMines);
        }
    }
}
