package chess;

import java.util.ArrayList;

public class NullChessPiece extends ChessPiece {

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

	public NullChessPiece(ChessBoard board) {
		super(ColorEnum.Empty, board);
	}

	@Override
	public boolean canMoveThisWay(Position newPosition) {
		return false;
	}
}
