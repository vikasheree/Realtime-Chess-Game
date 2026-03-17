import React, { useState } from "react";
import { createGame, joinGame } from "../api/api";

const LobbyPage = ({ onGameStart }) => {

  const [username, setUsername] = useState("");
  const [gameId, setGameId] = useState("");

  const handleCreate = async () => {
    const res = await createGame(username);
    onGameStart(res.data.id, username, "white");
  };

  const handleJoin = async () => {
    const res = await joinGame(gameId, username);
    onGameStart(res.data.id, username, "black");
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>

      <h1>Chess Lobby</h1>

      <input
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />

      <br /><br />

      <button onClick={handleCreate}>
        Create Game
      </button>

      <br /><br />

      <input
        placeholder="Game ID"
        value={gameId}
        onChange={(e) => setGameId(e.target.value)}
      />

      <br /><br />

      <button onClick={handleJoin}>
        Join Game
      </button>

    </div>
  );
};

export default LobbyPage;