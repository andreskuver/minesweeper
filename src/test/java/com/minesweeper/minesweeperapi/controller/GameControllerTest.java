package com.minesweeper.minesweeperapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minesweeper.minesweeperapi.dto.request.CreateGameRequest;
import com.minesweeper.minesweeperapi.dto.request.UpdateGameRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createNewGameWithoutBodyShouldReturnBadRequest() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/game").contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewGameWithInvalidRowsAndColsShouldReturnBadRequest() throws Exception {
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.setCols(51);
        createGameRequest.setRows(51);

        this.mockMvc
                .perform(post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createGameRequest))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Rows or Cols size is invalid"));
    }

    @Test
    public void createNewGameWithInvalidMinesShouldReturnBadRequest() throws Exception {
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.setCols(10);
        createGameRequest.setRows(10);
        createGameRequest.setMines(101);

        this.mockMvc
                .perform(post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createGameRequest))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Mines number is invalid"));
    }

    @Test
    public void shouldCreateANewGame() throws Exception {
        CreateGameRequest createGameRequest = new CreateGameRequest();
        createGameRequest.setCols(4);
        createGameRequest.setRows(4);
        createGameRequest.setMines(2);

        this.mockMvc
                .perform(post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createGameRequest))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.id").value("100")
                )
                .andExpect(
                        jsonPath("$.status").value("PLAYING")
                );
    }

    @Test
    public void shouldReturnNotFoundWhenGameNotExists() throws Exception {
        UpdateGameRequest updateGameRequest = new UpdateGameRequest();
        updateGameRequest.setPosX(3);
        updateGameRequest.setPosY(4);

        this.mockMvc
                .perform(
                        put("/api/game/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(updateGameRequest))
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Game 1 not exists or is finished"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
