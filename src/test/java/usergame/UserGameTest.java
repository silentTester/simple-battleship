package usergame;

import buildgame.GameBoard;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserGameTest {

    @Test
    public void userGameInputsKilledABattleship() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipLocationCells(new int[]{1, 2, 3});

        List<String> userInput = userGame.checkYourself("1,2,3");

        assertEquals("Killed all", userGame.firedMissiles(userInput, gameBoard));
    }

    @Test
    public void userGameInputsHitAndMissedABattleship() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipLocationCells(new int[]{11, 12, 13});

        List<String> userInput = userGame.checkYourself("11,0,13");

        assertEquals("Hit 2 Missed 1", userGame.firedMissiles(userInput, gameBoard));
    }

    @Test
    public void userGameInputsTotallyMissedABattleship() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipLocationCells(new int[]{0, 1, 2});

        List<String> userInput = userGame.checkYourself("11,10,13");

        assertEquals("Hit 0 Missed 3", userGame.firedMissiles(userInput, gameBoard));
    }

    @Test
    public void userGameInputsAndHitsABattleshipSetRandomly() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();
        int[] randomLocation = gameBoard.getARandomLocation();

        gameBoard.setBattleshipLocationCells(randomLocation);

        List<String> userInput = userGame.checkYourself(Arrays.toString(gameBoard.getBattleshipLocation()));

        assertEquals("Killed all", userGame.firedMissiles(userInput, gameBoard));
    }

}
