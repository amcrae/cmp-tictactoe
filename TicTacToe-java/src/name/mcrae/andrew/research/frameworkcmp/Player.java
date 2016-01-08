package name.mcrae.andrew.research.frameworkcmp;

/** Generic representation of a player of games. 
 * 
 *  This is an interface rather than a class on the assumption that it makes it easier to bring other Java code 
 *  written for other games into this comparison framework, should one desire to ever do that. */
public interface Player {

	/** This name is required to be unique on the server. */
	String getPlayerNamme();
	
	/** Inform the player that a move has been made so they can query the game state and act accordingly. */
	void notifyMoveMade(Game g);
}
