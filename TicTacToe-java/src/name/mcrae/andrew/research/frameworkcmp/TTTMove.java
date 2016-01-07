package name.mcrae.andrew.research.frameworkcmp;

import name.mcrae.andrew.research.frameworkcmp.TicTacToeBoard.TTTPiece;

public class TTTMove {
	
	public enum MoveType {
		PASS,
		PLACE_PIECE
	}
	
	private long timestamp;
	private TTTPlayer subject;
	private MoveType verb;
	private TTTPiece object;
	private Vec2D position;

}
