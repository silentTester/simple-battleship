package usergame;

import buildgame.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class UserGame {
    int numOfHits = 0;
    int numOfGuesses = 0;
    int numOfMisses = 0;

    public List<String> checkYourself(String userInput) {
        List<String> listUserInput = new ArrayList<String>();
        String filtered = userInput.replaceAll("[^0-9,]", "");

        for (String eachUserInput : filtered.split(",")) {
            listUserInput.add(eachUserInput);
        }

        return listUserInput;
    }

    public String firedMissiles(List<String> userInput, GameBoard gameBoard) {
        int numOfHits = 0;
        int numOfMiss = 0;
        String result;

        for (int battleshipLocaton : gameBoard.getBattleshipLocation()) {
            String partOfShipLocation = String.valueOf(battleshipLocaton);

            if (userInput.contains(partOfShipLocation)) {
                numOfHits++;
            } else {
                numOfMiss++;
            }
        }

        if (numOfHits == gameBoard.getBattleshipLocation().length) {
            result = "Killed all";
        } else
            result = "Hit " + numOfHits + " Missed " + numOfMiss;

        return result;
    }

    public int guessesTarget(int input) {

        return input;
    }

    public String firesAMissile(int userInput, GameBoard gameBoard) {
        String result = "Miss";

        for (int battleshipLocaton : gameBoard.getBattleshipLocation()) {
            if (userInput == battleshipLocaton) {
                numOfHits++;
                result = "Hit";
                break;
            } else {
                result = "Miss";
            }
        }

        if (numOfHits == gameBoard.getBattleshipLocation().length) {
            result = "Killed all";
        }
        numOfGuesses++;

        if (result.equals("Miss")) {
            numOfMisses++;
        }

        return result;
    }

}
