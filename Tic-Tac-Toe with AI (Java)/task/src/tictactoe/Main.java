package tictactoe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] TicTacToeCell = new char[3][3];

		System.out.print("Enter the cells: ");
		String input = scanner.next().replaceAll("_", " ");
		scanner.nextLine();

		int counter = 0;
		for (int i = 0; i < TicTacToeCell.length; i++) {
			for (int j = 0; j < TicTacToeCell[i].length; j++, counter++) {
				TicTacToeCell[i][j] = input.charAt(counter);
			}
		}
		printTable(TicTacToeCell);

		int XCoordinate;
		int YCoordinate;
		while(true) {
			try {
				System.out.print("Enter the coordinates: ");
				String coordinates = scanner.nextLine();
				XCoordinate = Integer.parseInt(coordinates.split(" ")[0]);
				YCoordinate = Integer.parseInt(coordinates.split(" ")[1]);

				if (checkRange(XCoordinate, YCoordinate)) {
					XCoordinate -= 1;
					YCoordinate -= 1;

					if (TicTacToeCell[XCoordinate][YCoordinate] != ' ') {
						System.out.println("This cell is occupied! Choose another one!");
					} else {
						break;
					}
				} else {
					System.out.println("Coordinates should be from 1 to 3!");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("You should enter numbers!");
			} catch (ArrayIndexOutOfBoundsException aioobe){
				System.out.println("Please give two numbers!");
			}
		}

		boolean turn = turn(TicTacToeCell);
		if (turn) {
			TicTacToeCell[XCoordinate][YCoordinate] = 'X';
		} else {
			TicTacToeCell[XCoordinate][YCoordinate] = 'O';
		}

		printTable(TicTacToeCell);
		printOutcome(TicTacToeCell, turn);
	}

	private static void printTable(char[][] TicTacToe) {
		System.out.println("---------");
		for (char[] firstArray: TicTacToe) {
			System.out.print("|");
			for (char secondArray: firstArray) {
				System.out.print(" " + secondArray);
			}
			System.out.println(" |");
		}
		System.out.println("---------");
	}

	private static boolean checkRange(int x, int y) {
		return (1 <= x && x <= 3) && (1 <= y && y <= 3);
	}

	private static boolean turn(char[][] TicTacToe) {
		int counter = 0;

		for (char[] x: TicTacToe) {
			for (char y: x) {
				if (y == 'X' || y == 'O') {
					counter++;
				}
			}
		}

		return counter % 2 == 0;
	}

	private static void printOutcome(char[][] TicTacToe, boolean turn) {
		String player = turn ? "X": "O";

		// Checking horizontal and vertical
		for (int i = 0; i < TicTacToe.length; i++) {
			if (TicTacToe[i][0] != ' ') {
				if (TicTacToe[i][0] == TicTacToe[i][1] && TicTacToe[i][0] == TicTacToe[i][2]) {
					System.out.printf("%s wins\n", player);
					return;
				} else if (TicTacToe[0][i] == TicTacToe[1][i] && TicTacToe[0][i] == TicTacToe[2][i]) {
					System.out.printf("%s wins\n", player);
					return;
				}
			}
		}

		// Checking diagonal
		if (TicTacToe[0][0] == TicTacToe[1][1] && TicTacToe[0][0] == TicTacToe[2][2]) {
			System.out.printf("%s wins\n", player);
			return;
		}

		if (TicTacToe[0][2] == TicTacToe[1][1] && TicTacToe[0][2] == TicTacToe[2][0]) {
			System.out.printf("%s wins\n", player);
			return;
		}

		// Checking whether the game is a draw or not finished
		for (char[] x: TicTacToe) {
			for (char y: x) {
				if (y == ' ') {
					System.out.println("Game not finished");
					return;
				}
			}
		}

		System.out.println("Draw");
	}
}
