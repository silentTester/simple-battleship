package usergame;

import buildgame.GameBoard2;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserGameTest {

    @Test
    public void userGameInputsDirectHitAndKilledAll() throws Exception {
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
    public void userGameInputsAndTotallyMissedABattleship() throws Exception {
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
    public void userGameInputsAnIndividualCellHitsAndMissABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("D4, E4, F4");

        List<String> userInput = userGame.checkYourself("E4");
        assertEquals("Hit 1 Missed 0", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("F4");
        assertEquals("Hit 2 Missed 0", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("A5");
        assertEquals("Hit 2 Missed 1", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("F4");
        assertEquals("Killed all", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        assertEquals(4, userGame.numOfGuesses);
        assertEquals(3, userGame.numOfHits);
        assertEquals(1, userGame.numOfMisses);
    }

    @Test
    public void userGameInputsAnIndividualCellHitsAndKillsABattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("F1, F2, F3");

        List<String> userInput = userGame.checkYourself("F1");
        assertEquals("Hit 1 Missed 0", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("F2");
        assertEquals("Hit 2 Missed 0", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("F3");
        assertEquals("Killed all", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        assertEquals(3, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(0, userGame.numOfMisses);
    }

    @Test
    public void userGameInputsACharacterAndMissesBattleship() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("E4, F4, D4");

        List<String> userInput = userGame.checkYourself("F1");
        assertEquals("Hit 0 Missed 1", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("C1");
        assertEquals("Hit 0 Missed 2", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("B0");
        assertEquals("Hit 0 Missed 3", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        assertEquals(0, userGame.numOfHits);
        assertEquals(3, userGame.numOfGuesses);
        assertEquals(3, userGame.numOfMisses);
    }

    @Test
    public void userGameInputsAndHitsMultipleBattleshipsCreated() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();
        UserGame userGame = new UserGame();

        gameBoard.setBattleshipOnTheGameboard("E4, F4, D4");
        gameBoard.setBattleshipOnTheGameboard("C3, C4, C5");

        System.out.println(gameBoard.displayGameBoard());

        List<String> userInput = userGame.checkYourself("C3, D4, E4");
        assertEquals("Hit 3 Missed 0", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("C4, C5, A0");
        assertEquals("Hit 5 Missed 1", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("F5");
        assertEquals("Hit 5 Missed 2", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        userInput = userGame.checkYourself("F4");
        assertEquals("Killed all", userGame.seekAndFireAtBattleship(userInput, gameBoard));

        assertEquals(8, userGame.numOfGuesses);
        assertEquals(6, userGame.numOfHits);
        assertEquals(2, userGame.numOfMisses);
    }

}
