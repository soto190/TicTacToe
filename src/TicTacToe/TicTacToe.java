package TicTacToe;
import java.util.Random;

public class TicTacToe {

	public Enviroment env = new Enviroment();
	boolean gameOver = false;

	AgentIntelligent player1 = new AgentIntelligent(1);
	AgentRandom player2 = new AgentRandom(2);

	Random rnd = new Random();

	public void start() {

		player1.readPolitics();
		player2.readPolitics();

		int i =0;
		while(i<= 500) {
			i++;
			System.out.println("Partida: " + i);
			player1.increasePlayedGames();
			player2.increasePlayedGames();
			double turno = rnd.nextDouble();

			while (!isGameOver()) {
				if (turno > 0.0) {
					player1.move(env);

					boolean ganoJ1 = isWinner(player1);

					if (ganoJ1) {
						player2.increaseGamesLost();
						player2.resetWinsInARow();
					} else if (!isGameOver())
						player2.move(env);

					boolean ganoJ2 = isWinner(player2);
					if (ganoJ2) {
						player1.increaseGamesLost();
						player1.resetWinsInARow();
					}
					if (isGameOver() && !(ganoJ1 || ganoJ2)) {
						player1.increaseGamesDraw();
						player2.increaseGamesDraw();
						player1.resetWinsInARow();
						player2.resetWinsInARow();
						System.out.println("Games " + i + " draw...");
					}
				} else {

					player2.move(env);

					boolean ganoJ2 = isWinner(player2);

					if (ganoJ2) {
						player1.increaseGamesLost();
						player1.resetWinsInARow();
					} else if (!isGameOver())
						player1.move(env);

					boolean ganoJ1 = isWinner(player1);
					if (ganoJ1) {
						player2.increaseGamesLost();
						player2.resetWinsInARow();
					}
					if (isGameOver() && !(ganoJ1 || ganoJ2)) {
						player2.increaseGamesDraw();
						player1.increaseGamesDraw();
						player2.resetWinsInARow();
						player1.resetWinsInARow();
						System.out.println("Game " + i + " draw...");
					}
				}
			}
			printBoard();
			restartGame();
		}

		player1.savePolitics();
		player1.resetWinsInARow();
		printResults(player1);
		System.out.println("");
		printResults(player2);

	}

	public void printResults(Agent jugador) {
		System.out.println("Player data" + jugador.id);
		System.out.println("Games totals: " + jugador.totalPlayedGames);
		System.out.println("Games won: " + jugador.totalGamesWons);
		System.out.println("Games draw: " + jugador.totalGameDraw);
		System.out.println("Games lost: " + jugador.totalGameLose);
		System.out.println("Maximum of wins in a row: "
				+ jugador.maxWinsInARow);

	}

	private void restartGame() {
		gameOver = false;
		player1.restart();
		player2.restart();
		env.restart();
	}

	private boolean isGameOver() {
		if (env.getEstado().getValue() == 511)
			return true;
		return gameOver;
	}

	private boolean isWinner(Agent jugador) {
		for (WinnerPosition pg : WinnerPosition.values())
			if ((jugador.getMovements() & pg.getValue()) == pg.getValue()) {
				gameOver = true;
				jugador.increaseGamesWon();
				jugador.increaseWinsInARow();
				System.out.println("Gano jugador: " + jugador.id);
				return true;
			}
		return false;

	}

	public void printBoard() {
		String s1 = Integer.toBinaryString(player1.getMovements());
		String s2 = Integer.toBinaryString(player2.getMovements());
		String tablero[] = new String[9];

		while (s1.length() < 9)
			s1 = "0" + s1;
		while (s2.length() < 9)
			s2 = "0" + s2;

		for (int i = tablero.length - 1; i >= 0; i--) {

			if (s1.charAt(i) == '1')
				tablero[tablero.length - i - 1] = "O";
			else if (s2.charAt(i) == '1')
				tablero[tablero.length - i - 1] = "X";
			else if (tablero[tablero.length - i - 1] == null)
				tablero[tablero.length - i - 1] = "-";
		}

		String tab = String.format(
				"%2s |%2s |%2s \n%2s |%2s |%2s \n%2s |%2s |%2s \n", tablero[0],
				tablero[1], tablero[2], tablero[3], tablero[4], tablero[5],
				tablero[6], tablero[7], tablero[8]);
		System.out.println(tab);
	}

	public static void main(String[] args) {
		TicTacToe tictactoe = new TicTacToe();

		tictactoe.start();

	}

}
