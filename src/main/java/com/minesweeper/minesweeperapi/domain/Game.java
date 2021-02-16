package com.minesweeper.minesweeperapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    private Long id;

    private GameStatus status;

    private Cell[][] cells;
}
