package tictactoe.play;

public class HardMode extends Mode {

	HardMode(String player1, String player2) {
		super(player1, player2);
	}

	@Override
	void computerInput() {
		GameStatus gameStatus = new GameStatus();
		for (char[] chars : ticTacToeTable) {
			for (int j = 0; j < ticTacToeTable.length; j++) {
				if (chars[j] != 'X' && chars[j] != 'O') {
					chars[j] = gameStatus.turn() ? 'X': 'O';
				}
			}
		}
	}

	int minimax() {
		return 1;
	}
}
