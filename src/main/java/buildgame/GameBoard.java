package buildgame;

import java.util.Arrays;

public class GameBoard {

    private int[] locationOfBattleship;

    public void setBattleshipLocation(int[] shipLocation) throws RuntimeException {

        if (isBattleshipInSequence(shipLocation))
            locationOfBattleship = shipLocation;
        else
            throw new RuntimeException("error in setting battleship location");
    }

    public int[] getBattleshipLocation() {
        return locationOfBattleship;
    }

    public boolean isBattleshipInSequence(int[] locationOfBattleship) {
        int index = 0;

        while (index + 1 < locationOfBattleship.length) {
            Arrays.sort(locationOfBattleship);
            if (locationOfBattleship[index] + 1 != locationOfBattleship[index + 1]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
