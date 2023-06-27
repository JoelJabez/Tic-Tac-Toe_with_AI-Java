package tictactoe.play;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	private TicTacToe() {}

	public static void start() {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		while (true) {
			System.out.print("Input command: ");
			String input = scanner.nextLine();
			String status = input.split(" ")[0];

			switch (status) {
				case "start" -> {
					try {
						String mode = input.split(" ")[1];
						String against = input.split(" ")[2];
						switch (mode) {
							case "easy" -> {
								switch (against) {
									case "easy" -> EasyMode.battleAgainst("computer", "computer");
									case "user" -> EasyMode.battleAgainst("computer", "user");
								}
							}

							case "user" -> {
								switch (against) {
									case "easy" -> EasyMode.battleAgainst("user", "computer");
									case "user" -> EasyMode.battleAgainst("user", "user");
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
