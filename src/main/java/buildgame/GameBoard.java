package buildgame;

import java.util.Arrays;

public class GameBoard {

    private int[] locationOfBattleship;

    public void setBattleshipLocation(int[] shipLocation) throws ExceptionBattleshipDuplicateLocation, ExceptionBattleshipNotInConsecutiveOrder {

        if (isBattleshipInSequence(shipLocation)) {
            locationOfBattleship = shipLocation;
        } else if (isBattleshipLocationADuplicate(shipLocation)) {
            throw new ExceptionBattleshipDuplicateLocation("Duplicate error in setting battleship location");
        } else
            throw new ExceptionBattleshipNotInConsecutiveOrder("Not in order error in setting battleship location");
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

    public boolean isBattleshipLocationADuplicate(int[] locationOfBattleship) {
        int index = 0;

        while (index + 1 < locationOfBattleship.length) {
            Arrays.sort(locationOfBattleship);
            if (locationOfBattleship[index] == locationOfBattleship[index + 1]) {
                return true;
            }
            index++;
        }
        return false;
    }

}
