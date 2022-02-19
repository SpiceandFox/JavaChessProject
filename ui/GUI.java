package ui;

import java.awt.Color;

import javax.swing.JFrame;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Position;

public class GUI implements IUserInterface {
    private JFrame mainFrame = new JFrame();
    private ChessBoardPanel chessPanel;
    private ChessBoard board;

    public GUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("Chess");
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(Color.RED);
        mainFrame.setLayout(null);

        chessPanel = new ChessBoardPanel(400, Color.WHITE, Color.BLACK);
        chessPanel.setBounds(10, 10, 410, 410);
        mainFrame.add(chessPanel);

        mainFrame.setVisible(true);
    }

    private ChessPiece[] reverseArray(ChessPiece[] array) {
        ChessPiece[] newPieces = new ChessPiece[array.length];
        int index = array.length;
        for (int i = 0; i < newPieces.length; i++) {
            index--;
            newPieces[index] = array[i];
        }
        return newPieces;
    }

    @Override
    public void displayBoard() {
        ChessPiece[] pieces = reverseArray(board.getChessPieces());
        for (int i = 0; i < pieces.length; i++) {
            chessPanel.setChessPiece(pieces[i].getName(), i);
        }
    }

    @Override
    public void displayMessage(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    @Override
    public void run() {
        try {
            displayBoard();
        } catch (Exception e) {
            displayMessage(e.getMessage());
        }
    }

}
