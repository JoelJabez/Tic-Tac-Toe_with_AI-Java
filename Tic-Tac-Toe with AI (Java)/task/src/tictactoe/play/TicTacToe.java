package tictactoe.play;

import java.util.Scanner;

public class TicTacToe {

	private TicTacToe() {}

	public static void start() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Input command: ");
			String input = scanner.nextLine();
			String status = input.split(" ")[0];

			switch (status) {
				case "start" -> {
					try {
						String mode = input.split(" ")[1];
						String against = input.split(" ")[2];
						Mode levelOfDifficulty;

						switch (mode) {
							case "easy" -> {
								switch (against) {
									case "easy" -> {
										levelOfDifficulty = new EasyMode("computer", "computer");
										levelOfDifficulty.battleAgainst();
									}
									case "user" -> {
										levelOfDifficulty = new EasyMode("computer", "user");
										levelOfDifficulty.battleAgainst();
									}
								}
							}

							case "user" -> {
								switch (against) {
									case "easy" -> {
										levelOfDifficulty = new EasyMode("user", "computer");
										levelOfDifficulty.battleAgainst();
									}
									case "medium" -> {
										levelOfDifficulty = new MediumMode("user", "computer");
										levelOfDifficulty.battleAgainst();
									}
									case "user" -> {
										levelOfDifficulty = new Mode("user", "user");
										levelOfDifficulty.battleAgainst();
									}
								}
							}
						}
					} catch (ArrayIndexOutOfBoundsException aioobe) {
						System.out.println("Bad parameters!");
					}
				}

				case "exit" -> {
					return;
				}

				default -> System.out.println("Invalid input");
			}

		}
	}


}
