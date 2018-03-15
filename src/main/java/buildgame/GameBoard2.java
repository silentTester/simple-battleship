package buildgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameBoard2 {

    private final String COLUMNS = "ABCDEFG";
    private final int ROW_MAX_SIZE = 6;
    private final int COL_MAX_SIZE = 6;
    private String[][] gameBoard = new String[ROW_MAX_SIZE][COL_MAX_SIZE];

    public void setBattleshipOnTheGameboard(String shipLocation) throws Exception {
        String[] inputShip = shipLocation.trim().split(",");
        ArrayList<String> coOrdinates = new ArrayList<String>();

        for (String str : inputShip) {
            coOrdinates.add(str.trim());
        }

        Collections.sort(coOrdinates);

        if (isBattleshipInSequence(coOrdinates) && (isBattleshipCellUnoccupied(coOrdinates))) {
            for (int index = 0; index < coOrdinates.size(); index++) {
                String row = coOrdinates.get(index).substring(0, 1);
                int rowPos = COLUMNS.indexOf(row);
                int colPos = Integer.parseInt(coOrdinates.get(index).substring(1));
                gameBoard[rowPos][colPos] = coOrdinates.get(index);
            }
        } else
            throw new Exception("Error setting boat - either out of sequence or location is unavailable");
    }

    public String[][] getAllCellsOnTheGameBoard() {
        return gameBoard;
    }

    public String generateARandomCellForBattleship() {
        StringBuilder coOrdinates = new StringBuilder();

        Random rnd = new Random();
        int randomRowPos = rnd.nextInt(ROW_MAX_SIZE);
        int randomColPos = rnd.nextInt(COL_MAX_SIZE);
        String cellStartPos = "";

        for (int index = 0; index < 3; index++) {
            if (randomRowPos >= 4) {
                randomRowPos = randomRowPos - 2;
            } else
                randomRowPos++;

            cellStartPos = ((char) (randomColPos + 'A' - 0)) + "" + randomRowPos;
            coOrdinates.append(cellStartPos).append(",");
        }

        return coOrdinates.toString();
    }

    private boolean isBattleshipInSequence(ArrayList<String> inputShip) {
        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();

        for (int index = 0; index < inputShip.size(); index++) {
            String row = inputShip.get(index).substring(0, 1);
            int rowPos = COLUMNS.indexOf(row);
            int colPos = Integer.parseInt(inputShip.get(index).substring(1));
            rows.add(rowPos);
            cols.add(colPos);
        }

        for (int index = 0; index < rows.size() - 1; index++) {
            if (rows.get(index) != rows.get(index + 1)) {
                if (cols.get(index) + 1 == cols.get(index + 1))
                    return false;
            } else if (cols.get(index) + 1 != cols.get(index + 1))
                return false;
        }

        return true;
    }

    private boolean isBattleshipCellUnoccupied(ArrayList<String> inputShip) {
        for (String str : inputShip) {
            for (int row = 0; row < gameBoard.length; row++) {
                for (int col = 0; col < gameBoard.length; col++) {
                    if (str.equals(gameBoard[row][col])) {
                        System.out.println(str + " is occupied " + gameBoard[row][col]);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBattleshipLocatedInCell(String inputLocation) {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                if (inputLocation.equals(gameBoard[row][col])) {
                    return true;

                }
            }
        }
        return false;
    }

    public String[][] printGameBoard() {
        for (String[] cells : gameBoard) {
            for (String eachCell : cells) {
                System.out.format("|%-5s", eachCell);
            }
            System.out.println();
        }
        System.out.println();

        return gameBoard;
    }

    public int countNumberOfCellsOccupied() {
        int nosCells = 0;

        for (String[] cells : gameBoard) {
            for (String eachCell : cells) {
                if (eachCell != null) {
                    nosCells++;
                }
            }
        }

        return nosCells;
    }

    public int countNumberOfBattleships() {
        int nosCells = 0, nosShips = 0;

        for (String[] cells : gameBoard) {
            for (String eachCell : cells) {
                if (eachCell != null) {
                    nosCells++;
                    if (nosCells == 3) {
                        nosShips++;
                        nosCells = 0;
                    }
                }
            }
        }

        return nosShips;
    }

}