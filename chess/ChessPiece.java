package chess;

import java.util.ArrayList;

public abstract class ChessPiece {
    protected Position position;
    protected ChessBoard board;
    private ColorEnum color;

    public abstract ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException;

    protected boolean isLegalMove(Position newPosition) throws InvalidMoveException, GameStateException {

        if (!board.isInBounds(newPosition)) {
            return false;
        }
        ChessPiece newPositionFigure = this.board.getChessPiece(newPosition);
        if (newPositionFigure.getColor() == this.getColor()) {
            return false;
        }

        if (!board.wayIsClear(position, newPosition)) {
            return false;
        }
        // if (!kingIsSafe()) {
        // return false;
        // }

        return true;
    }

    public abstract char getName();

    protected ChessPiece(ColorEnum color, ChessBoard board) {
        this.color = color;
        this.board = board;
        this.position = new Position(0, 0);
    }

    protected ArrayList<Position> getAllPositionsInLine(int fileIncrement, int rankIncrement)
            throws InvalidMoveException, GameStateException {
        ArrayList<Position> results = new ArrayList<>();
        Position tempPos = new Position(this.position);
        while (isLegalMove(tempPos)) {
            results.add(tempPos);
            tempPos.rank = tempPos.rank + rankIncrement;
            tempPos.file = tempPos.file + fileIncrement;
        }
        return results;
    }

    protected ColorEnum getColor() {
        return this.color;
    }

    protected boolean kingIsSafe() throws InvalidMoveException, GameStateException {
        King king = board.getKing(color);
        return !king.isInDanger(king.position);
    }
}
