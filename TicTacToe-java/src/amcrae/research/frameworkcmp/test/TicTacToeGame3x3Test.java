package amcrae.research.frameworkcmp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import amcrae.research.frameworkcmp.GameType;
import amcrae.research.frameworkcmp.Player;
import amcrae.research.frameworkcmp.TTTMove;
import amcrae.research.frameworkcmp.TTTMove.MoveType;
import amcrae.research.frameworkcmp.TTTPlayer;
import amcrae.research.frameworkcmp.TTTResult;
import amcrae.research.frameworkcmp.TTTResult.MoveResult;
import amcrae.research.frameworkcmp.TestStubTTTPlayer;
import amcrae.research.frameworkcmp.TicTacToeBoard;
import amcrae.research.frameworkcmp.TicTacToeGame;
import amcrae.research.frameworkcmp.Vec2D;
import amcrae.research.frameworkcmp.Game.GameState;
import amcrae.research.frameworkcmp.TicTacToeBoard.TTTPiece;

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
		assertTrue("Title should contain Tic-Tac-Toe and dimensions", 
				gt.getName().contains("Tic-Tac-Toe") && gt.getName().contains("3") 
		);
	}

	@Test
	public void testGetGameId() {
		assertTrue("Should be greater than 0", defaultGame.getGameId()>0);
		TicTacToeGame g2 = new TicTacToeGame();
		assertNotEquals("Should be different per game.", g2.getGameId(), defaultGame.getGameId());
	}

	@Test
	public void testGetGameType() {
		GameType gt = defaultGame.getGameType();
		assertNotNull(gt);
		assertArrayEquals("Should be 3x3 by default", new int[]{3,3}, gt.getDimensions());
		assertNotNull(gt.getBriefInstruction());
		assertTrue("Brief description did not even mention the main player action or the board.", 
			gt.getBriefInstruction().contains("place") || gt.getBriefInstruction().contains("board") 
		);
		assertTrue("gameType should be >0", gt.getGameTypeId()>0);
		assertNotNull(gt.getName());
		assertTrue("Name does not mention Tic or Tac", gt.getName().contains("Tic") && gt.getName().contains("Tac"));
		assertEquals("player min must be 2", 2, gt.getPlayersMin());
		assertEquals("player max must be 2", 2, gt.getPlayersMax());
	}

	@Test
	public void testGetState() {
		assertEquals("default game should be in a waiting state.", defaultGame.getState(), GameState.PREREQUISITES );
	}

	@Test
	public void testPlayerJoins() {
		//Some DI would be nice.
		TestStubTTTPlayer p1 = new TestStubTTTPlayer();
		p1.setPlayerName("Player 1");
		defaultGame.playerJoins(p1);
		assertTrue("Should be playing the game it just joined.", p1.isPlaying(defaultGame) );
		assertNotNull("The player was not assigned a playing piece!", p1.getClientState(defaultGame).piece );

		try {
			defaultGame.playerJoins(p1);
			fail("trying to join the same game twice should have thrown exception.");
		} catch (Exception je) {
			assertNotNull("Expected exception thrown ", je.getClass() );
		}

		TestStubTTTPlayer p2 = new TestStubTTTPlayer();
		p1.setPlayerName("Player 2");
		defaultGame.playerJoins(p2);
		assertTrue("Should be playing the game it just joined.", p2.isPlaying(defaultGame) );
		assertNotNull("The player was not assigned a playing piece!", p2.getClientState(defaultGame).piece );
		
		assertNotEquals("Players should not have been assigned the same playing pieces", p2.getClientState(defaultGame).piece, p1.getClientState(defaultGame).piece );
	}

	List<TTTPlayer> players = new ArrayList<>(); 
	
	/**  should be same sequence as in testPlayerJoins */
	private void add2Players() {
		TestStubTTTPlayer p1 = new TestStubTTTPlayer();
		p1.setPlayerName("Player 1");
		defaultGame.playerJoins(p1);
		players.add(p1);
		
		TestStubTTTPlayer p2 = new TestStubTTTPlayer();
		p1.setPlayerName("Player 2");
		defaultGame.playerJoins(p2);
		players.add(p2);
	}
	
	
	@Test
	public void testGetPlayers() {
		add2Players();
		assertNotNull( defaultGame.getPlayers() );
		Player[] pa = defaultGame.getPlayers();
		TTTPlayer[] tpa = Arrays.copyOf(pa, pa.length, TTTPlayer[].class);
		assertEquals("Wrong player count", 2, tpa.length);
		boolean found = true;
		for (TTTPlayer p : players) {
			assertTrue("Didn't find "+p, found = found && ( tpa[0].equals(p) || tpa[1].equals(p)) ); 
		}
	}

	@Test
	public void testGetSymbolPlayer() {
		add2Players();
		assertEquals("Not expected piece order", players.get(1), defaultGame.getSymbolPlayer(TTTPiece.CROSS));
		assertEquals("Not expected piece order", players.get(0), defaultGame.getSymbolPlayer(TTTPiece.NOUGHT));
	}
	
	@Test
	public void testPlayerLeaves() {
		add2Players();
		TTTPlayer p2 = players.get(1);
		defaultGame.playerLeaves(p2);
		assertEquals("Player object still hanging around", 1, defaultGame.getPlayers().length);
		assertNotEquals("Wrong player was removed", p2, defaultGame.getPlayers()[0] );
	}

	protected final Vec2D<Integer> MIDDLE_3x3 = new Vec2D<>(1,1);
	
	@Test
	public void testCheck() {
		add2Players();
		TicTacToeBoard s0 = new TicTacToeBoard(3,3);
		TTTMove m0 = new TTTMove();
		TTTPlayer p =  defaultGame.getSymbolPlayer(TTTPiece.NOUGHT);
		m0.setSubject(p);
		m0.setVerb(MoveType.PLACE_PIECE);
		m0.setObject(TTTPiece.NOUGHT);
		m0.setPosition(MIDDLE_3x3);
		TTTResult r0 = defaultGame.check(s0, m0);
		assertTrue("Valid first move was not allowed", r0.isPermitted() );
		assertNotNull("Didn't get any piece at assigned position", r0.getNewState().getSlot(MIDDLE_3x3) );
		assertEquals("Didn't get played piece at assigned position", TTTPiece.NOUGHT, r0.getNewState().getSlot(MIDDLE_3x3) );
		assertEquals("Game state wasn't expected this early.", MoveResult.CONTINUE, r0.getMoveResult() );
	}
	
	@Test
	public void testGetCurrentMovePlayer() {
		add2Players();
		assertEquals("Player 1 with noughts should go first", players.get(0), defaultGame.getCurrentMovePlayer() );
	}

	@Test
	public void testGetGameStartTime() {
		Date t1 = new Date();
		try { Thread.sleep(10); } catch (InterruptedException inte) { }
		add2Players();
		try { Thread.sleep(10); } catch (InterruptedException inte) { }
		Date t2 = new Date();
		Date st = defaultGame.getGameStartTime();
		assertNotNull("No start time when one should be started.", st);
		assertTrue("Should have started game clock after"+t1,  st.after(t1)   );
		assertTrue("Should have started game clock before"+t2, st.before(t2)  );
	}

	@Test
	public void testPlayMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentBoard() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testGetHistory() {
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
