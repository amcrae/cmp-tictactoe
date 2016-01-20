package amcrae.research.frameworkcmp;

import java.util.HashMap;
import java.util.Map;

import amcrae.research.frameworkcmp.TicTacToeBoard.TTTPiece;

public class TestStubTTTPlayer implements TTTPlayer {

	protected String playerName;
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public String getPlayerName() {
		return playerName;
	}
	
	public class ClientState {
		public TicTacToeGame game;
		public TTTPiece piece;
	}
	
	protected Map<Integer, ClientState> clientGames = new HashMap<>(5);
	
	public boolean isPlaying(Game g) {
		for ( ClientState cs : clientGames.values()) {
			if (g.equals(cs.game)) return true;
		}
		return false;
	}

	public ClientState getClientState(Game g) {
		return clientGames.get(g.getGameId());
	}
	
	
	@Override
	/** Should be overridden by subclasses such as AI or Human players*/
	public void notifyMoveMade(Game g) {
		System.err.println("Move made in game "+g);
	}

	@Override
	public void setPiece(TicTacToeGame game, TTTPiece sym) {
		System.err.println("Piece set to " +sym+" by system in game "+game);
		
		if (! clientGames.containsKey( Integer.valueOf(game.getGameId()) ) ) {
			ClientState cs = new ClientState();
			cs.game = game;
			clientGames.put(Integer.valueOf(game.getGameId()), cs);
		}
		ClientState cs  = clientGames.get(Integer.valueOf(game.getGameId()));
		cs.piece = sym;
	}

}
