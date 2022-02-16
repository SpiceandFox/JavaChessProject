package chess;
import java.util.ArrayList;

public class Pawn extends ChessPiece{

	public Pawn (ColorEnum color, ChessBoard board)
	{
		super(color, board);
	}

    public ArrayList<Position> getAllPossibleMoves(){
        ArrayList<Position> result = new ArrayList<>();
        int rankDirection = 1;

		if (this.getColor() == ColorEnum.White) {
			rankDirection = -1;	
		}

		Position tempPosition = new Position(this.position);

		// square infront of pawn
		tempPosition.rank = tempPosition.rank + (1*rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

		// double move
		tempPosition = new Position(this.position);
		tempPosition.rank = tempPosition.rank + (2*rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

		// right side
		tempPosition = new Position(this.position);
		tempPosition.file++;
		tempPosition.rank = tempPosition.rank + (1*rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}
		

		//left side
		tempPosition = new Position(this.position);
		tempPosition.file--;
		tempPosition.rank = tempPosition.rank + (1*rankDirection);
		if (isLegalMove(tempPosition)) {
			result.add(tempPosition);
		}

        return result;
    }

	@Override
	protected boolean isLegalMove(Position newPosition) {

		//todo finish


		if (!board.isInBounds(newPosition)) {
			return false;
		}

		ChessPiece newPositionFigure = board.getChessPiece(newPosition);

		if (newPositionFigure instanceof EnPassentShadow == false && newPositionFigure.getColor() == this.getColor() ) 
		{
			return false;
		}



		return true;
	}

	@Override
	public char getName() {
		if (this.getColor() == ColorEnum.Black) {
			return 'p';
		}
		return 'P';
	}

}
