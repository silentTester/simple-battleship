package buildgame;

public class GameBoard {

    private int[] locationOfBattleship;

    public void setLocationOfBattleship(int[] shipLocation) {
        locationOfBattleship = shipLocation;
    }

    public int[] getLocationOfBattleship() {
        return locationOfBattleship;
    }

    public boolean isSequential(int[] locationOfBattleship) {
        int index = 0;

        while (index + 1 < locationOfBattleship.length) {
            if (locationOfBattleship[index] + 1 != locationOfBattleship[index + 1]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
