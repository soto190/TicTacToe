package TicTacToe;
import java.util.Comparator;

public class Politic implements Comparator<Politic> {

	int id;
	double value;
	Action action;
	State state;
	double e = 0;

	static int totalPolitics;

	public Politic(State est, Action act) {
		this.id = Politic.totalPolitics++;
		this.state = est;
		this.action = act;
		this.value = -1;
	}

	public Politic(int id, State state, Action action, double value, double e) {
		Politic.totalPolitics++;
		this.id = id;
		this.state = state;
		this.action = action;
		this.value = value;
		this.e = e;
	}

	public int getId() {
		return id;
	}

	public Action getAction() {
		return action;
	}

	public State getEstate() {
		return state;
	}

	public double getValue() {
		return value;
	}

	public double getE() {
		return e;
	}

	public void setE(double newE) {
		this.e = newE;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void increaseE(int value) {
		e += value;
	}

	@Override
	public String toString() {

		return "[Id: " + this.id + " Est: " + this.state + " Act: " + this.action
				+ " Val: " + this.value + " e: " + this.e + " ]";
	}

	@Override
	public int compare(Politic arg0, Politic arg1) {

		if (arg0.getValue() == arg1.getValue())
			return 0;
		else if (arg0.getValue() < arg1.getValue())
			return -1;
		else if (arg0.getValue() > arg1.getValue())
			return 1;

		return 0;
	}

	public boolean equals(Politic pol) {
		if (this.getEstate() == pol.getEstate()
				&& this.getAction() == pol.getAction())
			return true;
		else
			return false;
	}

}
