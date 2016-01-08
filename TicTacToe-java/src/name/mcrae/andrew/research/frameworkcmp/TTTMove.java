package name.mcrae.andrew.research.frameworkcmp;

import name.mcrae.andrew.research.frameworkcmp.TicTacToeBoard.TTTPiece;

public class TTTMove {
	
	public enum MoveType {
		PASS,
		PLACE_PIECE,
		RESIGN
	}
	
	private long timestamp;
	private TTTPlayer subject;
	private MoveType verb;
	private TTTPiece object;
	private Vec2D<Integer> position;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public TTTPlayer getSubject() {
		return subject;
	}
	public void setSubject(TTTPlayer subject) {
		this.subject = subject;
	}
	public MoveType getVerb() {
		return verb;
	}
	public void setVerb(MoveType verb) {
		this.verb = verb;
	}
	public TTTPiece getObject() {
		return object;
	}
	public void setObject(TTTPiece object) {
		this.object = object;
	}
	public Vec2D<Integer> getPosition() {
		return position;
	}
	public void setPosition(Vec2D<Integer> position) {
		this.position = position;
	}
	
}
