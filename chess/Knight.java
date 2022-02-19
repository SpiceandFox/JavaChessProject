package chess;
import java.util.ArrayList;

public class Knight extends ChessPiece{

    public Knight(ColorEnum color, ChessBoard board){
        super(color, board);
    }
    @Override
    public ArrayList<Position> getAllPossibleMoves() {
        ArrayList<Position> result = new ArrayList<>();

        //clockwise order
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
    protected boolean isLegalMove(Position newPosition) {

        if (!board.isInBounds(newPosition)) {
            return false;
        }

        ChessPiece newPositionFigure = board.getChessPiece(newPosition);

        if (newPositionFigure.getColor() == this.getColor()) {
            return false;
        }

        if ((Math.abs(position.rank - newPosition.rank) == 1 && Math.abs(position.file - newPosition.file) == 2) 
        || (Math.abs(position.rank - newPosition.rank) == 2 && Math.abs(position.file - newPosition.file) == 1))
        {
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
