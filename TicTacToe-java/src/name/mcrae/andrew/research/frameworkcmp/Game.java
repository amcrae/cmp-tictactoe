package name.mcrae.andrew.research.frameworkcmp;

public interface Game {

	int getGameId();
	GameType getGameType();
	Player[] getPlayers();
	void playerJoins(Player p);
	void playerLeaves(Player p);
	GameState getState();
	
	public enum GameState {
		INVITING,
		PLAY,
		ENDED
	}
	
}
