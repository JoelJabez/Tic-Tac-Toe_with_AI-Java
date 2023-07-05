package tictactoe.play;

public class MediumMode extends Mode {
	MediumMode(String player1, String player2) {
		super(player1, player2);
	}

	@Override
	void computerInput() {
		GameStatus gameStatus = new GameStatus();
		gameStatus.setTicTacToe(ticTacToeTable);
		char turn = gameStatus.turn() ? 'X': 'O';

		int[] coordinate = new int[2];
		int xCoordinate;
		int yCoordinate;
		System.out.println("Making move level \"medium\"");

		if (isAttackOrDefend(turn, coordinate)) {
			xCoordinate = coordinate[0];
			yCoordinate = coordinate[1];
		} else {
			do {
				xCoordinate = random.nextInt(3);
				yCoordinate = random.nextInt(3);
			} while (isOccupied(ticTacToeTable, xCoordinate, yCoordinate));
		}
		ticTacToeTable[xCoordinate][yCoordinate] = turn;
		printTable(ticTacToeTable);
	}

	private boolean isAttackOrDefend(char turn, int[] coordinates) {
		for (int i = 0; i < LENGTH; i++) {
			if (checkHorizontalAndVertical(turn, coordinates, i)) {
				return true;
			}
		}
		return checkDiagonals(turn, coordinates);
	}

	private boolean checkDiagonals(char turn, int[] coordinates) {
		if (isLeftDiagonal(coordinates, turn) && isEmpty(coordinates[0], coordinates[1])) {
			return true;
		}
		return isRightDiagonal(coordinates, turn) && isEmpty(coordinates[0], coordinates[1]);
	}

	private boolean checkHorizontalAndVertical(char turn, int[] coordinates, int i) {
		if (isHorizontal(coordinates, i, turn) && isEmpty(coordinates[0], coordinates[1])) {
			return true;
		}

		return isVertical(coordinates, i, turn) && isEmpty(coordinates[0], coordinates[1]);
	}

	private boolean isHorizontal(int[] coordinates, int i, char turn) {
		int oCounter = 0;
		int xCounter = 0;

		for (int j = 0; j < LENGTH; j++) {
			int[] counter = coordinateToPlace(i, j, xCounter, oCounter, coordinates);
			xCounter = counter[0];
			oCounter = counter[1];
		}

		return attackOrDefend(xCounter, oCounter, turn);
	}

	private boolean isVertical(int[] coordinates, int i, char turn) {
		int xCounter = 0;
		int oCounter = 0;
		for (int j = 0; j < LENGTH; j++) {
			int[] counter = coordinateToPlace(j, i, xCounter, oCounter, coordinates);
			xCounter = counter[0];
			oCounter = counter[1];
		}

		return attackOrDefend(xCounter, oCounter, turn);
	}

	private boolean isLeftDiagonal(int[] coordinates, char turn) {
		int xCounter = 0;
		int oCounter = 0;
		for (int i = 0; i < LENGTH; i++) {
			int[] counter = coordinateToPlace(i, i, xCounter, oCounter, coordinates);
			xCounter = counter[0];
			oCounter = counter[1];
		}

		return attackOrDefend(xCounter, oCounter, turn);
	}

	private boolean isRightDiagonal(int[] coordinates, char turn) {
		int xCounter = 0;
		int oCounter = 0;
		int j = LENGTH - 1;

		for (int i = 0; i < LENGTH; i++, j--) {
			int[] counter = coordinateToPlace(i, j, xCounter, oCounter, coordinates);
			xCounter = counter[0];
			oCounter = counter[1];
		}

		return attackOrDefend(xCounter, oCounter, turn);
	}

	private int[] coordinateToPlace(int x, int y, int xCounter, int oCounter, int[] coordinates) {
		int[] counter = new int[2];
		switch (ticTacToeTable[x][y]) {
			case 'X' -> xCounter++;
			case 'O' -> oCounter++;
			default -> {
				coordinates[0] = x;
				coordinates[1] = y;
			}
		}

		counter[0] = xCounter;
		counter[1] = oCounter;
		return counter;
	}

	private boolean attackOrDefend(int xCounter, int oCounter, char turn)  {
		if (xCounter + oCounter != 3) {
			if (xCounter == 2 && turn == 'X' || oCounter == 2 && turn == 'O') {
				return true;
			} else return xCounter == 2 && turn == 'O' || oCounter == 2 && turn == 'X';
		}
		return false;
	}

	boolean isEmpty(int x, int y) {
		return ticTacToeTable[x][y] == ' ';
	}
}
