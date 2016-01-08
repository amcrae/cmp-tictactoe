package amcrae.research.frameworkcmp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** Most of the game logic and rules for Tic-Tac-Toe will be implemented here. */
public class TicTacToeGame implements Game {
	
	//TODO: Need a general unique id generator service in the system.
	private int gameId;
	
	@Override
	public int getGameId() {
		return gameId;
	}

	private GameType gameType;
	
	@Override
	public GameType getGameType() {
		return gameType;
	}

	List<TTTPlayer> currentPlayers = new ArrayList<TTTPlayer>(4);
	
	@Override
	public Player[] getPlayers() {
		Player[] a = new Player[currentPlayers.size()];
		return currentPlayers.toArray(a);
	}

	@Override
	public void playerJoins(Player p) {
		//TODO: solve the Player --> TTTPlayer transition problem. Does a ChatPlayer create a TTTPlayer prior to joining?
		try {
			TTTPlayer tp = (TTTPlayer)p; 
			if (currentPlayers.contains(tp)) throw new IllegalArgumentException();
			if (!currentPlayers.add(tp)) throw new IllegalStateException();
		} catch (ClassCastException cce) {
			throw new IllegalArgumentException("Player was not a TTTPlayer");
		}
	}

	@Override
	public void playerLeaves(Player p) {
		if (!currentPlayers.contains(p)) throw new IllegalArgumentException();
		if (!currentPlayers.remove(p)) throw new IllegalStateException();
	}

	private GameState gameState = GameState.PREREQUISITES;
	
	@Override
	public GameState getState() {
		return gameState;
	}

	private List<TTTMove> history = new LinkedList<TTTMove>();
	
	public TTTMove[] getHistory(long after_timestamp) {
		//TODO: decide if SortedSet would be better, or some other temporal index used for efficiently returning a time range.
		throw new NotImplementedException();
	}
	
}
