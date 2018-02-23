package usergame;

import buildgame.GameBoard;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserGameTest {

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

    @Test
    public void userGameInputsACharacterHitsAndMissABattleship() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();
        int userInput;

        gameBoard.setBattleshipLocationCells(new int[]{9, 10, 11});

        userInput = userGame.guessesTarget(10);
        assertEquals("Hit", userGame.firesAMissile(userInput, gameBoard));

        userInput = userGame.guessesTarget(9);
        assertEquals("Hit", userGame.firesAMissile(userInput, gameBoard));

        userInput = userGame.guessesTarget(5);
        assertEquals("Miss", userGame.firesAMissile(userInput, gameBoard));
        assertEquals(2, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(1, userGame.numOfMisses);
    }

    @Test
    public void userGameInputsACharacterHitsAndKillsABattleship() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();
        int userInput;

        gameBoard.setBattleshipLocationCells(new int[]{5, 6, 7});

        userInput = userGame.guessesTarget(5);
        assertEquals("Hit", userGame.firesAMissile(userInput, gameBoard));

        userInput = userGame.guessesTarget(6);
        assertEquals("Hit", userGame.firesAMissile(userInput, gameBoard));

        userInput = userGame.guessesTarget(7);
        assertEquals("Killed all", userGame.firesAMissile(userInput, gameBoard));

        assertEquals(3, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(0, userGame.numOfMisses);
    }

    @Test
    public void userGameInputsACharacterAndMissesBattleship() throws Exception {
        GameBoard gameBoard = new GameBoard();
        UserGame userGame = new UserGame();
        int userInput;

        gameBoard.setBattleshipLocationCells(new int[]{2, 3, 4});

        userInput = userGame.guessesTarget(1);
        assertEquals("Miss", userGame.firesAMissile(userInput, gameBoard));

        userInput = userGame.guessesTarget(0);
        assertEquals("Miss", userGame.firesAMissile(userInput, gameBoard));

        userInput = userGame.guessesTarget(5);
        assertEquals("Miss", userGame.firesAMissile(userInput, gameBoard));
        assertEquals(0, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(3, userGame.numOfMisses);
    }

}
