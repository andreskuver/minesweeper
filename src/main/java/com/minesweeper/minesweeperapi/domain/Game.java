package com.minesweeper.minesweeperapi.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@SequenceGenerator(
        name = "games_sequence",
        sequenceName = "games_sequence",
        initialValue = 100
)
@Entity(name = "GAME")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="games_sequence")
    private Long id;

    @Column
    private GameStatus status;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Cell[][] cells;
}
