package tictactoe.play;

class GameStatus {
	private char[][] ticTacToe = new char[3][3];

	void setTicTacToe(char[][] table) {
		ticTacToe = table;
	}

	boolean turn() {
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

	boolean isGameOver(char[][] ticTacToe) {
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

	private boolean hasWinner(String player) {
		return checkDiagonal(player) || checkHorizontalAndVertical(player);
	}

	private boolean checkDiagonal(String player) {
		Mode mode = new Mode();
		if (ticTacToe[0][0] == ticTacToe[1][1] && ticTacToe[0][0] == ticTacToe[2][2] && mode.isOccupied(ticTacToe, 0, 0)) {
			printWinner(player);
			return true;
		}

		if (ticTacToe[0][2] == ticTacToe[1][1] && ticTacToe[0][2] == ticTacToe[2][0] && mode.isOccupied(ticTacToe, 0, 2)) {
			printWinner(player);
			return true;
		}
		return false;
	}

	private boolean checkHorizontalAndVertical(String player) {
		Mode mode = new Mode();
		for (int i = 0; i < ticTacToe.length; i++) {
			if (ticTacToe[i][0] == ticTacToe[i][1] && ticTacToe[i][0] == ticTacToe[i][2] && mode.isOccupied(ticTacToe, i, 0)) {
				printWinner(player);
				return true;
			} else if (ticTacToe[0][i] == ticTacToe[1][i] && ticTacToe[0][i] == ticTacToe[2][i] && mode.isOccupied(ticTacToe, 0, i)) {
				printWinner(player);
				return true;
			}
		}
		return false;
	}

	private void printWinner(String player) {
		System.out.printf("%s wins\n\n", player);
	}
}
