package ui;

import java.util.Scanner;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Position;
import chess.WinException;

public class ConsoleUI implements IUserInterface {
	private boolean continuePlaying = true;
	private static Scanner scanner = new Scanner(System.in);
	private ChessBoard board;

	@Override
	public void displayBoard() {
		ChessPiece[] figures = board.getChessPieces();
		for (int i = 0; i < figures.length; i++) {
			if (i % 8 == 0) {
				System.out.println();
				System.out.print("    +---+---+---+---+---+---+---+---+");
				System.out.println();
				System.out.print((i / 8 + 1) + "   |");
			}
			System.out.print(' ');
			System.out.print(figures[i].getName());
			System.out.print(' ');
			System.out.print('|');
		}
		System.out.println();
		System.out.println("    +---+---+---+---+---+---+---+---+");
		System.out.println("      a   b   c   d   e   f   g   h");
		System.out.println("Player " + board.getCurrentPlayer().toString() + " to play");
	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

	@Override
	public Position getPosition() {
		String userInput = scanner.nextLine();
		return new Position(userInput);
	}

	@Override
	public void run() {
		Position from;
		Position to;
		while (continuePlaying) {
			try {
				displayBoard();
				displayMessage("Next move:");
				displayMessage("From:");
				from = getPosition();
				displayMessage("To:");
				to = getPosition();
				board.moveChessPiece(from, to);
			} catch (WinException e) {
				displayMessage(e.getMessage());
				continuePlaying = false;
			} catch (Exception e) {
				displayMessage(e.getMessage());
			}
		}
	}

	@Override
	public void setBoard(ChessBoard board) {
		this.board = board;

	}
}