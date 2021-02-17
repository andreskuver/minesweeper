package com.minesweeper.minesweeperapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell {

    private boolean uncovered;

    private boolean isMine;

    private int adjacentMines;

    private CellMark flag;
}
