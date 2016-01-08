package amcrae.research.frameworkcmp;

/** This high-level interface represents some of the basic state of a game that is viewed/controlled by other classes in the framework comparison project.
 *  In theory it only needs to be implemented once as the model of the game and the different web/app frameworks will use this same code as their model in MVC.
 *  
 *  This is an interface rather than a class on the assumption that it makes it easier to bring other Java code 
 *  written for other games into this comparison framework, should one desire to ever do that. */
public interface Game {

	/** This should return a number unique to an instance of the game, for reference by clients. */
	int getGameId();
	
	/** Return data structure that constrains what type of game is being (or can be) played. */
	GameType getGameType();
	
	/** This returns an array of generic players (not specific to any game) currently waiting or playing the game, 
	 * which is an array that should not be modified. */
	Player[] getPlayers();
	
	/** Add a player into the game, where the specific game code decides where and how to add them. 
	 * @throws IllegalStateException when the player cannot join. 
	 * @throws IllegalArgumentException when the given player is already an active player.
	 * */ 
	void playerJoins(Player p);
	
	/** Remove a current player from the game, where the specific game code decides if and how to remove them. 
	 * @throws IllegalStateException when the player cannot leave. 
	 * @throws IllegalArgumentException when the given player is not currently an active player. 
	 * */
	void playerLeaves(Player p);

	/** Show current gross generic state of the game, not specific to the actual game type. */
	GameState getState();
	
	/** Represent whether game is waiting, in play, or game over. */
	public enum GameState {
		/** Game cannot play because some pre-requisites (e.g. number of players) are not satisfied. */
		PREREQUISITES,
		/** Game is in progress. */
		PLAY,
		/** Game has ended for some reason. */
		ENDED
	}
	
}
