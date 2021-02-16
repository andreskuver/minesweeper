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
                boardMines++;
            }
        } while (boardMines < mines);
    }
}
