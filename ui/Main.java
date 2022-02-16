package ui;
import chess.ChessBoard;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        IUserInterface ui = new ConsoleUI();
        ui.setBoard(board);
        ui.run();
    }
}