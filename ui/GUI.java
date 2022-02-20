package ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
    private JLayeredPane chessBoardSection;
    private JPanel possibleMovePanel;
    ArrayList<JLabel> possibleMovesPanels = new ArrayList<>();

    public GUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("Chess");
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(Color.RED);
        mainFrame.setLayout(null);

        initListener();
        initChessBoardSection();
        chessPanel = new ChessBoardPanel(400, Color.WHITE, Color.BLACK, chessPieceListener);
        chessPanel.setBounds(10, 10, 410, 410);
        chessBoardSection.add(chessPanel);
        mainFrame.setVisible(true);
    }

    private void initChessBoardSection() {
        chessBoardSection = new JLayeredPane();
        chessBoardSection.setBounds(0, 0, 430, 430);
        chessBoardSection.setBackground(Color.BLUE);
        chessBoardSection.setOpaque(true);
        mainFrame.add(chessBoardSection);
        initPossibleMovesPanel();
        chessBoardSection.setVisible(true);

    }

    private void initPossibleMovesPanel() {
        possibleMovePanel = new JPanel();
        possibleMovePanel.setSize(chessBoardSection.getSize());
        possibleMovePanel.setOpaque(false);
        possibleMovePanel.setVisible(true);
        chessBoardSection.add(possibleMovePanel, Integer.valueOf(3));
    }

    private void displayPossibleMoves(ChessPiece piece) throws InvalidMoveException, GameStateException {
        clearPossibleMoves();
        ArrayList<Position> moves = piece.getAllPossibleMoves();
        for (Position move : moves) {
            displayPossibleMove(move.getBoardArrayIndex());
        }

    }

    public void displayPossibleMove(int position) {
        Point p = chessPanel.getChessSquareCenterPoint(position);
        JLabel possibleMove = new JLabel();
        possibleMove.setBackground(Color.GREEN);
        possibleMove.setLocation(p);
        possibleMove.setOpaque(true);
        possibleMove.setSize(5, 5);
        possibleMovePanel.add(possibleMove);
        possibleMovesPanels.add(possibleMove);
        possibleMove.setVisible(true);
        possibleMovePanel.repaint();
    }

    public void clearPossibleMoves() {
        for (JLabel jLabel : possibleMovesPanels) {
            jLabel.setBackground(jLabel.getBackground());
        }
        possibleMovesPanels.clear();
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
