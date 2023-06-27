package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	private static char[][] ticTacToeTable = new char[3][3];
	public static void start() {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		for (char[] chars: ticTacToeTable) {
			Arrays.fill(chars, ' ');
		}

		printTable();
		do {
			int xCoordinate;
			int yCoordinate;
			// user play
			while (true) {
				try {
					System.out.print("Enter the coordinates: ");
					String coordinates = scanner.nextLine();
					xCoordinate = Integer.parseInt(coordinates.split(" ")[0]);
					yCoordinate = Integer.parseInt(coordinates.split(" ")[1]);

					if (checkRange(xCoordinate, yCoordinate)) {
						xCoordinate -= 1;
						yCoordinate -= 1;

						if (!isEmpty(xCoordinate, yCoordinate)) {
							System.out.println("This cell is occupied! Choose another one!");
						} else {
							ticTacToeTable[xCoordinate][yCoordinate] = 'X';
							printTable();
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

			if (isDone()) {
				return;
			}

			System.out.println("Making move level \"easy\"");
			do {
				xCoordinate = random.nextInt(3);
				yCoordinate = random.nextInt(3);
			} while (isEmpty(xCoordinate, yCoordinate));
			ticTacToeTable[xCoordinate][yCoordinate] = 'O';
			printTable();
		} while (!isDone());
	}

	private static boolean isEmpty(int xCoordinate, int yCoordinate) {
		return ticTacToeTable[xCoordinate][yCoordinate] == ' ';
	}

	private static void printTable() {
		System.out.println("---------");
		for (char[] firstArray: ticTacToeTable) {
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

	private static boolean turn() {
		int counter = 0;

		for (char[] x: ticTacToeTable) {
			for (char y: x) {
				if (y == 'X' || y == 'O') {
					counter++;
				}
			}
		}

		return counter % 2 == 0;
	}

	private static boolean isDone() {
		String player = turn() ? "O": "X";
		final int zero = 0;

		if (hasWinner(player)) {
			return true;
		}

		int counter = zero;
		for (char[] x: ticTacToeTable) {
			for (char y: x) {
				if (y != ' ') {
					counter++;
				}
			}
		}

		if (counter == 9) {
			System.out.println("Draw");
			return true;
		}

		return false;
	}

	private static boolean hasWinner(String player) {
		return checkDiagonal(player) || checkHorizontalAndVertical(player);
	}

	private static boolean checkDiagonal(String player) {
		if (ticTacToeTable[0][0] == ticTacToeTable[1][1] && ticTacToeTable[0][0] == ticTacToeTable[2][2] && !isEmpty(0, 0)) {
			System.out.printf("%s wins\n", player);
			return true;
		}

		if (ticTacToeTable[0][2] == ticTacToeTable[1][1] && ticTacToeTable[0][2] == ticTacToeTable[2][0] && !isEmpty(0, 2)) {
			System.out.printf("%s wins\n", player);
			return true;
		}
		return false;
	}

	private static boolean checkHorizontalAndVertical(String player) {
		for (int i = 0; i < ticTacToeTable.length; i++) {
			if (ticTacToeTable[i][0] == ticTacToeTable[i][1] && ticTacToeTable[i][0] == ticTacToeTable[i][2] && !isEmpty(i, 0)) {
				System.out.printf("%s wins\n", player);
				return true;
			} else if (ticTacToeTable[0][i] == ticTacToeTable[1][i] && ticTacToeTable[0][i] == ticTacToeTable[2][i] && !isEmpty(0, i)) {
				System.out.printf("%s wins\n", player);
				return true;
			}
		}
		return false;
	}
}
