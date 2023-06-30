package tictactoe.play;

class EasyMode extends Mode {

	EasyMode(String player1, String player2) {
		super(player1, player2);
	}

	@Override
	void battleAgainst() {
		GameStatus gameStatus = new GameStatus();
		createEmptyBoard();
		printTable(ticTacToeTable);
		do {
			switch (getPlayer1()) {
				case "user" -> userInput();
				case "computer" -> computerInput();
			}

			if (gameStatus.isGameOver(ticTacToeTable)) {
				return;
			}

			switch (getPlayer2()) {
				case "user" -> userInput();
				case "computer" -> computerInput();
			}
		} while(!gameStatus.isGameOver(ticTacToeTable));
	}

	@Override
	void computerInput() {
		GameStatus gameStatus = new GameStatus();
		gameStatus.setTicTacToe(ticTacToeTable);

		int xCoordinate;
		int yCoordinate;
		System.out.println("Making move level \"easy\"");
		do {
			xCoordinate = random.nextInt(3);
			yCoordinate = random.nextInt(3);
		} while(isOccupied(ticTacToeTable, xCoordinate, yCoordinate));
		ticTacToeTable[xCoordinate][yCoordinate] = gameStatus.turn() ? 'X': 'O';
		printTable(ticTacToeTable);
	}
}
