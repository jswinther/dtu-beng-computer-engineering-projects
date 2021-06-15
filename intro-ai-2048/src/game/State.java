package game;

public class State {
	
	public State() {
		
	}
	
	public State(State state) {
		this.setBoard(state.getBoard());
		this.setMergeScore(state.getMergeScore());
	}
	
	private int[][] board = new int[4][4];
	private int mergeScore = 0;
	
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	public int getMergeScore() {
		return mergeScore;
	}
	public void setMergeScore(int mergeScore) {
		this.mergeScore = mergeScore;
	}
	

}
