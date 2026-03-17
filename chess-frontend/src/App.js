import React, { useState } from "react";
import LobbyPage from "./pages/LobbyPage";
import GamePage from "./pages/GamePage";

function App() {

  const [game, setGame] = useState(null);

  const startGame = (gameId, username, color) => {
    setGame({ gameId, username, color });
  };

  if (!game) {
    return <LobbyPage onGameStart={startGame} />;
  }

  return (
    <GamePage
      gameId={game.gameId}
      username={game.username}
      color={game.color}
    />
  );
}

export default App;