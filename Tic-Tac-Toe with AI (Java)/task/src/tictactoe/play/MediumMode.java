package tictactoe.play;

public class MediumMode extends Mode {
	MediumMode(String player1, String player2) {
		super(player1, player2);
	}

	@Override
	void computerInput() {
		GameStatus gameStatus = new GameStatus();
		char turn = gameStatus.turn() ? 'O': 'X';

		int[] coordinate = new int[2];
		int xCoordinate;
		int yCoordinate;
		System.out.println("Making move level \"medium\"");
		System.out.println(isAttackOrDefend(turn, coordinate));
		System.out.printf("%d %d\n", coordinate[0], coordinate[1]);

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

		for (int i = 0; i < ticTacToeTable.length; i++) {

			if (isHorizontal(coordinates, i, turn) && !isOccupied(ticTacToeTable, coordinates[0], coordinates[1])) {
				return true;
			}

			if (isVertical(coordinates, i, turn) && !isOccupied(ticTacToeTable, coordinates[0], coordinates[1])) {
				return true;
			}
		}

		if (isLeftDiagonal(coordinates, turn) && !isOccupied(ticTacToeTable, coordinates[0], coordinates[1])) {
			return true;
		}
		return isRightDiagonal(coordinates, turn) && !isOccupied(ticTacToeTable, coordinates[0], coordinates[1]);
	}

	private boolean isHorizontal(int[] coordinates, int i, char turn) {
		int oCounter = 0;
		int xCounter = 0;

		System.out.println("isHorizontal method");
		for (int j = 0; j < ticTacToeTable.length; j++) {
			int[] coordinatesAndCounter = coordinatesAndCounter(i, j, xCounter, oCounter);
			coordinates[0] = coordinatesAndCounter[0];
			coordinates[1] = coordinatesAndCounter[1];

			xCounter = coordinatesAndCounter[2];
			oCounter = coordinatesAndCounter[3];
		}

		System.out.println();
		return attackOrDefend(xCounter, oCounter, turn);
	}

	private boolean isVertical(int[] coordinates, int i, char turn) {
		int xCounter = 0;
		int oCounter = 0;
		for (int j = 0; j < ticTacToeTable.length; j++) {
			int[] coordinatesAndCounter = coordinatesAndCounter(j, i, xCounter, oCounter);
			coordinates[0] = coordinatesAndCounter[0];
			coordinates[1] = coordinatesAndCounter[1];

			xCounter = coordinatesAndCounter[2];
			oCounter = coordinatesAndCounter[3];
		}

		return attackOrDefend(xCounter, oCounter, turn);
	}

	private boolean isLeftDiagonal(int[] coordinates, char turn) {
		int xCounter = 0;
		int oCounter = 0;
		for (int i = 0; i < ticTacToeTable.length; i++) {
			int[] coordinatesAndCounter = coordinatesAndCounter(i, i, xCounter, oCounter);
			coordinates[0] = coordinatesAndCounter[0];
			coordinates[1] = coordinatesAndCounter[1];

			xCounter = coordinatesAndCounter[2];
			oCounter = coordinatesAndCounter[3];
		}

		return attackOrDefend(xCounter, oCounter, turn);
	}

	private boolean isRightDiagonal(int[] coordinates, char turn) {
		int xCounter = 0;
		int oCounter = 0;
		int j = ticTacToeTable.length - 1;

		System.out.println("\nisRightDiagonal method: ");
		for (int i = 0; i < ticTacToeTable.length; i++, j--) {
			int[] coordinatesAndCounter = coordinatesAndCounter(i, j, xCounter, oCounter);
			coordinates[0] = coordinatesAndCounter[0];
			coordinates[1] = coordinatesAndCounter[1];

			xCounter = coordinatesAndCounter[2];
			oCounter = coordinatesAndCounter[3];

			System.out.println("x coordinate: " + coordinates[0]);
			System.out.println("y coordinate: " + coordinates[1]);

			System.out.println("x counter: " + xCounter);
			System.out.println("y counter: " + oCounter);
		}

		System.out.println();
		return attackOrDefend(xCounter, oCounter, turn);
	}

	private int[] coordinatesAndCounter(int x, int y, int xCounter, int oCounter) {
		int[] coordinatesAndCounter = new int[4];
		switch (ticTacToeTable[x][y]) {
			case 'X' -> xCounter++;
			case 'O' -> oCounter++;
			default -> {
				coordinatesAndCounter[0] = x;
				coordinatesAndCounter[1] = y;
			}
		}

		coordinatesAndCounter[2] = xCounter;
		coordinatesAndCounter[3] = oCounter;
		return coordinatesAndCounter;
	}

	private boolean attackOrDefend(int xCounter, int oCounter, char turn)  {
		if (xCounter + oCounter != 3) {
			if (xCounter == 2 && turn == 'X' || oCounter == 2 && turn == 'O') {
				return true;
			} else return xCounter == 2 && turn == 'O' || oCounter == 2 && turn == 'X';
		}
		return false;
	}
}
