package buildgame;

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
            if (locationOfBattleship[index] + 1 != locationOfBattleship[index + 1]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
