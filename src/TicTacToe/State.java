package TicTacToe;
import java.util.Comparator;

public class State implements Comparator<State> {

	int val = 0;
	int movOponent = 0;

	public State(int val) {
		this.val = val;
	}

	public int getValue() {
		return val;
	}

	@Override
	public int compare(State e1, State e2) {
		if (e1.getValue() == e2.getValue())
			return 0;
		else if (e1.getValue() < e2.getValue())
			return -1;
		else if (e1.getValue() > e2.getValue())
			return 1;
		return 0;
	}

	public boolean equals(State estado) {
		if (this.getValue() == estado.getValue())
			return true;
		else
			return false;
	}
	
	public String toString(){
		return this.val +" B: " + Integer.toBinaryString(this.val);
	}
}
