package com.minesweeper.minesweeperapi.dto.request;

import lombok.Data;

@Data
public class CreateGameRequest {
    private int cols;
    private int rows;
}
