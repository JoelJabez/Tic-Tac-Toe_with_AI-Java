package tictactoe.play;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Mode {

	Random random = new Random();
	char[][] ticTacToeTable = new char[3][3];
	private String player1 = "";
	private String player2 = "";

	Mode() {}

	Mode(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	String getPlayer1() {
		return player1;
	}

	String getPlayer2() {
		return player2;
	}

	void determinePlayer(String player) {
		if (player.equals("user")) {
			userInput();
		} else {
			computerInput();
		}
	}

	void createEmptyBoard() {
		for (char[] chars: ticTacToeTable) {
			Arrays.fill(chars, ' ');
		}
	}

	private void userInput() {
		Scanner scanner = new Scanner(System.in);
		GameStatus gameStatus = new GameStatus();
		gameStatus.setTicTacToe(ticTacToeTable);

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

					if (isOccupied(ticTacToeTable, xCoordinate, yCoordinate)) {
						System.out.println("This cell is occupied! Choose another one!");
					} else {
						ticTacToeTable[xCoordinate][yCoordinate] = gameStatus.turn() ? 'X': 'O';
						gameStatus.setTicTacToe(ticTacToeTable);
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

	void computerInput() {}

	void battleAgainst() {
		GameStatus gameStatus = new GameStatus();
		createEmptyBoard();
		printTable(ticTacToeTable);
		do {
			determinePlayer(getPlayer1());
			if (gameStatus.isGameOver(ticTacToeTable)) {
				return;
			}

			determinePlayer(getPlayer2());
		} while(!gameStatus.isGameOver(ticTacToeTable));
	}

	boolean isOccupied(char[][] ticTacToe, int xCoordinate, int yCoordinate) {
		return ticTacToe[xCoordinate][yCoordinate] != ' ';
	}

	void printTable(char[][] ticTacToe) {
		System.out.println("---------");
		for (char[] firstArray: ticTacToe) {
			System.out.print("|");
			for (char secondArray: firstArray) {
				System.out.print(" " + secondArray);
			}
			System.out.println(" |");
		}
		System.out.println("---------");
	}

	boolean checkRange(int x, int y) {
		return (1 <= x && x <= 3) && (1 <= y && y <= 3);
	}
}
