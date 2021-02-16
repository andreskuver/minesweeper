package com.minesweeper.minesweeperapi.domain.repository.impl;

import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.repository.BoardRepository;
import org.springframework.stereotype.Service;

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
}
