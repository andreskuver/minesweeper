package com.minesweeper.minesweeperapi.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Wrapper for application configurations
 * It load the configs from application.properties file
 */
@Data
@Component
public class Config {

    @Value("${cols.max}")
    private long colsMax;

    @Value("${cols.min}")
    private long colsMin;

    @Value("${rows.max}")
    private long rowsMax;

    @Value("${rows.min}")
    private long rowsMin;
}
