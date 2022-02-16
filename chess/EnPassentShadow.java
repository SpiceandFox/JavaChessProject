package chess;
import java.util.ArrayList;

public class EnPassentShadow extends ChessPiece {
    public EnPassentShadow(ChessBoard board) {
        super(ColorEnum.Empty, board);
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
}
