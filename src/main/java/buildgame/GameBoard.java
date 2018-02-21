package buildgame;

import java.util.Arrays;

public class GameBoard {

    private int[] locationOfBattleship;

    public void setBattleshipLocationCells(int[] shipLocation) throws BattleshipDuplicateLocationException, BattleshipNotInConsecutiveOrderException {

        if (isBattleshipInSequence(shipLocation)) {
            locationOfBattleship = shipLocation;
        } else if (isBattleshipLocationADuplicate(shipLocation)) {
            throw new BattleshipDuplicateLocationException("Duplicate error in setting battleship location");
        } else
            throw new BattleshipNotInConsecutiveOrderException("Not in order error in setting battleship location");
    }

    public int[] getBattleshipLocationCells() {
        return locationOfBattleship;
    }

    public boolean isBattleshipInSequence(int[] locationOfBattleship) {

        for (int index = 0; index < locationOfBattleship.length - 1; index++) {
            Arrays.sort(locationOfBattleship);
            if (locationOfBattleship[index] + 1 != locationOfBattleship[index + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBattleshipLocationADuplicate(int[] locationOfBattleship) {

        for (int index = 0; index < locationOfBattleship.length - 1; index++) {
            Arrays.sort(locationOfBattleship);
            if (locationOfBattleship[index] == locationOfBattleship[index + 1]) {
                return true;
            }
        }
        return false;
    }

}
