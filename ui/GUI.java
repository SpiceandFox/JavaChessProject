package ui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Position;

public class GUI implements IUserInterface {
    private JFrame mainFrame = new JFrame();
    private ChessBoardPanel chessPanel;
    private ChessBoard board;
    private ActionListener chessPieceListener;
    private ArrayList<chess.Position> possibleMoves = new ArrayList<>();
    private Position fromPosition = new Position();
    // ChessBoardSection holds the chessboard and the surrounding rank/file
    // descriptors
    private JLayeredPane chessBoardSection;
    // errorDisplaySection holds the Textoutput at the bottom of screen
    private JLayeredPane errorDisplaySection;
    private JTextPane errorTextArea;
    // moveDisplay shows last played moves at right side
    private JLayeredPane moveDisplaySection;

    public GUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("Chess");
        mainFrame.setResizable(true);
        mainFrame.getContentPane().setBackground(Color.RED);
        mainFrame.setLayout(new BorderLayout());

        initListener();
        initChessBoardSection();
        initMoveDisplaySection();
        initErrorDisplaySection();

        mainFrame.setVisible(true);
        mainFrame.repaint();
    }

    private void initErrorDisplaySection() {
        errorDisplaySection = new JLayeredPane();
        errorDisplaySection.setPreferredSize(new Dimension(400, 100));
        errorDisplaySection.setBackground(Color.ORANGE);
        errorDisplaySection.setOpaque(true);
        mainFrame.add(errorDisplaySection, BorderLayout.SOUTH);
        errorDisplaySection.setVisible(true);

        errorTextArea = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        errorTextArea.setCharacterAttributes(attributeSet, true);
        errorTextArea.setText("test");
        errorDisplaySection.setBackground(Color.LIGHT_GRAY);
        errorTextArea.setOpaque(true);
        errorTextArea.setPreferredSize(new Dimension(100, 80));
        errorDisplaySection.add(errorTextArea, BorderLayout.CENTER);
        errorDisplaySection.moveToFront(errorTextArea);
        errorTextArea.setVisible(true);

    }

    private void initMoveDisplaySection() {
        moveDisplaySection = new JLayeredPane();
        moveDisplaySection.setPreferredSize(new Dimension(200, 600));
        moveDisplaySection.setBackground(Color.MAGENTA);
        moveDisplaySection.setOpaque(true);
        mainFrame.add(moveDisplaySection, BorderLayout.EAST);
        moveDisplaySection.setVisible(true);
    }

    private void initChessBoardSection() {
        chessBoardSection = new JLayeredPane();
        chessBoardSection.setPreferredSize(new Dimension(430, 430));
        chessBoardSection.setLayout(new GridBagLayout());
        chessBoardSection.setBackground(Color.BLUE);
        chessBoardSection.setOpaque(true);
        mainFrame.add(chessBoardSection, BorderLayout.CENTER);
        chessPanel = new ChessBoardPanel(400, new Color(239, 217, 181, 255), new Color(181, 136, 99, 255),
                chessPieceListener);
        chessPanel.setPreferredSize(new Dimension(400, 400));
        chessBoardSection.add(chessPanel, new GridBagConstraints());
        chessBoardSection.setVisible(true);

    }

    private void initListener() {
        this.chessPieceListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = ((ChessFigureButton) e.getSource()).getPosition();
                try {
                    Position toPosition = new Position(index);
                    ChessPiece activeChessPiece = board.getChessPiece(index);
                    if (possibleMoves.contains(toPosition)) {
                        board.moveChessPiece(fromPosition, toPosition);
                        displayBoard();
                        return;
                    } else {
                        fromPosition = new Position(toPosition);
                    }
                    possibleMoves = activeChessPiece.getAllPossibleMoves();
                    chessPanel.displayPossibleMoves(possibleMoves);
                } catch (Exception exception) {
                    displayMessage(exception.getMessage());
                }
            }
        };
    }

    @Override
    public void displayBoard() {
        chessPanel.clearPossibleMoves();
        ChessPiece[] pieces = board.getChessPieces();
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
