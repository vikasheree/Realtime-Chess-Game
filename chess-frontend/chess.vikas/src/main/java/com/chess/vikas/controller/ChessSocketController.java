package com.chess.vikas.controller;

import com.chess.vikas.dto.MoveMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChessSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/move")
    public void sendMove(MoveMessage move) {

        messagingTemplate.convertAndSend(
                "/topic/game/" + move.getGameId(),
                move.getMove()
        );

    }
}
