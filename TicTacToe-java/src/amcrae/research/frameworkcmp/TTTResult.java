package amcrae.research.frameworkcmp;

/** Represents the game state that results (or would result) 
 * from a player making (or hypothetically attempting to make) a particular move in Tic-Tac-Toe.
 * */
public class TTTResult {

	public enum MoveResult {
		CONTINUE,
		DRAW,
		WIN
	}
	
	private TTTMove lastMove;
	private MoveResult moveResult;
	private boolean permitted;
	private TicTacToeBoard newState;
	
	public TTTMove getLastMove() {
		return lastMove;
	}
	public void setLastMove(TTTMove lastMove) {
		this.lastMove = lastMove;
	}
	public MoveResult getMoveResult() {
		return moveResult;
	}
	public void setMoveResult(MoveResult moveResult) {
		this.moveResult = moveResult;
	}
	public boolean isPermitted() {
		return permitted;
	}
	public void setPermitted(boolean permitted) {
		this.permitted = permitted;
	}
	public TicTacToeBoard getNewState() {
		return newState;
	}
	public void setNewState(TicTacToeBoard newState) {
		this.newState = newState;
	}
	
	
}
