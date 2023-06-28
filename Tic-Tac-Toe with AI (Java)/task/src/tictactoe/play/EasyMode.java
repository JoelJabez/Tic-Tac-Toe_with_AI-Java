package tictactoe.play;

import static tictactoe.play.GameStatus.isGameOver;
import static tictactoe.play.GameStatus.turn;

class EasyMode extends Mode {

	EasyMode(String player1, String player2) {
		super(player1, player2);
	}

	@Override
	void battleAgainst() {
		createEmptyBoard();
		printTable(ticTacToeTable);
		do {
			switch (getPlayer1()) {
				case "user" -> userInput();
				case "computer" -> computerInput();
			}

			if (isGameOver(ticTacToeTable)) {
				return;
			}

			switch (getPlayer2()) {
				case "user" -> userInput();
				case "computer" -> computerInput();
			}
		} while(!isGameOver(ticTacToeTable));
	}

	@Override
	void computerInput() {
		int xCoordinate;
		int yCoordinate;
		System.out.println("Making move level \"easy\"");
		do {
			xCoordinate = random.nextInt(3);
			yCoordinate = random.nextInt(3);
		} while(isOccupied(ticTacToeTable, yCoordinate, xCoordinate));
		ticTacToeTable[xCoordinate][yCoordinate] = turn() ? 'X': 'O';
		printTable(ticTacToeTable);
	}
}
