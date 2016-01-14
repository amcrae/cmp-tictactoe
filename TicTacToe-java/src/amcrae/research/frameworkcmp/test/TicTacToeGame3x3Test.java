package amcrae.research.frameworkcmp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import amcrae.research.frameworkcmp.GameType;
import amcrae.research.frameworkcmp.TicTacToeGame;

public class TicTacToeGame3x3Test {

	TicTacToeGame defaultGame;
	
	@Before
	public void setUp() throws Exception {
		defaultGame = new TicTacToeGame();
	}

	@After
	public void tearDown() throws Exception {
		defaultGame = null; //gc
	}

	@Test
	public void testTicTacToeGame() {
		assertEquals("Should not be any players yet.", 0, defaultGame.getPlayers().length);
		GameType gt = defaultGame.getGameType();
		assertArrayEquals("Dimensions wrong.", new int[]{3,3}, gt.getDimensions());
		assertEquals("Player min should be two.", 2, gt.getPlayersMin());
		assertTrue("Title should contain Tic-Tac-Toe and dimensions", gt.getName().contains("Tic-Tac-Toe") && gt.getName().contains("3") );
	}

	@Test
	public void testGetGameId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameStartTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGameType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayerJoins() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSymbolPlayer() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPlayerLeaves() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetCurrentMovePlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFinalState() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testStartDefaultMultiPlayerGame() {
		fail("Not yet implemented");
	}
	
}
