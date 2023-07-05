package tictactoe.play;

import java.util.HashMap;

public class HardMode extends Mode {

	HashMap<String, Integer> lookupTable = new HashMap<>();
	HardMode(String player1, String player2) {
		super(player1, player2);
	}

	void addItemsToLookupTable() {
		GameStatus gameStatus = new GameStatus();
		char turn = gameStatus.turn() ? 'O': 'X';
		char opposingTurn = gameStatus.turn() ? 'X': 'O';

		lookupTable.put(String.valueOf(turn), 1);
		lookupTable.put("tie", 0);
		lookupTable.put(String.valueOf(opposingTurn), -1);
	}

	@Override
	void computerInput() {
		GameStatus gameStatus = new GameStatus();
		addItemsToLookupTable();

		gameStatus.setTicTacToe(ticTacToeTable);
		char turn = gameStatus.turn() ? 'X': 'O';
		char opposingTurn = gameStatus.turn() ? 'O': 'X';

		int xCoordinates = 0;
		int yCoordinates = 0;
		int bestScore = (int) Double.NEGATIVE_INFINITY;
		System.out.println("Making move level \"hard\"");
		for (int i = 0; i < ticTacToeTable.length; i++) {
			for (int j = 0; j < ticTacToeTable.length; j++) {
				if (!isOccupied(ticTacToeTable, i, j)) {
					ticTacToeTable[i][j] = turn;
					int score = minimax(ticTacToeTable, false, turn, opposingTurn);
					if (score > bestScore) {
						bestScore = score;
						xCoordinates = i;
						yCoordinates = j;
					}
					ticTacToeTable[i][j] = ' ';
				}
			}
		}

		ticTacToeTable[xCoordinates][yCoordinates] = turn;
		printTable(ticTacToeTable);
	}

	int minimax(char[][] ticTacToe, boolean isMaximising, char turn, char opposingTurn) {
		String result = checkWinner();
		if (result != null) {
			return lookupTable.get(result);
		}

		int bestScore;
		int length = ticTacToe.length;
		if (isMaximising) {
			bestScore = (int) Double.NEGATIVE_INFINITY;
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					if (!isOccupied(ticTacToe, i, j)) {
						ticTacToe[i][j] = turn;
						int score = minimax(ticTacToe, false, turn, opposingTurn);
						ticTacToe[i][j] = ' ';
						bestScore = Math.max(bestScore, score);
					}
				}
			}
			return bestScore;
		} else {
			bestScore = (int) Double.POSITIVE_INFINITY;
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					if (!isOccupied(ticTacToe, i, j)) {
						ticTacToe[i][j] = opposingTurn;
						int score = minimax(ticTacToe, true, turn, opposingTurn);
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
