package amcrae.research.frameworkcmp;

import amcrae.research.frameworkcmp.TicTacToeBoard.TTTPiece;

/** This adds any messaging or implied state specific to a player of Tic-Tac-Toe. */
public interface TTTPlayer extends Player {

	/** As Tic-Tac-Toe is a board game with pieces placed by players, 
	 * a player needs to know which piece has been assigned to them by the game system.
	 * This leaves open the possibility that the player may first request to play a particular piece, which the Game may honour.
	 * @param game	The game in which the player has now been assigned a playing piece.
	 * @param sym	The piece just assigned.
	 * */
	void setPiece(TicTacToeGame game, TTTPiece sym);
}
