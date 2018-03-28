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
            assignBattleshipToTheGameboard(coOrdinates);
        } else
            throw new Exception("Error setting boat - either out of sequence or location is unavailable");
    }

    private void assignBattleshipToTheGameboard(ArrayList<String> coOrdinates) {
        for (int index = 0; index < coOrdinates.size(); index++) {
            String row = coOrdinates.get(index).substring(0, 1);
            int rowPos = COLUMNS.indexOf(row);
            int colPos = Integer.parseInt(coOrdinates.get(index).substring(1));
            gameBoard[rowPos][colPos] = coOrdinates.get(index);
        }
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

        for (int index = 0; index < rows.size(); index++) {
            //check rows - if different rows e.g. A1, B1
            if (index < rows.size() - 1) {
                if (rows.get(index) != rows.get(index + 1)) {
                    //expect cols to be the same
                    if (cols.get(index) != cols.get(index + 1)) {
                        return false;
                    }
                    //check rows - if the same e.g. A1, A2
                } else if (rows.get(index) == rows.get(index + 1)) {
                    //expect cols to be different
                    if (cols.get(index) == cols.get(index + 1)) {
                        return false;
                    }
                }
            } //check final cell against the first & previous cell.
            else {
                //final cell on a different row, e.g. A1, B1
                if (rows.get(index) != rows.get(0)) {
                    //expect cols to be the same
                    if (cols.get(index) != cols.get(0)) {
                        return false;
                    }
                    //final cell on the same row, e.g. A1, A3
                } else if (rows.get(index) == rows.get(0)) {
                    //expect final cell value to be identical to the previous cell value
                    if (cols.get(index) - 1 != cols.get(index - 1)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean isBattleshipCellUnoccupied(ArrayList<String> shipLocation) {
        for (String shipInput : shipLocation) {
            for (int row = 0; row < gameBoard.length; row++) {
                for (int col = 0; col < gameBoard.length; col++) {
                    if (shipInput.equals(gameBoard[row][col])) {
                        System.out.println(shipInput + " is occupied " + gameBoard[row][col]);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isBattleshipLocatedInCell(String inputLocation) {
        for (int row = 0; row < getAllCellsOnTheGameBoard().length; row++) {
            for (int col = 0; col < getAllCellsOnTheGameBoard().length; col++) {
                if (inputLocation.equals(getAllCellsOnTheGameBoard()[row][col])) {
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

        removeCarriageReturn(gameBoardOutput, "\n");
        displayRowAxisName(gameBoardOutput);

        return gameBoardOutput;
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

    //common private methods
    private void displayRowAxisName(StringBuilder gameBoardOutput) {
        gameBoardOutput.append("   ");

        for (int index = 0; index < ROW_MAX_SIZE; index++) {
            gameBoardOutput.append(String.format("%s  ", index));
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