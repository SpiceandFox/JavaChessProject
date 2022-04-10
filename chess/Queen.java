package chess;

import java.util.ArrayList;

public class Queen extends ChessPiece {
  public Queen(ColorEnum color, ChessBoard board) {
    super(color, board);
  }

  @Override
  public ArrayList<Position> getAllPossibleMoves() throws InvalidMoveException, GameStateException {
    ArrayList<Position> result = new ArrayList<>();
    result.addAll(this.getAllPositionsInLine(1, 1));
    result.addAll(this.getAllPositionsInLine(-1, -1));
    result.addAll(this.getAllPositionsInLine(-1, 1));
    result.addAll(this.getAllPositionsInLine(1, -1));
    result.addAll(this.getAllPositionsInLine(1, 0));
    result.addAll(this.getAllPositionsInLine(-1, 0));
    result.addAll(this.getAllPositionsInLine(0, 1));
    result.addAll(this.getAllPositionsInLine(0, -1));
    return result;
  }

  @Override
  public char getName() {
    if (this.getColor() == ColorEnum.Black) {
      return 'q';
    }
    return 'Q';
  }

  @Override
  public boolean canMoveThisWay(Position newPosition) {
    if (!board.wayIsClear(position, newPosition)) {
      return false;
    }
    return (((this.position.file == newPosition.file && this.position.rank != newPosition.rank)
        || (this.position.file != newPosition.file && this.position.rank == newPosition.rank))
        || (Math.abs(this.position.rank - newPosition.rank) == Math.abs(this.position.file - newPosition.file)));
  }

}
