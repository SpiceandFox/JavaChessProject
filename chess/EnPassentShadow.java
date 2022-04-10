package chess;

import java.util.ArrayList;

public class EnPassentShadow extends ChessPiece {
    public EnPassentShadow(ChessBoard board, Position position) {
        super(ColorEnum.Empty, board);
        this.position = position;
    }

    @Override
    public ArrayList<Position> getAllPossibleMoves() {
        return new ArrayList<>();
    }

    @Override
    protected boolean isLegalMove(Position newPosition) {
        return false;
    }

    @Override
    public char getName() {
        return ' ';
    }

    @Override
    public boolean canMoveThisWay(Position newPosition) {
        return false;
    }
}
