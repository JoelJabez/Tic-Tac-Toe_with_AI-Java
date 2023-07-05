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

						if (mode.equals(against)) {
							switch (mode) {
								case "easy" -> {
									levelOfDifficulty = new EasyMode(mode, against);
									levelOfDifficulty.battleAgainst();
								}

								case "medium" -> {
									levelOfDifficulty = new MediumMode(mode, against);
									levelOfDifficulty.battleAgainst();
								}

								case "hard" -> {
									levelOfDifficulty = new HardMode(mode, against);
									levelOfDifficulty.battleAgainst();
								}

								case "user" -> {
									levelOfDifficulty = new Mode(mode, against);
									levelOfDifficulty.battleAgainst();
								}

								default -> System.out.println("Bad parameters");
							}
						}

						else {
							switch (mode) {
								case "easy" -> {
									if (against.equals("user")) {
										levelOfDifficulty = new EasyMode("computer", "user");
										levelOfDifficulty.battleAgainst();
									} else {
										System.out.println("Bad parameters");
									}
								}

								case "medium" -> {
									if (against.equals("user")) {
										levelOfDifficulty = new MediumMode("computer", "user");
										levelOfDifficulty.battleAgainst();
									} else {
										System.out.println("Bad parameters");
									}
								}

								case "hard" -> {
									if (against.equals("user")) {
										levelOfDifficulty = new HardMode("computer", "user");
										levelOfDifficulty.battleAgainst();
									} else {
										System.out.println("Bad parameters");
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

										case "hard" -> {
											levelOfDifficulty = new HardMode("user", "computer");
											levelOfDifficulty.battleAgainst();
										}

										default -> System.out.println("Bad parameters");
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
