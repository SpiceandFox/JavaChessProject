package chess;

public class Position {
    protected int file;
    protected int rank;

    public Position(int rank, int file) {
        this.file = file;
        this.rank = rank;
    }

    public Position(int boardArrayIndex) {
        updateInternalValues(boardArrayIndex);
    }

    public Position(Position oldPosition) {
        file = oldPosition.file;
        rank = oldPosition.rank;
    }

    public Position() {
        file = 0;
        rank = 0;
    }

    public Position(String positionString) {
        char[] tempChars = positionString.toCharArray();
        this.rank = Character.getNumericValue(tempChars[1]);
        this.rank--;

        switch (tempChars[0]) {
            case 'a':
                this.file = 0;
                break;
            case 'b':
                this.file = 1;
                break;
            case 'c':
                this.file = 2;
                break;
            case 'd':
                this.file = 3;
                break;
            case 'e':
                this.file = 4;
                break;
            case 'f':
                this.file = 5;
                break;
            case 'g':
                this.file = 6;
                break;
            case 'h':
                this.file = 7;
                break;
            default:
                break;
        }
    }

    public int getBoardArrayIndex() {
        return (this.file + (8 * this.rank));
    }

    public void updateInternalValues(int boardArrayIndex) {
        this.file = boardArrayIndex % 8;
        this.rank = boardArrayIndex / 8;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position == false) {
            return false;
        }
        if ((this.rank == ((Position) obj).rank) && (this.file == ((Position) obj).file)) {
            return true;
        }
        return false;
    }
}
