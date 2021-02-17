package com.minesweeper.minesweeperapi.controller;

import com.minesweeper.minesweeperapi.dto.response.ErrorResponse;
import com.minesweeper.minesweeperapi.exception.GameNotExistsException;
import com.minesweeper.minesweeperapi.exception.InvalidInputForCreateGameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler to centralize (here we can log, trace, etc)
 * and normalize the API error messages. This class map the domain errors
 * to HTTP responses with the respective HTTP Status Codes.
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            InvalidInputForCreateGameException.class,
            RuntimeException.class,
            Exception.class
    })
    public ResponseEntity<ErrorResponse> responseHandleException(final Exception exception) {
        ErrorResponse errorResponse;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        logger.error(exception.getMessage(), exception);

        // InvalidInputForCreateGameException is treated as Bad Request HTTP status
        if (exception instanceof InvalidInputForCreateGameException) {
            status = HttpStatus.BAD_REQUEST;
            errorResponse = new ErrorResponse(exception.getMessage());
            return customHandler(errorResponse, status);
        }

        // GameNotExistsException is treated as Not Found HTTP status
        if (exception instanceof GameNotExistsException) {
            status = HttpStatus.NOT_FOUND;
            errorResponse = new ErrorResponse(exception.getMessage());
            return customHandler(errorResponse, status);
        }

        // Otherwise, an unexpected error occurred
        errorResponse = new ErrorResponse("Internal Server Error");
        return customHandler(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> customHandler(ErrorResponse exceptionResponse, HttpStatus status){
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
