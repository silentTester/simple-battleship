package battleship.buildgame;

public class GameBoard {

    private int[] locationOfBattleship;
    private int[] abc;
    private int a;
    private int b;
    private int c;

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

//    public boolean isSequential(int[] sequentialLocationArray) {
//        abc = new int[]{1, 2, 3};
//        a = 0;
//        b = 0;
//        c = 0;
//
//        if (abc == (a = b - 1) && abc == (c = b + 1))
//            return true;
//        else
//            return false;
//    }
}
