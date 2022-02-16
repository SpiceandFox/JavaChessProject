package chess;
public class WinException extends Exception {

    public WinException(String color) {
        super("Congratulations! Player "+color+" Wins");
    }

    public WinException() {
        super("Game is over with Remis.");
    }
    
}
