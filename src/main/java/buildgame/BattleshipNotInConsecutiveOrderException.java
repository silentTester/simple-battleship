package buildgame;

public class BattleshipNotInConsecutiveOrderException extends Exception {
    public BattleshipNotInConsecutiveOrderException(String errMessage) {
        super(errMessage);
    }
}
