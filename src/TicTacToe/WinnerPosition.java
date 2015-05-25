package TicTacToe;
public enum WinnerPosition {
	/**
	 *  1 | 2 | 3
	 *  ---------
	 *  4 | 5 | 6
	 *  ---------
	 *  7 | 8 | 9
	 */
	HORIZONTAL_SUPERIOR(7), HORIZONTAL_MEDIA(56), HORIZONTAL_INFERIOR(448), VERTICAL_IZQUIERDA(
			73), VERTICAL_MEDIA(146), VERTICAL_DERECHA(292), DIAGONAL_NEGATIVA(
			273), DIAGONAL_POSITIVA(84);

	private final int valor;

	WinnerPosition(int valor) {
		this.valor = valor;
	}

	int getValue() {
		return this.valor;
	}

}
