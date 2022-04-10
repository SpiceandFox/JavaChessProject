package chess;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private ChessPiece[] figures = new ChessPiece[64];
    private ColorEnum colorToPlay;
    private ArrayList<ChessPiece> castlingFigures = new ArrayList<>();
    private EnPassentShadow activeShadow;
    private int halfmoveClock;
    private int fullmoveNumber;

    /**
     * Empty constructor
     */
    public ChessBoard() {
        for (int i = 0; i < figures.length; i++) {
            figures[i] = new NullChessPiece(this);
        }
        colorToPlay = ColorEnum.White;
    }

    /**
     * constructor using Forsyth-Edwards Notation
     * 
     * @param boardString
     */
    public ChessBoard(String boardString) {
        int currentWorkingIndex = 0;
        ArrayList<ChessPiece> tempFigures = new ArrayList<>();

        String[] fenFields = boardString.split(" ");

        // reverse string
        StringBuilder sb = new StringBuilder(fenFields[0]);
        fenFields[0] = sb.reverse().toString();

        char[] figureChars = fenFields[0].toCharArray();

        // create all chesspieces
        for (char c : figureChars) {
            tempFigures = getNewChessPieceFromChar(c);
            for (ChessPiece figure : tempFigures) {
                figures[currentWorkingIndex] = figure;
                currentWorkingIndex++;
            }
        }

        // active color
        if (fenFields[1].equals("w")) {
            colorToPlay = ColorEnum.White;
        } else {
            colorToPlay = ColorEnum.Black;
        }

        // set Castling rights
        this.setAllCastlingRights(fenFields[2]);

        // en Passant target
        if (!fenFields[3].equals("-")) {
            Position tempPosition = new Position(fenFields[3]);
            figures[tempPosition.getBoardArrayIndex()] = new EnPassentShadow(this);
        }

        // Halfmove clock
        this.halfmoveClock = Integer.parseInt(fenFields[4]);

        // fullmove number
        this.fullmoveNumber = Integer.parseInt(fenFields[5]);

        this.updateAllPositions();

    }

    /**
     * Returns the Forsyth Edword Notation of current boardstate
     * TO DO
     * 
     * @return String
     */
    public String getFenString() {
        return "asdf";
    }

    /**
     * Returns color of current player
     * 
     * @return ColorEnum
     */
    public ColorEnum getCurrentPlayer() {
        return colorToPlay;
    }

    /**
     * Returns all chess pieces from parameter color
     * 
     * @param color
     * @return ArrayList<ChessPiece>
     */
    protected ArrayList<ChessPiece> getAllChessPiecesFromColor(ColorEnum color) {
        ArrayList<ChessPiece> result = new ArrayList<>();

        for (ChessPiece figure : figures) {
            if (figure.getColor() == color) {
                result.add(figure);
            }
        }

        return result;
    }

    /**
     * returns the array representing the board
     * 
     * @return ChessPiece[]
     */
    public ChessPiece[] getChessPieces() {
        return figures;
    }

    /**
     * returns chess piece of board array on parameter position
     * 
     * @param position
     * @return ChessPiece
     */
    public ChessPiece getChessPiece(Position position) {
        return figures[position.getBoardArrayIndex()];
    }

    /**
     * returns chess piece of board array at parameter index
     * 
     * @param index
     * @return ChessPiece
     */
    public ChessPiece getChessPiece(int index) {
        return figures[index];
    }

    /**
     * Checks bounds of a position
     * 
     * @param position
     * @return boolean
     */
    public boolean isInBounds(Position position) {
        return position.file < 8 && position.file >= 0 && position.rank < 8 && position.rank >= 0;
    }

    /**
     * Returns list of chesspieces based on the character
     * numerical values return an equal amount of NullChessPieces
     * 
     * @param c
     * @return ArrayList<ChessPiece>
     */
    private ArrayList<ChessPiece> getNewChessPieceFromChar(char c) {
        ArrayList<ChessPiece> result = new ArrayList<>();
        if (Character.isDigit(c)) {
            for (int i = 0; i < Character.getNumericValue(c); i++) {
                result.add(new NullChessPiece(this));
            }
            return result;
        }

        switch (c) {
            case 'b':
                result.add(new Bishop(ColorEnum.Black, this));
                break;
            case 'p':
                result.add(new Pawn(ColorEnum.Black, this));
                break;
            case 'r':
                result.add(new Rook(ColorEnum.Black, this));
                break;
            case 'q':
                result.add(new Queen(ColorEnum.Black, this));
                break;
            case 'n':
                result.add(new Knight(ColorEnum.Black, this));
                break;
            case 'k':
                result.add(new King(ColorEnum.Black, this));
                break;
            case 'B':
                result.add(new Bishop(ColorEnum.White, this));
                break;
            case 'P':
                result.add(new Pawn(ColorEnum.White, this));
                break;
            case 'R':
                result.add(new Rook(ColorEnum.White, this));
                break;
            case 'Q':
                result.add(new Queen(ColorEnum.White, this));
                break;
            case 'N':
                result.add(new Knight(ColorEnum.White, this));
                break;
            case 'K':
                result.add(new King(ColorEnum.White, this));
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * Sets all castling rights only sets rights on normal chess layout
     * and doesn't work with alternative gamemodes or board layouts
     * Q = queenside
     * K = kingside
     * lower case = black
     * upper case = white
     * 
     * @param castlingFiguresString
     */
    private void setAllCastlingRights(String castlingFiguresString) {
        char[] tempChars = castlingFiguresString.toCharArray();
        ChessPiece figureToChange;
        for (char c : tempChars) {
            switch (c) {
                case 'k':
                    figureToChange = figures[7];
                    if (figureToChange instanceof Rook) {
                        ((Rook) figureToChange).setCastlingRights();
                    }
                    figureToChange = figures[4];
                    if (figureToChange instanceof King) {
                        ((King) figureToChange).setCastlingRights();
                    }
                    break;
                case 'q':
                    figureToChange = figures[0];
                    if (figureToChange instanceof Rook) {
                        ((Rook) figureToChange).setCastlingRights();
                    }
                    figureToChange = figures[4];
                    if (figureToChange instanceof King) {
                        ((King) figureToChange).setCastlingRights();
                    }
                    break;
                case 'K':
                    figureToChange = figures[63];
                    if (figureToChange instanceof Rook) {
                        ((Rook) figureToChange).setCastlingRights();
                    }
                    figureToChange = figures[60];
                    if (figureToChange instanceof King) {
                        ((King) figureToChange).setCastlingRights();
                    }
                    break;
                case 'Q':
                    figureToChange = figures[56];
                    if (figureToChange instanceof Rook) {
                        ((Rook) figureToChange).setCastlingRights();
                    }
                    figureToChange = figures[60];
                    if (figureToChange instanceof King) {
                        ((King) figureToChange).setCastlingRights();
                    }
                    break;
            }
        }
    }

    /**
     * checks for collition on a straigth line,
     * can be diagonal
     * 
     * @param pos1
     * @param pos2
     * @return boolean
     */
    public boolean wayIsClear(Position pos1, Position pos2) {
        int directionFile = 0;
        int directionRank = 0;

        if (this.getChessPiece(pos1) instanceof Knight) {
            return true;
        }

        if (pos1.rank != pos2.rank) {
            if (pos1.rank < pos2.rank) {
                directionRank = 1;
            } else {
                directionRank = -1;
            }
        }

        if (pos1.file != pos2.file) {
            if (pos1.file < pos2.file) {
                directionFile = 1;
            } else {
                directionFile = -1;
            }
        }
        Position tempPos = new Position(pos1);
        tempPos.file += directionFile;
        tempPos.rank += directionRank;
        while (!tempPos.equals(pos2)) {
            ChessPiece figureOnPosition = figures[tempPos.getBoardArrayIndex()];
            if ((figureOnPosition instanceof NullChessPiece) || (figureOnPosition instanceof EnPassentShadow)) {
                tempPos.file += directionFile;
                tempPos.rank += directionRank;
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * executes the move
     * 
     * @param from
     * @param to
     * @throws InvalidMoveException
     * @throws GameStateException
     * @throws WinException
     */
    public void moveChessPiece(Position from, Position to)
            throws InvalidMoveException, GameStateException, WinException {
        ChessPiece figure = this.getChessPiece(from);
        if (figure.getColor() == ColorEnum.Empty) {
            throw new InvalidMoveException("You didn't select a playing piece");
        }
        if (figure.getColor() != this.colorToPlay) {
            throw new InvalidMoveException("You selected the wrong colored playing piece");
        }

        if (figure.isLegalMove(to)) {
            figures[to.getBoardArrayIndex()] = figure;
            figures[from.getBoardArrayIndex()] = new NullChessPiece(this);
            placeEnPassantShadow(figure, to);
            finishTurn(figure);
        } else {
            throw new InvalidMoveException("Your piece can't move that way");
        }
    }

    /**
     * updates the boardstate
     * 
     * @param figure
     * @throws InvalidMoveException
     * @throws GameStateException
     * @throws WinException
     */
    private void finishTurn(ChessPiece figure) throws InvalidMoveException, GameStateException, WinException {
        if (colorToPlay == ColorEnum.White) {
            colorToPlay = ColorEnum.Black;
        } else {
            colorToPlay = ColorEnum.White;
        }
        updateAllPositions();

        fullmoveNumber++;
        if (figure instanceof Pawn) {
            halfmoveClock = 0;
        } else {
            halfmoveClock++;
        }

        if (gameIsOver()) {
            throw new WinException(figure.getColor().toString());
        }

    }

    /**
     * checks for either a win or a remis based on remaining legal moves
     * TO DO implement remis
     * 
     * @return boolean
     * @throws InvalidMoveException
     * @throws GameStateException
     */
    private boolean gameIsOver() throws InvalidMoveException, GameStateException {
        ArrayList<ChessPiece> enemyFigures = getAllChessPiecesFromColor(colorToPlay);
        List<Position> possibleMoves = new ArrayList<>();
        for (ChessPiece figure : enemyFigures) {
            possibleMoves.addAll(figure.getAllPossibleMoves());
        }
        return possibleMoves.isEmpty();
    }

    /**
     * en passent shadows is spawned whenever a pawn moves
     * two spaces forward
     * 
     * @param figure
     * @param to
     */
    private void placeEnPassantShadow(ChessPiece figure, Position to) {
        if (figure instanceof Pawn == false) {
            return;
        }
        if (Math.abs(figure.position.rank - to.rank) < 2) {
            return;
        }
        Position enPassantShadowPosition = new Position(figure.position);
        enPassantShadowPosition.rank = Math.abs(figure.position.rank - to.rank);

        this.activeShadow = new EnPassentShadow(this);
        figures[enPassantShadowPosition.getBoardArrayIndex()] = this.activeShadow;
    }

    private void updateAllPositions() {
        for (int i = 0; i < figures.length; i++) {
            figures[i].position.updateInternalValues(i);
        }
    }

    /**
     * returns the king of parameter color
     * 
     * @param color
     * @return King
     * @throws GameStateException
     */
    public King getKing(ColorEnum color) throws GameStateException {
        for (ChessPiece figure : figures) {
            if ((figure instanceof King) && (figure.getColor() == color)) {
                return (King) figure;
            }
        }
        throw new GameStateException("There is no King on the Board");
    }

    /**
     * simulates the move and checks for king safety again
     * TO DO edge case for en passant
     * 
     * @param chessPiece
     * @param newPosition
     * @return
     * @throws GameStateException
     * @throws InvalidMoveException
     */
    public boolean kingIsSafeOnNextTurn(ChessPiece chessPiece, Position newPosition)
            throws InvalidMoveException, GameStateException {
        int oldPositionBoardIndex = chessPiece.position.getBoardArrayIndex();
        int newPositionBoardIndex = newPosition.getBoardArrayIndex();
        ChessPiece currentPieceOnNewPosition = getChessPiece(newPosition);

        figures[newPositionBoardIndex] = chessPiece;
        figures[oldPositionBoardIndex] = new NullChessPiece(this);

        figures[oldPositionBoardIndex].position.updateInternalValues(oldPositionBoardIndex);
        figures[newPositionBoardIndex].position.updateInternalValues(newPositionBoardIndex);

        boolean result = chessPiece.kingIsSafe();

        figures[newPositionBoardIndex] = currentPieceOnNewPosition;
        figures[oldPositionBoardIndex] = chessPiece;

        figures[oldPositionBoardIndex].position.updateInternalValues(oldPositionBoardIndex);
        figures[newPositionBoardIndex].position.updateInternalValues(newPositionBoardIndex);

        return result;
    }

}
