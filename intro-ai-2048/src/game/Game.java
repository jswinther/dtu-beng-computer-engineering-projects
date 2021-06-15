package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enums.Move;

public class Game {
	
	
	public State setupGame(State state) { // Returns the default board layout.
		State boardCopy = new State();
		canSpawnTile(boardCopy);
		canSpawnTile(boardCopy);
		return boardCopy;
	}
	
	public State result(State state, Move move) { //	Returns a copy of the given state moved.
		State boardCopy = new State(state);
		if(move == null)
			return boardCopy;
		switch(move) {
		case UP:
			rotate(boardCopy, 4);
			move(boardCopy);
			rotate(boardCopy, 4);
			break;
		case DOWN:
			rotate(boardCopy, 2);
			move(boardCopy);
			rotate(boardCopy, 2);
			break;
		case LEFT:
			rotate(boardCopy, 1);
			move(boardCopy);
			rotate(boardCopy, 3);
			break;
		case RIGHT:
			rotate(boardCopy, 3);
			move(boardCopy);
			rotate(boardCopy, 1);
			break;
		default:
			break;	
		}
		return boardCopy;
	}
	
	// This function will move the pieces up, as well as merge the pieces that can be merged
	// In colaboration with the rotate function it can move to any direction.
	private void move(State state) {
		int nextTile;
//		System.out.println("Score at the start is: " + state.getMergeScore());
		// Setting i = 1, because the first row (i = 0) can't be moved up.
		for(int i = 1; i < state.getBoard().length; i++) 
		{
			for(int j = 0; j < state.getBoard().length; j++) 
			{
				// 0's can't be moved, so they are ignored.
				if(state.getBoard()[i][j] > 0) 
				{
					nextTile = i - 1;
					// Keep moving tiles until it hits wall or a tile with a value.
					while(nextTile >= 0 && state.getBoard()[nextTile][j] == 0) 
					{
						state.getBoard()[nextTile][j] = state.getBoard()[nextTile+1][j];
						state.getBoard()[nextTile+1][j] = 0;
						nextTile--;
					}

					// Merge tiles:
					if(nextTile >= 0)
					{
						if(state.getBoard()[nextTile][j] == state.getBoard()[nextTile+1][j])
						{
							state.getBoard()[nextTile][j] = state.getBoard()[nextTile][j] + state.getBoard()[nextTile+1][j];
							state.setMergeScore(state.getMergeScore() + state.getBoard()[nextTile][j]);
//							System.out.println("Merging tiles will give a score of: " + state.getMergeScore());
							state.getBoard()[nextTile+1][j] = 0;
						}
					}

					// Move again in case some of the merged tiles opened up holes.
					while(nextTile >= 0 && state.getBoard()[nextTile][j] == 0) 
					{
						state.getBoard()[nextTile][j] = state.getBoard()[nextTile+1][j];
						state.getBoard()[nextTile+1][j] = 0;
						nextTile--;
					}
				}
			}
		}
	}
	
	// Function that will rotate the entire board to fit with the move function.
	public void rotate(State state, int numTimes) 
	{
		for (int i = 0; i < numTimes; i++) 
		{
			int[][] nextState = new int[state.getBoard().length][state.getBoard().length];
			for(int j = 0; j < state.getBoard().length; j++) 
			{
				for(int k = 0; k < state.getBoard().length; k++) 
				{
					nextState[j][k]=state.getBoard()[state.getBoard().length-k-1][j];
				}
			}
			state.setBoard(nextState);
		}
	}
	
	public void printState(State state, Move move) {
		System.out.println("\n-----------------------------");
		for (int i = 0; i < state.getBoard().length; i++) {
			for (int j = 0; j < state.getBoard().length; j++) {
				System.out.print(state.getBoard()[i][j] + "\t");
			}
			System.out.println("\n");
		}
		if(move != null)
			System.out.println("--------- MOVED " + move.toString() + " ---------\n");
	}
	
	public boolean canSpawnTile(State state) {
		State stateCopy = new State(state);
		Random rand = new Random();
		int x = rand.nextInt(4);
		int y = rand.nextInt(4);
		int numberOfFilledTimes = 0;

		// Checks for terminal state.
		for (int i = 0; i < stateCopy.getBoard().length; i++) {
			for (int j = 0; j < stateCopy.getBoard().length; j++) {
				if(stateCopy.getBoard()[i][j] > 0)
					numberOfFilledTimes++;
			}
		}

		if(numberOfFilledTimes == 16) {
			return false;
		} else {
			while(state.getBoard()[x][y] > 0) {
				x = rand.nextInt(4);
				y = rand.nextInt(4);
			}
			int chanceFor4 = rand.nextInt(10);
			if(chanceFor4 > 0)
				state.getBoard()[x][y] = 2;
			else
				state.getBoard()[x][y] = 4;
		}
		return true;
	}
	
	public boolean isGoal(State state) {
		for (int i = 0; i < state.getBoard().length; i++) {
			for (int j = 0; j < state.getBoard().length; j++) {
				if(state.getBoard()[i][j] == 2048)
					return true;
			}
		}
		return false;
	}

	public List<Move> action(State state) {
		List<Move> actions = new ArrayList<>();	
		
		State s1 = new State(result(state, Move.UP));
		State s2 = new State(result(state, Move.DOWN));
		State s3 = new State(result(state, Move.LEFT));
		State s4 = new State(result(state, Move.RIGHT));
		
		if(!compareBoards(s1.getBoard(), state.getBoard()))
			actions.add(Move.UP);
		if(!compareBoards(s2.getBoard(), state.getBoard()))
			actions.add(Move.DOWN);
		if(!compareBoards(s3.getBoard(), state.getBoard()))
			actions.add(Move.LEFT);
		if(!compareBoards(s4.getBoard(), state.getBoard()))
			actions.add(Move.RIGHT);
		
		return actions;
	}

	private boolean compareBoards(int[][] board, int[][] board2) {
		for (int i = 0; i < board2.length; i++) {
			for (int j = 0; j < board2.length; j++) {
				if(board[i][j] != board2[i][j])
					return false;
			}
		}
		return true;
	}
}
