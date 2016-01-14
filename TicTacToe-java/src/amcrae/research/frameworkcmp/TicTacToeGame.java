package amcrae.research.frameworkcmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import amcrae.research.frameworkcmp.TicTacToeBoard.TTTPiece;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** Most of the game logic and rules for Tic-Tac-Toe will be implemented here. */
public class TicTacToeGame implements Game {
	/** The no-arg constructor will make a 2-player 3x3 Tic-Tac-Toe with no instructions. */
	public TicTacToeGame() {
		this.gameId = getNextId(); 
		GameType gt = new GameType();
		gt.setDimensions(new int[]{3,3} );
		gt.setName("TicTacToe 2P 3x3");
		gt.setGameTypeId(1);
		gt.setPlayersMax(2);
		gt.setPlayersMin(2);
		gt.setBriefInstruction("Place your piece on the board to get 3 in a row.");
		this.gameType = gt;
		this.gameState = GameState.PREREQUISITES;
	}
	
	//TODO: Need a general unique id generator service in the system.
	private int gameId;
	
	private static int nextid = 1;
	private static synchronized int getNextId() {
		return nextid++;
	}
	
	@Override
	public int getGameId() {
		return gameId;
	}

	private Date gameStarted;
	
	@Override
	public Date getGameStartTime() {
		return gameStarted;
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
			TTTPiece piece = getFreePiece();
			pieceAssignment.put(piece, tp);
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

	/** Show the list of moves which occurred after the given real world time, which returns all moves made in the game when the after_timestamp parameter is before the start of the game.
	 * */
	public TTTMove[] getHistory(Date after_timestamp) {
		//TODO: decide if SortedSet would be better, or some other temporal index used for efficiently returning a time range.
		throw new NotImplementedException();
	}
	
	/** TODO: return the player that the game is waiting for to make the current move.
	 * @return null when the game is over or is not possible to identify a player for the next move because the slot is empty. 
	 * */
	public TTTPlayer getCurrentMovePlayer() {
		throw new NotImplementedException();
	}
	
	/* Play simply rotates in player number order. */
	private TTTPlayer nextPlayer(TTTPlayer afterPlayer) {
		return currentPlayers.get(currentPlayers.indexOf(afterPlayer) % currentPlayers.size() + 1);
	}
	
	private Map<TTTPiece, TTTPlayer> pieceAssignment = new EnumMap<TicTacToeBoard.TTTPiece, TTTPlayer>(TicTacToeBoard.TTTPiece.class); 

	private TTTPiece getFreePiece() {
		ArrayList<TTTPiece> candidates = new ArrayList<>( Arrays.asList( TTTPiece.values() ) );
		candidates.removeAll(pieceAssignment.keySet());
		if (candidates.size()>0) return candidates.get(0);
		throw new IllegalStateException("No free pieces for player to use.");
	}
	
	/** Return the player that owns/plays the given board piece.*/
	public TTTPlayer getSymbolPlayer(TTTPiece symbol) {
		return pieceAssignment.get(symbol);
	}
	
	/** TODO: Return the current state of the board, which is basically information about where players have placed their pieces so far. 
	 * This should always be valid state even when the game has not started or the game is over. */
	public TicTacToeBoard getCurrentBoard() {
		throw new NotImplementedException();
	}

	/** TODO: Check if a proposed move is permitted and what Board state it will create if played. 
	 * Useful for validating a player's move or helping bots plan their next move. */
	public TTTResult check(TTTMove proposal) {
		throw new NotImplementedException();
	}
	
	/** TODO: A particular move is played or attempted to be played, which results in a TTTResult and possibly a new game state if the move is permitted. */
	public TTTResult playMove(TTTMove move) {
		throw new NotImplementedException();
	}
	
	/** TODO: Check if this is a final state where no further moves are possible, such as a win or a draw. */
	public boolean isFinalState(TicTacToeBoard boardState) {
		throw new NotImplementedException();
	}
}
