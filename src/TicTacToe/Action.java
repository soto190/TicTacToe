package TicTacToe;
public enum Action {
	MARCAR_CUADRO_1(1), MARCAR_CUADRO_2(2), MARCAR_CUADRO_3(4), MARCAR_CUADRO_4(
			8), MARCAR_CUADRO_5(16), MARCAR_CUADRO_6(32), MARCAR_CUADRO_7(64), MARCAR_CUADRO_8(
			128), MARCAR_CUADRO_9(256);

	private final int valor;

	Action(int valor) {
		this.valor = valor;
	}

	int getValue() {
		return this.valor;
	}
	
	public static Action getAction(int valor){
		return Action.values()[valor];
	}
	
	public static Action getRandomAction(int aleatorio){
		return getAction(aleatorio);
	}
	
	public static Action getThisAccion(int valor){
		for (Action act: Action.values()) {
			if(act.getValue() == valor)
				return act;
		}
		return null;
	}
}
