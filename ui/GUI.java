package ui;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.GameStateException;
import chess.InvalidMoveException;
import chess.Position;

public class GUI implements IUserInterface {
    private JFrame mainFrame = new JFrame();
    private ChessBoardPanel chessPanel;
    private ChessBoard board;
    private ActionListener chessPieceListener;

    public GUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("Chess");
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(Color.RED);
        mainFrame.setLayout(null);

        initListener();
        chessPanel = new ChessBoardPanel(400, Color.WHITE, Color.BLACK, chessPieceListener);
        chessPanel.setBounds(10, 10, 410, 410);
        mainFrame.add(chessPanel);
        mainFrame.setVisible(true);
    }

    private void displayPossibleMoves(ChessPiece piece) throws InvalidMoveException, GameStateException {
        chessPanel.clearPossibleMoves();
        ArrayList<Position> moves = piece.getAllPossibleMoves();
        for (Position move : moves) {
            chessPanel.displayPossibleMove(move.getBoardArrayIndex());
        }

    }

    private void initListener() {
        this.chessPieceListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = ((ChessFigureButton) e.getSource()).getPosition();
                try {
                    displayPossibleMoves(board.getChessPiece(index));
                } catch (Exception exception) {
                    displayMessage(exception.getMessage());
                }
            }
        };
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
        System.out.println(message);

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
