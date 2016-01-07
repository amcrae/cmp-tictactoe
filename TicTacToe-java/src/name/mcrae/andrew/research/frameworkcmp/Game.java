package name.mcrae.andrew.research.frameworkcmp;

public interface Game {

	int getGameId();
	GameType getGameType();
	GameState getState();
	Player[] getPlayers();
	
	public enum GameState {
		INVITING,
		PLAY,
		ENDED
	}
	
	
}
