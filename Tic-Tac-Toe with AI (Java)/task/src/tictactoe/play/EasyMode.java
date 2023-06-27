package tictactoe.play;

import static tictactoe.play.GameStatus.isGameOver;
import static tictactoe.play.GameStatus.turn;

class EasyMode extends Mode {

	static void battleAgainst(String player1, String player2) {
		createEmptyBoard();
		printTable(ticTacToeTable);
		do {
			switch (player1) {
				case "user" -> userInput();
				case "computer" -> computerInput();
			}

			if (isGameOver(ticTacToeTable)) {
				return;
			}

			switch (player2) {
				case "user" -> userInput();
				case "computer" -> computerInput();
			}
		} while(!isGameOver(ticTacToeTable));
	}

	private static void computerInput() {
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

	private static void userInput() {
		int yCoordinate;
		int xCoordinate;
		while (true) {
			try {
				System.out.print("Enter the coordinates: ");
				String coordinates = scanner.nextLine();
				xCoordinate = Integer.parseInt(coordinates.split(" ")[0]);
				yCoordinate = Integer.parseInt(coordinates.split(" ")[1]);

				if (checkRange(xCoordinate, yCoordinate)) {
					xCoordinate -= 1;
					yCoordinate -= 1;

					if (isOccupied(ticTacToeTable, yCoordinate, xCoordinate)) {
						System.out.println("This cell is occupied! Choose another one!");
					} else {
						ticTacToeTable[xCoordinate][yCoordinate] = turn() ? 'X': 'O';
						printTable(ticTacToeTable);
						break;
					}

				} else {
					System.out.println("Coordinates should be from 1 to 3!");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("You should enter numbers!");
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				System.out.println("Please give two numbers!");
			}
		}
	}
}
