package name.mcrae.andrew.research.frameworkcmp;

public class GameType {

	private int gameTypeId;
	private String name;
	private int playersMin;
	private int playersMax;
	private int[] dimensions;
	private String briefInstruction;
	private String fullRules;
	
	public int getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlayersMin() {
		return playersMin;
	}
	public void setPlayersMin(int playersMin) {
		this.playersMin = playersMin;
	}
	public int getPlayersMax() {
		return playersMax;
	}
	public void setPlayersMax(int playersMax) {
		this.playersMax = playersMax;
	}
	public int[] getDimensions() {
		return dimensions;
	}
	public void setDimensions(int[] dimensions) {
		this.dimensions = dimensions;
	}
	public String getBriefInstruction() {
		return briefInstruction;
	}
	public void setBriefInstruction(String briefInstruction) {
		this.briefInstruction = briefInstruction;
	}
	public String getFullRules() {
		return fullRules;
	}
	public void setFullRules(String fullRules) {
		this.fullRules = fullRules;
	}
	
}
