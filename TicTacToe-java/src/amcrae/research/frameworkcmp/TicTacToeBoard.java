package amcrae.research.frameworkcmp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeBoard implements Serializable {

	public enum TTTPiece {
		NOUGHT("Ο"), CROSS("Χ"), TRIANGLE("Δ");
		
		TTTPiece(String sym){
			this.symbol=sym;
		}
		private String symbol;
		
		public String getSymbol() {
			return symbol;
		}
	}
	
	private TTTPiece[][] slots;
	private int w;
	private int h;
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(slots);
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			TicTacToeBoard other = (TicTacToeBoard)obj;
			return Arrays.deepEquals(this.slots, other.slots);
		} catch (ClassCastException cce) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + String.format(" %dx%d %s", w,h, Arrays.deepToString(slots) );
	}
	
	
	public TicTacToeBoard() {
		super();
	}

	/** A copy constructor*/
	public TicTacToeBoard(TicTacToeBoard orig) {
		this();
		this.h = orig.h;
		this.w = orig.w;
		this.slots = Arrays.copyOf(orig.slots, orig.slots.length, TTTPiece[][].class);
	}
	
	/** Usual constructor */
	public TicTacToeBoard(int width, int height) {
		this();
		this.w = width;
		this.h = height;
		this.slots = new TTTPiece[h][w];
	}
	
	/** Co-ordinates are zero-based and bottom left is (0,0). */
	public TTTPiece getSlot(int x, int y) {
		return slots[y][x];
	}

	public TTTPiece getSlot(Vec2D<Integer> pos) {
		return getSlot(pos.getX(),pos.getY());
	}
	
	public void setSlot(int x, int y, TTTPiece piece) {
		slots[y][x] = piece;
	}
	
	public void setSlot(Vec2D<Integer> pos, TTTPiece piece) {
		setSlot(pos.getX(), pos.getY(), piece);;
	}
	
	public List<Vec2D<Integer>> getFreeSlots() {
		List<Vec2D<Integer>> answer = new ArrayList<Vec2D<Integer>>(w*h);
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				Vec2D<Integer> pos = new Vec2D<Integer>(x, y);
				TTTPiece sym = getSlot(pos);
				if (sym==null)	answer.add(pos);
			}
		}
		return answer;
	}

}
