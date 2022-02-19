package chess;
import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(ColorEnum color, ChessBoard board){
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
    protected boolean isLegalMove(Position newPosition) {
        if (!board.isInBounds(newPosition)) {
			return false;
		}

		ChessPiece newPositionFigure = this.board.getChessPiece(newPosition);
		
		if (newPositionFigure.getColor() == this.getColor())
		{
			return false;
		}

		if (Math.abs(this.position.rank - newPosition.rank) == Math.abs(this.position.file - newPosition.file) ) {
			return true;
		}

		return false;
    }

    @Override
    public char getName() {
        if (this.getColor() == ColorEnum.Black) {
			return 'b';
		}
		return 'B';
    }
    
}
