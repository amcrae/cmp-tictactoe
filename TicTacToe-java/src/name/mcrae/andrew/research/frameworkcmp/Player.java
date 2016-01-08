package name.mcrae.andrew.research.frameworkcmp;

/** Generic representation of a player of games. 
 * 
 *  This is an interface rather than a class on the assumption that it makes it easier to bring other Java code 
 *  written for other games into this comparison framework, should one desire to ever do that. */
public interface Player {

	String getPlayerNamme();
	void notifyMoveMade(Game g);
}
