package com.minesweeper.minesweeperapi.domain.repository;

import com.minesweeper.minesweeperapi.domain.Game;
import com.minesweeper.minesweeperapi.domain.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByIdAndStatus(Long id, GameStatus status);
}
