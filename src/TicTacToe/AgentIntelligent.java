package TicTacToe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class AgentIntelligent extends Agent {
	double epsilon = 0.01;
	double lambda = 0.9;
	double gamma = 1;
	double alfa = 0.1;

	Action currentAction;
	State currentState;

	Random rnd = new Random();
	LinkedList<Politic> politic = new LinkedList<Politic>();

	public AgentIntelligent(int id) {
		super(id);
	
		currentState = new State(0);
		currentAction = Action.getRandomAction(rnd.nextInt(9));
	}

	public Action selectAction(State estado) {

		Action action;
		Action a_qmax = Action.getAction(rnd.nextInt(9));

		for (Action act : Action.values())
			if (getValuePolitica(estado, act) > getValuePolitica(estado, a_qmax))
				a_qmax = act;
		action = a_qmax;

		if (rnd.nextDouble() < epsilon)
			action = Action.getAction(rnd.nextInt(9));

		return action;
	}

	public double getValuePolitica(State est, Action act) {

		for (Politic pol : this.politic)
			if (pol.getEstate().equals(est) && pol.getAction() == act)
				return pol.getValue();

		Politic newPol = new Politic(est, act);
		politic.add(newPol);
		return newPol.getValue();
	}

	protected int setValueOnPolitic(State state, Action act, int value) {

		for (Politic pol : this.politic)
			if (pol.getEstate().equals(state) && pol.getAction() == act) {
				pol.setValue(value);
				return 1;
			}
		return 0;

	}

	private void updatePolitic(int r, State currentState,
			Action currentAction, State nextState, Action nextAction) {
		double delta = r
				+ (gamma * getValuePolitica(nextState, nextAction))
				- getValuePolitica(currentState, currentAction);
		increaseTransitionValue(currentState, currentAction, 1);

		for (Politic pol : politic) {

			pol.setValue(pol.getValue() + (alfa * delta * pol.getE()));
			pol.setE(gamma * lambda * pol.getE());

			// if (pol.getEstado().equals(siguienteEstado)
			// && pol.getAction() == siguienteAccion)
			// if (r == 0)
			// pol.setValue(r);
			// else
			// pol.setValue(pol.getValue() + r);
		}
	}

	private int increaseTransitionValue(State currentState,
			Action currentAction, int val) {
		for (Politic pol : politic)
			if (pol.getEstate().equals(currentState)
					&& pol.getAction() == currentAction) {
				pol.increaseE(val);
				return 1;
			}
		return 0;
	}

	public void move(Enviroment env) {

		int r = 0;
		Action move;
		State state;
		State nextState;
		Action nextAction;

		do {

			move = selectAction(env.getEstado());

			state = new State(env.getEstado().getValue() | move.getValue());

			r = reward(move.getValue() | movements);

			nextAction = selectNextAction(env);
			nextState = new State(state.getValue()
					| nextAction.getValue());

			updatePolitic(r, state, move, nextState,
					nextAction);

		} while (!env.isValidAction(move));

		currentState = nextState;
		currentAction = nextAction;

		env.mark(move);
		addMovements(move);

	}

	public int reward(int val) {
		if (isWinner(val))
			return 0;
		else
			return -1;
	}

	public boolean isWinner(int val) {
		for (WinnerPosition pos : WinnerPosition.values())
			if ((pos.getValue() & val) == pos.getValue())
				return true;

		return false;
	}

	public Action selectNextAction(Enviroment env) {
		Action p_mejor, accion_sel;
		double r = rnd.nextDouble();
		if (epsilon > r)
			accion_sel = Action.getAction(rnd.nextInt(9));
		else {
			p_mejor = Action.MARCAR_CUADRO_1;

			for (Action act : Action.values())
				for (Politic pol : politic)
					if (pol.getEstate().equals(env.getEstado())
							&& pol.getAction() == act)
						if (pol.getValue() > getValuePolitica(env.getEstado(),
								p_mejor))
							p_mejor = act;

			accion_sel = p_mejor;
		}
		return accion_sel;
	}

	public void savePolitics() {
		String path = System.getProperty("user.dir") + File.separator
				+ "politica.dat";
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(path), false));

			for (Politic pol : politic)
				bw.write(String.format("%5d %3d %3d %f %f\n", pol.getId(), pol
						.getEstate().getValue(), pol.getAction().getValue(),
						pol.getValue(), pol.getE()));
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readPolitics() {
		String path = System.getProperty("user.dir") + File.separator
				+ "politica.dat";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(path)));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] data = line.trim().split("\\s+");
				Politic pol = new Politic(toInt(data[0]), new State(
						toInt(data[1])), Action.getThisAccion(toInt(data[2])),
						Double.parseDouble((data[3])),
						Double.parseDouble(data[4]));
				politic.add(pol);
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int toInt(String arg) {
		return Integer.parseInt(arg);
	}
}
