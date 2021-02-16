package com.minesweeper.minesweeperapi.dto.response;

import com.minesweeper.minesweeperapi.domain.Cell;
import com.minesweeper.minesweeperapi.domain.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {

    private Long id;

    private GameStatus status;

    private Cell[][] cells;
}
