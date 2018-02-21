package usergame;

import buildgame.GameBoard;

import java.util.ArrayList;
import java.util.List;

public class UserGame {

    public List<String> checkYourself(String userInput) {
        List<String> listUserInput = new ArrayList<String>();

        for (String eachUserInput : userInput.split(",")) {
            listUserInput.add(eachUserInput);
        }

        return listUserInput;
    }


    public String firedMissiles(List<String> userInput, GameBoard gameBoard) {
        int numOfHits = 0;
        int numOfMiss = 0;
        String result;

        for (int battleshipLocaton : gameBoard.getBattleshipLocationCells()) {
            String partOfShipLocation = String.valueOf(battleshipLocaton);

            if (userInput.contains(partOfShipLocation)) {
                numOfHits++;
            } else {
                numOfMiss++;
            }
        }

        if (numOfHits == gameBoard.getBattleshipLocationCells().length) {
            result = "Killed all";
        } else
            result = "Hit " + numOfHits + " Missed " + numOfMiss;

        return result;
    }
    
}
