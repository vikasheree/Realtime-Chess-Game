import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

let stompClient = null;

export const connectSocket = (gameId, onMove) => {

  const socket = new SockJS("http://localhost:8080/chess");

  stompClient = new Client({
    webSocketFactory: () => socket,
    debug: () => {}
  });

  stompClient.onConnect = () => {

    console.log("WebSocket Connected");

    stompClient.subscribe(`/topic/game/${gameId}`, (message) => {
      onMove(message.body);
    });

  };

  stompClient.activate();
};

export const sendMove = (gameId, move) => {

  if (!stompClient) return;

  stompClient.publish({
    destination: "/app/move",
    body: JSON.stringify({ gameId, move })
  });

};