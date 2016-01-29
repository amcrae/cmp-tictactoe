package amcrae.research.frameworkcmp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

import amcrae.research.frameworkcmp.TTTMove.MoveType;
import amcrae.research.frameworkcmp.TTTResult.MoveResult;
import amcrae.research.frameworkcmp.TicTacToeBoard.TTTPiece;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** Most of the game logic and rules for Tic-Tac-Toe will be implemented here. */
public class TicTacToeGame implements Game, Serializable, Cloneable {
	
	/** The no-arg constructor will make a 2-player 3x3 Tic-Tac-Toe with no instructions. */
	public TicTacToeGame() {
		this.gameId = getNextId(); 
		GameType gt = new GameType();
		gt.setDimensions(new int[]{3,3} );
		gt.setName("Tic-Tac-Toe 2P 3x3");
		gt.setGameTypeId(1);
		gt.setPlayersMax(2);
		gt.setPlayersMin(2);
		gt.setBriefInstruction("Place your piece on the board to get 3 in a row.");
		this.gameType = gt;
		this.gameState = GameState.PREREQUISITES;
		this.currentBoard = new TicTacToeBoard(gt.getDimensions()[0], gt.getDimensions()[1]);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/** Start a game with the given initial players, regardless of whether players are Human or Bots. */
	public static TicTacToeGame startDefaultMultiPlayerGame(TTTPlayer[] players) {
		TicTacToeGame game = new TicTacToeGame();
		for ( TTTPlayer p : players)
			game.playerJoins(p);
		return game;
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
		TTTPlayer tp = null; 
		try {
			tp = (TTTPlayer)p; 
		} catch (ClassCastException cce) {
			throw new IllegalArgumentException("Player was not a TTTPlayer");
		}
		if (currentPlayers.contains(tp)) throw new IllegalArgumentException();
		if (!currentPlayers.add(tp)) throw new IllegalStateException();
		TTTPiece piece = getFreePiece();
		if (piece==null) throw new MissingResourceException("No free game pieces for that player.", "TicTacToeGame pieceAssignment", "TTTPiece");
		pieceAssignment.put(piece, tp);
		try {
			tp.setPiece(this, piece);
		} catch (Exception ioe) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "TTTPlayer threw exception during setPiece : "+ ioe.getMessage() + "\n Ignoring exception and continuing game.", new Object[]{p} );
		}
		if (canStart()) startGame();
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

	private void startGame() {
		this.gameState = GameState.PLAY;
		if (this.gameStarted==null) this.gameStarted = new Date();
	}
	
	/** are all the pre-requisites for starting the game satisfied? */
	public boolean canStart() {
		return (
				this.currentPlayers.size()>= gameType.getPlayersMin() 
				&& this.currentPlayers.size()<=gameType.getPlayersMax()
				&& this.pieceAssignment.entrySet().size() == currentPlayers.size()
		);
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
	
	private List<TTTMove> history = new LinkedList<TTTMove>();

	/** Show the list of moves which occurred after the given real world time, which returns all moves made in the game when the after_timestamp parameter is before the start of the game.
	 * */
	public TTTMove[] getHistory(Date after_timestamp) {
		//TODO: decide if SortedSet would be better, or some other temporal index used for efficiently returning a time range.
		throw new NotImplementedException();
	}

	/** Return the player that owns/plays the given board piece.*/
	public TTTPlayer getSymbolPlayer(TTTPiece symbol) {
		return pieceAssignment.get(symbol);
	}
	
	TicTacToeBoard currentBoard;
	
	/** TODO: Return the current state of the board, which is basically information about where players have placed their pieces so far. 
	 * This should always be valid state even when the game has not started or the game is over. */
	public TicTacToeBoard getCurrentBoard() {
		return new TicTacToeBoard(currentBoard);
	}

	/** TODO: Check if a proposed move is permitted and what Board state it will create if played. 
	 * Useful for validating a player's move or helping bots plan their next move. */
	public TTTResult check(TicTacToeBoard state, TTTMove proposal) {
		TTTResult res  = new TTTResult();
		res.setLastMove(proposal);
		if(   proposal.getVerb()==null
		   || proposal.getVerb()==MoveType.PASS  /** May be needed internally as dummy/sentinel but no player is allowed to use it. */ 
 		) {
			res.setPermitted(false);
			res.setMoveResult(MoveResult.CONTINUE);
			res.setNewState(state);
		} else {
			switch (proposal.getVerb()) {
				case RESIGN: {
					res.setPermitted(true);
					res.setNewState(state);
					res.setMoveResult(MoveResult.LOSE);
				} break;
				
				case PLACE_PIECE: {
					if (  proposal.getPosition().getX()>=gameType.getDimensions()[0]
					   || proposal.getPosition().getY()>=gameType.getDimensions()[1]
					   || proposal.getPosition().getX()<0 
					   || proposal.getPosition().getY()<0		   
					   || (state.getSlot( proposal.getPosition()) != null)
					   || getSymbolPlayer(proposal.getObject()) != proposal.getSubject()
					) {
						res.setPermitted(false);
						res.setNewState(state);
						res.setMoveResult(MoveResult.CONTINUE);
					} else {
						res.setPermitted(true);
						TicTacToeBoard newstate = new TicTacToeBoard(state);
						newstate.setSlot(proposal.getPosition(),proposal.getObject());
						res.setNewState(newstate);
						res.setMoveResult( checkContinuation(newstate) );
					}
				} break;
				
				default: throw new IllegalArgumentException("Verb not allowed in current game state: "+proposal.getVerb().name() );
			}
		}
		return res;
	}
	
	/** TODO: Check if this is a final state where no further moves are possible, such as a win or a draw. */
	public MoveResult checkContinuation(TicTacToeBoard boardState) {
		throw new NotImplementedException();
	}
	
	/** TODO: A particular move is played or attempted to be played, which results in a TTTResult and possibly a new game state if the move is permitted. */
	public TTTResult playMove(TTTMove move) {
		throw new NotImplementedException();
	}
	
	@Override
	public boolean equals(Object obj) {
		TicTacToeGame o2 = (TicTacToeGame) obj;
		return o2.gameId == gameId;
	}
	
	@Override
	public int hashCode() {
		return gameId ^ 17;
	}
	
	@Override
	public String toString() {
		return "TicTacToe #" + this.gameId + "{" + gameType.toString() + ", State:"+ gameState.toString()+"}";
	}
}
