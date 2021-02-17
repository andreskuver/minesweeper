package com.minesweeper.minesweeperapi.exception;

/**
 * This exception represent an error cause of invalid input for create game
 */
public class InvalidInputForCreateGameException extends RuntimeException {

    public InvalidInputForCreateGameException(String message) {
        super(message);
    }
}
