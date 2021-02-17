package com.minesweeper.minesweeperapi.dto.request;

import lombok.Data;

@Data
public class UpdateGameRequest {

    private int posX;

    private int posY;
}
