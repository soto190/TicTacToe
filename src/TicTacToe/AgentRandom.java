package TicTacToe;


public class AgentRandom extends Agent {
	
	public  AgentRandom(int id) {
		super(id);

	}
	
	public void move(Enviroment env) {


		Action move;
		do {
			move = Action.getRandomAction(rnd.nextInt(9));
		
		} while (!env.isValidAction(move));
		
		env.mark(move);
		addMovements(move);

	}
}
