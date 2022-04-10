package chess;

import java.util.ArrayList;

public abstract class ChessPiece {
    protected Position position;
    protected ChessBoard board;
    private ColorEnum color;

    public abstract ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException;

    public abstract boolean canMoveThisWay(Position newPosition);

    /**
     * Checks legality of the parameter move
     * 
     * @param newPosition
     * @return boolean
     * @throws InvalidMoveException
     * @throws GameStateException
     */
    protected boolean isLegalMove(Position newPosition) throws InvalidMoveException, GameStateException {

        if (!board.isInBounds(newPosition)) {
            return false;
        }
        ChessPiece newPositionFigure = this.board.getChessPiece(newPosition);
        if (newPositionFigure.getColor() == this.getColor()) {
            return false;
        }

        if (!kingIsSafe()) {
            if (!board.kingIsSafeOnNextTurn(this, newPosition)) {
                return false;
            }
        }

        return canMoveThisWay(newPosition);
    }

    public abstract char getName();

    protected ChessPiece(ColorEnum color, ChessBoard board) {
        this.color = color;
        this.board = board;
        this.position = new Position(0, 0);
    }

    /**
     * returns a list of positions in a straigth line,
     * can be diagonal
     * 
     * @param fileIncrement
     * @param rankIncrement
     * @return ArrayList<Position>
     * @throws InvalidMoveException
     * @throws GameStateException
     */
    protected ArrayList<Position> getAllPositionsInLine(int fileIncrement, int rankIncrement)
            throws InvalidMoveException, GameStateException {
        ArrayList<Position> results = new ArrayList<>();
        Position tempPos = new Position(this.position);
        tempPos.rank = tempPos.rank + rankIncrement;
        tempPos.file = tempPos.file + fileIncrement;
        while (isLegalMove(tempPos)) {
            results.add(tempPos);
            tempPos = new Position(tempPos);
            tempPos.rank = tempPos.rank + rankIncrement;
            tempPos.file = tempPos.file + fileIncrement;
        }
        return results;
    }

    /**
     * returns color of piece
     * 
     * @return ColorEnum
     */
    protected ColorEnum getColor() {
        return this.color;
    }

    /**
     * checks if the king of this color is safe
     * 
     * @return boolean
     * @throws InvalidMoveException
     * @throws GameStateException
     */
    protected boolean kingIsSafe() throws InvalidMoveException, GameStateException {
        King king = board.getKing(color);
        return !king.isInDanger(king.position);
    }
}
