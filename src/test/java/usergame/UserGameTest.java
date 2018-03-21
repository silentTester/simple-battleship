package usergame;

import buildgame.GameBoard2;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserGameTest {

    @Test
    public void userGameInputsHitsABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("A1, A2, A3");

        List<String> userInput = userGame.checkYourself("A1, A2, A3");

        assertEquals("Killed all", userGame.seekAndFireAtBattleship(userInput, gameBoard));
    }

    @Test
    public void userGameInputsHitsAndMissesABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("A1, A2, A0");

        List<String> userInput = userGame.checkYourself("A1, A2, B0");

        assertEquals("Hit 2 Missed 1", userGame.seekAndFireAtBattleship(userInput, gameBoard));
    }

    @Test
    public void userGameInputsTotallyMissedABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("B1, B2, B3");

        List<String> userInput = userGame.checkYourself("C0, D0, E0");

        assertEquals("Hit 0 Missed 3", userGame.seekAndFireAtBattleship(userInput, gameBoard));
    }

    @Test
    public void userGameInputsAndHitsABattleshipSetRandomly() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();
        String randomCoOrdinates = gameBoard.generateARandomCellForBattleship();

        gameBoard.setBattleshipOnTheGameboard(randomCoOrdinates);

        System.out.println(gameBoard.displayGameBoard());

        List<String> userInput = userGame.checkYourself(randomCoOrdinates);

        assertEquals("Killed all", userGame.seekAndFireAtBattleship(userInput, gameBoard));
    }

    @Test
    public void userGameInputsACharacterHitsAndMissABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();
        String userInput;

        gameBoard.setBattleshipOnTheGameboard("D4, E4, F4");

        userInput = userGame.guessesTarget("E4");
        assertEquals("Hit 1 Missed 0", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        userInput = userGame.guessesTarget("F4");
        assertEquals("Hit 2 Missed 0", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        userInput = userGame.guessesTarget("A4");
        assertEquals("Hit 2 Missed 1", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        assertEquals(2, userGame.numOfHits);
        assertEquals(1, userGame.numOfMisses);
        assertEquals(3, userGame.numOfGuesses);
    }

    @Test
    public void userGameInputsACharacterHitsAndKillsABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();
        String userInput;

        gameBoard.setBattleshipOnTheGameboard("F1, F2, F3");

        userInput = userGame.guessesTarget("F1");
        assertEquals("Hit 1 Missed 0", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        userInput = userGame.guessesTarget("F2");
        assertEquals("Hit 2 Missed 0", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        userInput = userGame.guessesTarget("F3");
        assertEquals("Killed all", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        assertEquals(3, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(0, userGame.numOfMisses);
    }

    @Test
    public void userGameInputsACharacterAndMissesBattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();
        String userInput;

        gameBoard.setBattleshipOnTheGameboard("E4, F4, D4");

        userInput = userGame.guessesTarget("A1");
        assertEquals("Hit 0 Missed 1", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        userInput = userGame.guessesTarget("C2");
        assertEquals("Hit 0 Missed 2", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        userInput = userGame.guessesTarget("B0");
        assertEquals("Hit 0 Missed 3", userGame.seekAndFireAtBattleship(Collections.singletonList(userInput), gameBoard));

        assertEquals(0, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(3, userGame.numOfMisses);
    }

}
