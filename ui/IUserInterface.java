package ui;

import chess.ChessBoard;
import chess.Position;

public interface IUserInterface {
	public void displayBoard();

	public void displayMessage(String message);

	public Position getPosition();

	public void setBoard(ChessBoard board);

	public void run();
}
