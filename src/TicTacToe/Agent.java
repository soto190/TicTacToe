package TicTacToe;

import java.util.Random;

public class Agent {
	Random rnd = new Random();
	int id = 0;
	int movements = 0;

	int totalGamesWons = 0;
	int totalGameLose = 0;
	int totalGameDraw = 0;
	int totalPlayedGames = 0;
	int winsInARow = 0;
	int maxWinsInARow = 0;

	public Agent(int id) {
		this.id = id;
	}

	public void move(Enviroment env) {
	}

	public int getMovements() {
		return movements;
	}

	public void increasePlayedGames() {
		this.totalPlayedGames++;
	}

	public void increaseGamesWon() {
		this.totalGamesWons++;
	}

	public void increaseGamesDraw() {
		this.totalGameDraw++;
	}

	public void increaseGamesLost() {
		this.totalGameLose++;
	}
	
	public void increaseWinsInARow(){
		this.winsInARow++;
	}
	public void setWinsInARow(){
		this.maxWinsInARow++;
	}
	
	public void resetWinsInARow(){
		if(winsInARow > maxWinsInARow)
			maxWinsInARow = winsInARow;
		winsInARow = 0;
	}

	public void restart() {
		this.movements = 0;
	}
	
	public void addMovements(Action move){
		movements |= move.getValue();

	}
	
	public void readPolitics(){
		
	}

	
	public String toString() {
		return this.id + " " + this.getMovements() + " " +Integer.toBinaryString(getMovements());
	}
}
