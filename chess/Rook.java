package chess;
import java.util.ArrayList;

public class Rook extends ChessPiece {
	private boolean hasMoved = true;

	public Rook (ColorEnum color, ChessBoard board)
	{
		super(color, board);
	}

	public void setCastlingRights() {
		this.hasMoved = false;
	}

	@Override
	public ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException {
		ArrayList<Position> result = new ArrayList<>();
		result.addAll(this.getAllPositionsInLine(1, 0));
		result.addAll(this.getAllPositionsInLine(-1, 0));
		result.addAll(this.getAllPositionsInLine(0, 1));
		result.addAll(this.getAllPositionsInLine(0, -1));
		return result;
	}
	

	@Override
	protected boolean isLegalMove(Position newPosition) throws InvalidMoveException, GameStateException {
		if (!super.isLegalMove(newPosition)) {
			return false;
		}

		if (((this.position.file == newPosition.file)&&(this.position.rank != newPosition.rank))
			||((this.position.file != newPosition.file)&&(this.position.rank == newPosition.rank))) {
			return true;
		}

		return false;
	}


	@Override
	public char getName() {
		if (this.getColor() == ColorEnum.Black) {
			return 'r';
		}
		return 'R';
	}

	public boolean canCastle() {
		return !hasMoved;
	}

}
