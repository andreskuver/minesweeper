package com.minesweeper.minesweeperapi.exception;

/**
 * This exception represent an error cause of trying to work with an non existing game
 */
public class GameNotExistsException extends RuntimeException {

    public GameNotExistsException(String message) {
        super(message);
    }
}
