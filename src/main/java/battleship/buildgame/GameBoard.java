package battleship.buildgame;

public class GameBoard {

    private int[] locationOfBattleship;

    public void setLocationOfBattleship(int[] shipLocation) {
        locationOfBattleship = shipLocation;
    }

    public int[] getLocationOfBattleship() {
        return locationOfBattleship;
    }
}
