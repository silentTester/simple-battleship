package buildgame;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private final int MAX_SIZE = 3;
    private int[] locationOfBattleship = new int[MAX_SIZE];

    public void setBattleshipLocationCells(int[] shipLocation) throws BattleshipDuplicateLocationException, BattleshipNotInConsecutiveOrderException {
        Arrays.sort(shipLocation);

        if (isBattleshipInSequence(shipLocation)) {
            for (int index = 0; index < MAX_SIZE; index++) {
                locationOfBattleship[index] = shipLocation[index];
            }
        } else if (isBattleshipLocationADuplicate(shipLocation)) {
            throw new BattleshipDuplicateLocationException("Duplicate error in setting battleship location");
        } else
            throw new BattleshipNotInConsecutiveOrderException("Not in order error in setting battleship location");
    }

    public int[] getBattleshipLocation() {
        return locationOfBattleship;
    }

    public int[] getARandomLocation() {
        Random rnd = new Random();

        int randomNumber = rnd.nextInt(7);

        int[] randomShipLocation = new int[MAX_SIZE];

        if (randomNumber > 5) {
            for (int index = 0; index < MAX_SIZE; index++) {
                randomShipLocation[MAX_SIZE - index - 1] = randomNumber;
                randomNumber -= 1;
            }
        } else {
            for (int index = 0; index < MAX_SIZE; index++) {
                randomShipLocation[index] = randomNumber;
                randomNumber += 1;
            }
        }

        return randomShipLocation;
    }

    private boolean isBattleshipInSequence(int[] locationOfBattleship) {
        for (int index = 0; index < locationOfBattleship.length - 1; index++) {
            if (locationOfBattleship[index] + 1 != locationOfBattleship[index + 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isBattleshipLocationADuplicate(int[] locationOfBattleship) {
        for (int index = 0; index < locationOfBattleship.length - 1; index++) {
            if (locationOfBattleship[index] == locationOfBattleship[index + 1]) {
                return true;
            }
        }
        return false;
    }

}
