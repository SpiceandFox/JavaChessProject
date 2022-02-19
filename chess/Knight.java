package chess;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    public Knight(ColorEnum color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException {
        ArrayList<Position> result = new ArrayList<>();

        // clockwise order
        Position tempPosition = new Position(this.position);
        tempPosition.file++;
        tempPosition.rank++;
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file++;
        tempPosition.file++;
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file++;
        tempPosition.file++;
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file++;
        tempPosition.rank--;
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        tempPosition.rank--;
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        tempPosition.file--;
        tempPosition.rank--;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        tempPosition.file--;
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        tempPosition = new Position(this.position);
        tempPosition.file--;
        tempPosition.rank++;
        tempPosition.rank++;
        if (isLegalMove(tempPosition)) {
            result.add(tempPosition);
        }

        return result;
    }

    @Override
    protected boolean isLegalMove(Position newPosition) throws InvalidMoveException, GameStateException {

        if (!super.isLegalMove(newPosition)) {
            return false;
        }

        if ((Math.abs(position.rank - newPosition.rank) == 1 && Math.abs(position.file - newPosition.file) == 2)
                || (Math.abs(position.rank - newPosition.rank) == 2
                        && Math.abs(position.file - newPosition.file) == 1)) {
            return true;
        }
        return false;
    }

    @Override
    public char getName() {
        if (this.getColor() == ColorEnum.Black) {
            return 'n';
        }
        return 'N';
    }

}
