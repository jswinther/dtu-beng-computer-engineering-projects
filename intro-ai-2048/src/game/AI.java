package game;

import java.lang.Math;
import enums.Move;

public class AI {
	
	private Game game;
	
	public AI(Game game) {
		this.game = game;
	}
	
	public Move findBestMove(State state, int depth) {
		State nextState = new State(state);
		double max = -100000000.0;
		Move move = null;
		for (Move m : game.action(state)) {
			double val = minimax(nextState, m, depth-1);
			if(max < val) {
				max = val;
				move = m;
			}
		}
		return move;
	}
	
	private double minimax(State state, Move move, int depth) {
		State movedState = new State(game.result(state, move));
		game.canSpawnTile(movedState);
		if(depth == 0) {
			return heuristicScore(movedState);
		} else {
			double max = -100000000.0;
			for (Move m : game.action(movedState)) {
				double val = minimax(new State(movedState), m, depth-1);
				if (max < val) 
					max = val;
			}
			return max;
		}
	}

	public double heuristicScore(State s) {
		return s.getMergeScore()*Math.pow(1.1, findEmptyTiles(s));
	}

	public int findEmptyTiles(State s) {
		int counter = 0;
		for(int i = 1; i < s.getBoard().length; i++) {
			for(int j = 0; j < s.getBoard().length; j++) {
				if(s.getBoard()[i][j] == 0)
					counter++;
			}
		}
		return counter;
	}
	

}
