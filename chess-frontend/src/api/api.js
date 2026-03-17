import axios from "axios";

const API_BASE = "http://localhost:8080/api/game";

export const createGame = (username) => {
  return axios.post(`${API_BASE}/create?username=${username}`);
};

export const joinGame = (gameId, username) => {
  return axios.post(`${API_BASE}/join?gameId=${gameId}&username=${username}`);
};

export const getGame = (gameId) => {
  return axios.get(`${API_BASE}/${gameId}`);
};

export const makeMove = (gameId, move) => {
  return axios.post(`${API_BASE}/move?gameId=${gameId}&move=${move}`);
};