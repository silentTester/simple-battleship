package usergame;

import buildgame.GameBoard2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserGame {
    public int numOfHits = 0;
    public int numOfGuesses = 0;
    public int numOfMisses = 0;
    private String result;
    private List<String> battleShipLocationCells = new ArrayList<String>();

    public List<String> checkYourself(String userInput) {
        List<String> listUserInput = new ArrayList<String>();
        String[] inputShipLocation = userInput.trim().split(",");

        for (String eachUserInput : inputShipLocation) {
            listUserInput.add(eachUserInput.trim());
            Collections.sort(listUserInput);
        }

        return listUserInput;
    }

    public String seekAndFireAtBattleship(List<String> userInputGuesses, GameBoard2 gameBoard) {
        retrieveGameBoardAndExtractBattleShips(gameBoard);

        for (String userInput : userInputGuesses) {
            findBattleShipAndKeepCountOfShots(userInput, battleShipLocationCells);
        }

        if (numOfHits == gameBoard.countNumberOfCellsOccupied()) {
            result = "Killed all";
        } else
            result = "Hit " + numOfHits + " Missed " + numOfMisses;

        return result;
    }

    private void retrieveGameBoardAndExtractBattleShips(GameBoard2 gameBoard) {
        for (String[] gameBoardCells : gameBoard.getAllCellsOnTheGameBoard()) {
            for (String cellContainingBattleShip : gameBoardCells) {
                if (cellContainingBattleShip != null) {
                    battleShipLocationCells.add(cellContainingBattleShip);
                }
            }
        }
    }

    private void findBattleShipAndKeepCountOfShots(String userInput, List<String> cellsOccupied) {
        if (cellsOccupied.contains(userInput)) {
            numOfHits++;
        } else
            numOfMisses++;

        numOfGuesses++;
    }

}
