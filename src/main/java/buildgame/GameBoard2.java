package buildgame;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard2 {

    private final int ROW_MAX_SIZE = 6;
    private final int COL_MAX_SIZE = 6;
    private String[][] locationOfBattleship = new String[ROW_MAX_SIZE][COL_MAX_SIZE];

    public void setBattleshipLocationCells2D(String shipLocation) {
        String[] inputShip = shipLocation.split(",");
        ArrayList<String> coOrdinates = new ArrayList<String>();

        for (String str : inputShip) {
            coOrdinates.add(str.trim());
        }

        if (!isShipInSequence(inputShip)) {
            System.out.println("Error!");
        };

        for (int index = 0; index < coOrdinates.size(); index++) {
            String row = coOrdinates.get(index).substring(0, 1);
            int rowPos = "ABCDEFG".indexOf(row);
            int colPos = Integer.parseInt(coOrdinates.get(index).substring(1));
            locationOfBattleship[rowPos][colPos] = coOrdinates.get(index);

            System.out.println(rowPos + "," + colPos + ":" + locationOfBattleship[rowPos][colPos]);
        }
    }

    private boolean isShipInSequence(String[] inputShip) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        Collections.sort(numbers);

        for (int index = 0; index < inputShip.length; index++) {
            String row = inputShip[index].trim().substring(0, 1);
            int rowPos = "ABCDEFG".indexOf(row);
            int colPos = Integer.parseInt(inputShip[index].trim().substring(1));
            int position = rowPos + colPos;
            numbers.add(position);
        }

        for (int index = 0; index < numbers.size() - 1; index++) {
            if (numbers.get(index) + 1 != numbers.get(index + 1)) {
                return false;
            }
        }

        return true;
    }

    public String[][] printBattleshipLocation() {
        for (String[] a : locationOfBattleship) {
            for (String str : a) {
                System.out.println(str + "\t");
            }
            System.out.println("\n");

        }
        return locationOfBattleship;
    }

    public String[][] getBattleshipLocation() {

        return locationOfBattleship;
    }
}


