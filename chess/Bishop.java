package chess;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(ColorEnum color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException {
        ArrayList<Position> result = new ArrayList<>();
        result.addAll(this.getAllPositionsInLine(1, 1));
        result.addAll(this.getAllPositionsInLine(-1, -1));
        result.addAll(this.getAllPositionsInLine(-1, 1));
        result.addAll(this.getAllPositionsInLine(1, -1));
        return result;
    }

    @Override
    public char getName() {
        if (this.getColor() == ColorEnum.Black) {
            return 'b';
        }
        return 'B';
    }

    @Override
    public boolean canMoveThisWay(Position newPosition) {
        if ((Math.abs(this.position.rank - newPosition.rank) != Math.abs(this.position.file - newPosition.file))) {
            return false;
        }
        return board.wayIsClear(position, newPosition);
    }

}
