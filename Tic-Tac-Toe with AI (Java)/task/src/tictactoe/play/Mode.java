package tictactoe.play;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Mode {

	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();
	static char[][] ticTacToeTable = new char[3][3];

	static void createEmptyBoard() {
		for (char[] chars: ticTacToeTable) {
			Arrays.fill(chars, ' ');
		}
	}

	static boolean isOccupied(char[][] ticTacToe, int yCoordinate, int xCoordinate) {
		return ticTacToe[xCoordinate][yCoordinate] != ' ';
	}

	static void printTable(char[][] ticTacToe) {
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

	static boolean checkRange(int x, int y) {
		return (1 <= x && x <= 3) && (1 <= y && y <= 3);
	}
}
