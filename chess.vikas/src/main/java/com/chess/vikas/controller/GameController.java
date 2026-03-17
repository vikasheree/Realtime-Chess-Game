package com.chess.vikas.controller;

import com.chess.vikas.entity.Game;
import com.chess.vikas.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/create")
    public Game createGame(@RequestParam String username) {
        return gameService.createGame(username);
    }

    @PostMapping("/join")
    public Game joinGame(@RequestParam Long gameId,
                         @RequestParam String username) {
        return gameService.joinGame(gameId, username);
    }

    @PostMapping("/move")
    public Game makeMove(@RequestParam Long gameId,
                         @RequestParam String move) {
        return gameService.makeMove(gameId, move);
    }

    @GetMapping("/{id}")
public Game getGame(@PathVariable Long id) {
    return gameService.getGame(id);
}
}
