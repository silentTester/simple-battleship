package buildgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameBoard2 {

    private final String COLUMNS = "ABCDEFG";
    private final int ROW_MAX_SIZE = 6;
    private final int COL_MAX_SIZE = 6;
    private final int SHIP_SIZE = 3;
    private String[][] gameBoard = new String[ROW_MAX_SIZE][COL_MAX_SIZE];

    public void setBattleshipOnTheGameboard(String shipLocation) throws Exception {
        String[] inputShip = shipLocation.trim().split(",");
        ArrayList<String> coOrdinates = new ArrayList<String>();

        for (String str : inputShip) {
            coOrdinates.add(str.trim());
        }

        Collections.sort(coOrdinates);

        if (isBattleshipInSequence(coOrdinates) && (isBattleshipCellUnoccupied(coOrdinates))) {
            assignBattleshipToTheGameboard(coOrdinates);
        } else
            throw new Exception("Error setting boat - either out of sequence or location is unavailable");
    }

    public String[][] getAllCellsOnTheGameBoard() {
        return gameBoard;
    }

    public String generateARandomCellForBattleshipHorizontally() {
        StringBuilder coOrdinates = new StringBuilder();

        Random rnd = new Random();
        int randomColPos = rnd.nextInt(COL_MAX_SIZE);
        int randomRowPos = rnd.nextInt(ROW_MAX_SIZE);
        String cellStartPos = "";

        for (int index = 0; index < SHIP_SIZE; index++) {
            if (randomRowPos >= 4) {
                randomRowPos = randomRowPos - 2;
            } else
                randomRowPos++;

            cellStartPos = ((char) (randomColPos + 'A' - 0)) + "" + randomRowPos;
            coOrdinates.append(cellStartPos).append(",");
        }

        return coOrdinates.toString();
    }

    public boolean isPartOfTheBattleshipInCell(String inputLocation) {
        for (String[] allCells : gameBoard) {
            for (String eachCell : allCells) {
                if (inputLocation.equals(eachCell)) {
                    return true;
                }
            }
        }

        return false;
    }

    public StringBuilder displayTheGameBoard() {
        StringBuilder gameBoardOutput = new StringBuilder();
        int counter = 0;

        for (String[] cells : gameBoard) {
            counter = displayColumnAxisName(gameBoardOutput, counter);
            printGameboardCells(gameBoardOutput, cells);
        }

        displayRowAxisName(gameBoardOutput);

        return gameBoardOutput;
    }

    public int countNumberOfCellsOccupied() {
        int nosCells = 0;

        for (String[] allCells : gameBoard) {
            for (String eachCell : allCells) {
                if (eachCell != null) {
                    nosCells++;
                }
            }
        }

        return nosCells;
    }

    public int countNumberOfBattleships() {
        int nosShips;

        nosShips = countNumberOfCellsOccupied() / SHIP_SIZE;

        return nosShips;
    }

    //common private methods
    private void assignBattleshipToTheGameboard(ArrayList<String> coOrdinates) {
        extractRowAndColPositions(coOrdinates);
    }

    private void extractRowAndColPositions(ArrayList<String> coOrdinates) {
        String row;
        int rowPos;
        int colPos;

        for (int index = 0; index < coOrdinates.size(); index++) {
            row = coOrdinates.get(index).substring(0, 1);
            rowPos = COLUMNS.indexOf(row);
            colPos = Integer.parseInt(coOrdinates.get(index).substring(1));

            gameBoard[rowPos][colPos] = coOrdinates.get(index);
        }
    }

    private boolean isBattleshipInSequence(ArrayList<String> inputShip) {
        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();

        extractAndStoreColumnRowValues(inputShip, cols, rows);

        for (int index = 0; index < rows.size(); index++) {
            if (index < rows.size() - 1) {
                //check values of cells if they can be stored in sequence
                if (isConsequentiveCellsInSequence(cols, rows, index)) return false;
            } //check final cell against the first & previous cell.
            else {
                if (isFinalCellInSequence(cols, rows, index)) return false;
            }
        }

        return true;
    }

    private boolean isConsequentiveCellsInSequence(ArrayList<Integer> cols, ArrayList<Integer> rows, int index) {
        //if battleship cell is assigned in different rows e.g. A1, B1
        if (rows.get(index) != rows.get(index + 1)) {
            //expected column values to be identical, i.e. 1 from example above.
            if (cols.get(index) != cols.get(index + 1)) {
                return true;
            }
            //if battleship cell is assigned in the same rows e.g. A1, A2
        } else if (rows.get(index) == rows.get(index + 1)) {
            //expect cols to be different, i.e 1 & 2.
            if (cols.get(index) == cols.get(index + 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean isFinalCellInSequence(ArrayList<Integer> cols, ArrayList<Integer> rows, int index) {
        //if battleship final cell is assigned on a different row, e.g. A1, B1, C1
        if (rows.get(index) != rows.get(0)) {
            //expected final column value to be identical to the first, i.e. 1 from example above.
            if (cols.get(index) != cols.get(0)) {
                return true;
            }
            //if battleship final cell is assigned in the same rows e.g. A1, A2, A3
        } else if (rows.get(index) == rows.get(0)) {
            //checks previous column value to be in seq.
            if (cols.get(index) - 1 != cols.get(index - 1)) {
                return true;
            }
        }

        return false;
    }

    private void extractAndStoreColumnRowValues(ArrayList<String> inputShip, ArrayList<Integer> cols, ArrayList<Integer> rows) {
        storeRowsAndCols(inputShip, cols, rows);
    }

    private void storeRowsAndCols(ArrayList<String> inputShip, ArrayList<Integer> cols, ArrayList<Integer> rows) {
        for (int index = 0; index < inputShip.size(); index++) {
            String row = inputShip.get(index).substring(0, 1);
            int rowPos = COLUMNS.indexOf(row);
            int colPos = Integer.parseInt(inputShip.get(index).substring(1));

            rows.add(rowPos);
            cols.add(colPos);
        }
    }

    private boolean isBattleshipCellUnoccupied(ArrayList<String> shipLocation) {
        for (String shipInput : shipLocation) {
            for (String[] allCells : gameBoard) {
                for (String eachCell : allCells) {
                    if (shipInput.equals(eachCell)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void displayRowAxisName(StringBuilder gameBoardOutput) {
        removeCarriageReturn(gameBoardOutput, "\n");
        gameBoardOutput.append(" ");

        for (int index = 0; index < ROW_MAX_SIZE; index++) {
            gameBoardOutput.append(String.format(" %2s", index));
        }
    }

    private int displayColumnAxisName(StringBuilder gameBoardOutput, int counter) {
        char colAxis = (char) ('A' + counter);

        gameBoardOutput.append(colAxis + " {");
        counter++;

        return counter;
    }

    private void printGameboardCells(StringBuilder gameBoardOutput, String[] cells) {
        for (String eachCell : cells) {
            if (eachCell != null) {
                gameBoardOutput.append("x, ");
            } else
                gameBoardOutput.append("o, ");
        }

        removeCarriageReturn(gameBoardOutput, "},\n");
    }

    private void removeCarriageReturn(StringBuilder gameBoardOutput, String s) {
        gameBoardOutput.setLength(gameBoardOutput.length() - 2);
        gameBoardOutput.append(s);
    }

}