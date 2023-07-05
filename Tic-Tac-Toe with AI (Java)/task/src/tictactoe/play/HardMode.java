package tictactoe.play;

import java.util.HashMap;

public class HardMode extends Mode {
	final int NUMBER = 2;
	HashMap<String, Integer> lookupTable = new HashMap<>();

	HardMode(String player1, String player2) {
		super(player1, player2);
	}

	void addItemsToLookupTable(char turn, char opposingTurn) {
		lookupTable.put(String.valueOf(turn), 1);
		lookupTable.put("tie", 0);
		lookupTable.put(String.valueOf(opposingTurn), -1);
	}

	@Override
	void computerInput() {
		GameStatus gameStatus = new GameStatus();
		gameStatus.setTicTacToe(ticTacToeTable);

		char playerTurn = gameStatus.turn() ? 'X': 'O';
		char opposingPlayerTurn = gameStatus.turn() ? 'O': 'X';

		int bestScore = NUMBER * -1;
		int xCoordinates = 0;
		int yCoordinates = 0;
		addItemsToLookupTable(playerTurn, opposingPlayerTurn);

		System.out.println("Making move level \"hard\"");
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				if (!isOccupied(ticTacToeTable, i, j)) {
					ticTacToeTable[i][j] = playerTurn;
					int score = optimalMove(ticTacToeTable, false, playerTurn, opposingPlayerTurn);
					if (score > bestScore) {
						bestScore = score;
						xCoordinates = i;
						yCoordinates = j;
					}
					ticTacToeTable[i][j] = ' ';
				}
			}
		}

		ticTacToeTable[xCoordinates][yCoordinates] = playerTurn;
		printTable(ticTacToeTable);
	}

	int optimalMove(char[][] ticTacToe, boolean isMaximising, char playerTurn, char opposingPlayerTurn) {
		String result = checkWinner();
		if (result != null) {
			return lookupTable.get(result);
		}

		int bestScore;
		if (isMaximising) {
			bestScore = NUMBER * -1;
			for (int i = 0; i < LENGTH; i++) {
				for (int j = 0; j < LENGTH; j++) {
					if (!isOccupied(ticTacToe, i, j)) {
						ticTacToe[i][j] = playerTurn;
						int score = optimalMove(ticTacToe, false, playerTurn, opposingPlayerTurn);
						ticTacToe[i][j] = ' ';
						bestScore = Math.max(bestScore, score);
					}
				}
			}
			return bestScore;
		} else {
			bestScore = NUMBER;
			for (int i = 0; i < LENGTH; i++) {
				for (int j = 0; j < LENGTH; j++) {
					if (!isOccupied(ticTacToe, i, j)) {
						ticTacToe[i][j] = opposingPlayerTurn;
						int score = optimalMove(ticTacToe, true, playerTurn, opposingPlayerTurn);
						ticTacToe[i][j] = ' ';
						bestScore = Math.min(bestScore, score);
					}
				}
			}
			return bestScore;
		}
	}

	String checkWinner() {
		GameStatus gameStatus = new GameStatus();
		gameStatus.setTicTacToe(ticTacToeTable);
		String player = gameStatus.turn() ? "O": "X";

		if (gameStatus.hasWinner(player, false)) {
			return player;
		}

		int counter = 0;
		for (char[] ticTacToe: ticTacToeTable) {
			for (char chars: ticTacToe) {
				if (chars != ' ') {
					counter++;
				}
			}
		}

		if (counter == 9) {
			return "tie";
		}
		return null;
	}
}
