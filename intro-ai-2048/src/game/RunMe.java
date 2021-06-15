package game;

import java.util.concurrent.TimeUnit;

import enums.Move;

public class RunMe {
	public static void main(String[] args) throws InterruptedException {
		int winCounter = 0, counter = 0;
		while(winCounter != 100)
		{
			State state = new State();
			Move move = null;
			Game game = new Game();
			AI ai = new AI(game);
			while(!game.isGoal(state))
			{
				counter++;
				state = game.setupGame(state);
				move = null;
				while(!game.isGoal(state)) 
				{
					move = ai.findBestMove(state, 6);
					state = game.result(state, move);
					// Game ends if a tile can't be spawned
					if(!game.canSpawnTile(state)) {
						break;
					}
					// Uncomment the two lines below to see the movements.
					// it should be noted that it takes between 900 - 1000 moves 
					// so a game takes 5 minutes to watch.
					
					game.printState(state, move);	
					TimeUnit.MILLISECONDS.sleep(300);

				}
			}
			winCounter++;
			System.out.println("Number of wins: " + winCounter);
			System.out.println("Tries: " + counter);
			game.printState(state, move);
		}
		System.out.println("Average win rate is: " + (double)counter/(double)winCounter);
	}
}