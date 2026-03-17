package com.chess.vikas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerWhite;

    private String playerBlack;

    private String status;

    @Column(length = 2000)
    private String moves;
}