package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

	public Pawn(ColorEnum color, ChessBoard board) {
		super(color, board);
	}

	public ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException {
		ArrayList<Position> result = new ArrayList<>();
		int rankDirection = -1;

		if (this.getColor() == ColorEnum.White) {
			rankDirection = 1;
		}

		Position tempPosition = new Position(this.position);

		// square infront of pawn
		tempPosition.rank = tempPosition.rank + (1 * rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

		// double move
		tempPosition = new Position(this.position);
		tempPosition.rank = tempPosition.rank + (2 * rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

		// right side
		tempPosition = new Position(this.position);
		tempPosition.file++;
		tempPosition.rank = tempPosition.rank + (1 * rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

		// left side
		tempPosition = new Position(this.position);
		tempPosition.file--;
		tempPosition.rank = tempPosition.rank + (1 * rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

		return result;
	}

	@Override
	public char getName() {
		if (this.getColor() == ColorEnum.Black) {
			return 'p';
		}
		return 'P';
	}

	@Override
	public boolean canMoveThisWay(Position newPosition) {

		int fileDelta = newPosition.file - this.position.file;
		int rankDelta = newPosition.rank - this.position.rank;

		// pawn always increases/decreases rank
		if (rankDelta == 0) {
			return false;
		}

		// check direction
		if (rankDelta > 0 && this.getColor() == ColorEnum.Black) {
			return false;
		}
		if (rankDelta < 0 && this.getColor() == ColorEnum.White) {
			return false;
		}
		// check file
		if (Math.abs(fileDelta) > 1) {
			return false;
		}
		// check rank
		if (Math.abs(rankDelta) > 2) {
			return false;
		}
		// only 2 squares at specific ranks
		// color is irrelevant, because it would be out of bounds or wrong direction
		if (Math.abs(rankDelta) == 2 && !((this.position.rank == 1) || (this.position.rank == 6))) {
			return false;
		}
		ChessPiece newPositionFigure = board.getChessPiece(newPosition);

		if (fileDelta == 0) {
			if (newPositionFigure.getColor() != ColorEnum.Empty) {
				return false;
			}
		} else {
			if (newPositionFigure.getColor() == ColorEnum.Empty) {
				return false;
			}
		}
		if (newPositionFigure instanceof EnPassentShadow == false && newPositionFigure.getColor() == this.getColor()) {
			return false;
		}

		if (!board.wayIsClear(position, newPosition)) {
			return false;
		}

		return true;
	}

}
