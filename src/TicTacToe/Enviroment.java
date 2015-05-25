package TicTacToe;
/**
 * @autor José Carlos Soto Monterrubio
 * @category Ingeniero en Sistemas de Computación
 * 
 */
public class Enviroment {
	// private static int state = 0;
	private State estado = new State(0);

	public void setState(State state) {
		this.estado = state;
	}

	public boolean isValidAction(Action act) {
		if ((this.getEstado().getValue() | act.getValue()) != this.getEstado()
				.getValue())
			return true;
		else
			return false;

	}

	public void mark(Action act) {

		this.setState(new State(this.getEstado().getValue() | act.getValue()));

	}

	public State getEstado() {
		return this.estado;
	}

	public void restart() {
		this.estado = new State(0);
	}

	public String toString() {
		return this.estado.toString();
	}
}
