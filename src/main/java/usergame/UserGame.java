package usergame;

import buildgame.GameBoard2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserGame {
    int numOfHits = 0;
    int numOfGuesses = 0;
    int numOfMisses = 0;
    String result;

    public List<String> checkYourself(String userInput) {
        List<String> listUserInput = new ArrayList<String>();
        String[] inputShipLocation = userInput.trim().split(",");

        for (String eachUserInput : inputShipLocation) {
            listUserInput.add(eachUserInput.trim());
            Collections.sort(listUserInput);
        }

        return listUserInput;
    }

    public String guessesTarget(String userInput) {
        return userInput;
    }

    public String seekAndFireAtBattleship(List<String> userInput, GameBoard2 gameBoard) {
        for (String guess : userInput) {
            Boolean flag = false;
            for (String[] cells : gameBoard.getAllCellsOnTheGameBoard()) {
                for (String eachCell : cells) {
                    if (eachCell != null) {
                        if (guess.equals(eachCell)) {
                            numOfHits++;
                            numOfMisses = 0;
                            break;
                        } else if (flag == false) {
                            numOfMisses++;
                            flag = true;
                        }
                    }
                }
            }
        }

        numOfGuesses++;

        if (numOfHits == gameBoard.countNumberOfCellsOccupied()) {
            result = "Killed all";
        } else
            result = "Hit " + numOfHits + " Missed " + numOfMisses;

        return result;
    }

}
