package com.chess.vikas.service;

import com.chess.vikas.entity.Game;
import com.chess.vikas.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game createGame(String username) {

        Game game = new Game();

        game.setPlayerWhite(username);
        game.setStatus("WAITING");
        game.setMoves("");

        return gameRepository.save(game);
    }


    public Game joinGame(Long gameId, String username) {

    Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new RuntimeException("Game not found"));

    // If already joined
    if (game.getPlayerBlack() != null) {
        throw new RuntimeException("Game already has 2 players");
    }

    game.setPlayerBlack(username);
    game.setStatus("ACTIVE");

    return gameRepository.save(game);
}

public Game makeMove(Long gameId, String move) {

    Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new RuntimeException("Game not found"));

    String moves = game.getMoves();

    if (moves == null || moves.isEmpty()) {
        game.setMoves(move);
    } else {
        game.setMoves(moves + "," + move);
    }

    return gameRepository.save(game);
}

public Game getGame(Long id) {
    return gameRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Game not found"));
}
}
