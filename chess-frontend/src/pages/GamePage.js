import React, { useState, useEffect } from "react";
import { Chessboard } from "react-chessboard";
import { Chess } from "chess.js";

import { makeMove } from "../api/api";
import { connectSocket, sendMove } from "../websocket/socket";

const GamePage = ({ gameId, username, color }) => {

  const [game, setGame] = useState(new Chess());
  const [history, setHistory] = useState([]);
  // -----------------------------
  // WebSocket listener
  // -----------------------------
  useEffect(() => {

    connectSocket(gameId, (move) => {

      console.log("Move received:", move);

      setGame((prevGame) => {

        const chess = new Chess(prevGame.fen());

        try {

          const result = chess.move({
            from: move.substring(0, 2),
            to: move.substring(2, 4),
            promotion: "q"
          });

          if (!result) return prevGame;
          setHistory(chess.history());
          
          return chess;

        } catch (err) {
          console.log("Duplicate/invalid move ignored");
          return prevGame;
        }

      });

    });

  }, [gameId]);


  // -----------------------------
  // When player moves piece
  // -----------------------------
  const onPieceDrop = async (from, to) => {

    const chess = new Chess(game.fen());

    const move = chess.move({
      from,
      to,
      promotion: "q"
    });

    if (!move) return false;

    setGame(chess);
    setHistory(chess.history());

    const moveString = from + to;

    console.log("Sending move:", moveString);

    try {

      // Save move to backend
      await makeMove(gameId, moveString);

      // Send move via websocket
      sendMove(gameId, moveString);

    } catch (err) {
      console.log("Move API failed");
    }

    return true;
  };


  return (
  <div style={{ textAlign: "center", marginTop: "30px" }}>

    <h1 style={{
      position: "absolute",
      top: "15px",
      left: "30px",
      color: "#d6b85a",
      fontWeight: "bold"
    }}>
      ♟ Vikas Chess Arena
    </h1>
    <h2>Game #{gameId}</h2>
    <p>{username} playing as {color}</p>

    <div style={{ display: "flex", justifyContent: "center", gap: "40px" }}>

      {/* Chess Board */}
      
      <div style={{ width: "500px", position: "relative" }}>

  <div style={{
    position: "absolute",
    top: "40%",
    left: "25%",
    fontSize: "70px",
    opacity: "0.08",
    fontWeight: "bold",
    pointerEvents: "none"
  }}>
    Vikas
  </div>
        <Chessboard
          position={game.fen()}
          onPieceDrop={onPieceDrop}
          boardOrientation={color}
        />
      </div>

       
    
      {/* Move History */}
      <div style={{ width: "200px", textAlign: "left" }}>
        <h3>Move History</h3>

        {history.map((move, index) => (
          <div key={index}>
            {Math.floor(index / 2) + 1}. {move}
          </div>
        ))}

      </div>

    </div>

  </div>
);
};

export default GamePage;