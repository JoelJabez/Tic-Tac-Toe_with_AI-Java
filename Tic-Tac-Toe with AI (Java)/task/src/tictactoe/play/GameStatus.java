package tictactoe.play;

import static tictactoe.play.Mode.isOccupied;

class GameStatus {
	private static char[][] ticTacToe = new char[3][3];

	private static void setTicTacToe(char[][] table) {
		ticTacToe = table;
	}

	static boolean turn() {
		int counter = 0;

		for (char[] x: ticTacToe) {
			for (char y: x) {
				if (y == 'X' || y == 'O') {
					counter++;
				}
			}
		}

		return counter % 2 == 0;
	}

	static boolean isGameOver(char[][] ticTacToe) {
		setTicTacToe(ticTacToe);
		String player = turn() ? "O": "X";
		final int zero = 0;

		if (hasWinner(player)) {
			return true;
		}

		int counter = zero;
		for (char[] x: ticTacToe) {
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
		if (ticTacToe[0][0] == ticTacToe[1][1] && ticTacToe[0][0] == ticTacToe[2][2] && isOccupied(ticTacToe, 0, 0)) {
			printWinner(player);
			return true;
		}

		if (ticTacToe[0][2] == ticTacToe[1][1] && ticTacToe[0][2] == ticTacToe[2][0] && isOccupied(ticTacToe, 2, 0)) {
			printWinner(player);
			return true;
		}
		return false;
	}

	private static boolean checkHorizontalAndVertical(String player) {
		for (int i = 0; i < ticTacToe.length; i++) {
			if (ticTacToe[i][0] == ticTacToe[i][1] && ticTacToe[i][0] == ticTacToe[i][2] && isOccupied(ticTacToe, 0, i)) {
				printWinner(player);
				return true;
			} else if (ticTacToe[0][i] == ticTacToe[1][i] && ticTacToe[0][i] == ticTacToe[2][i] && isOccupied(ticTacToe, i, 0)) {
				printWinner(player);
				return true;
			}
		}
		return false;
	}

	private static void printWinner(String player) {
		System.out.printf("%s wins\n\n", player);
	}
}
