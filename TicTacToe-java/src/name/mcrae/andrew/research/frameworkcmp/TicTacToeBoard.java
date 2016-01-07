package name.mcrae.andrew.research.frameworkcmp;

public class TicTacToeBoard {

	public enum TTTPiece {
		NOUGHT("Ο"), CROSS("Χ"), TRIANGLE("Δ");
		
		TTTPiece(String sym){
			this.symbol=sym;
		}
		public String symbol;
		public String plural;
	}
	
	private TTTPiece[][] slots;
	private int w;
	private int h;
	
	public TicTacToeBoard(int width, int height) {
		this.w = width;
		this.h = height;
		this.slots = new TTTPiece[h][w];
	}
	
	public TTTPiece getSlot(int x, int y) {
		return slots[y][x];
	}
	
	public void setSlot(int x, int y, TTTPiece piece) {
		slots[y][x] = piece;
	}
}
