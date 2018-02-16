package battleship.buildgame;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void gameBoardContainsABattleshipInConsecutiveOrder() {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocation(new int[]{1, 2, 3});

        assertArrayEquals(new int[]{1, 2, 3}, gameBoard.getBattleshipLocation());
        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertTrue(gameBoard.isBattleshipInSequence(gameBoard.getBattleshipLocation()));
    }

    @Test
    public void gameBoardContainsAnotherBattleship() {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocation(new int[]{0, 1, 2});

        assertArrayEquals(new int[]{0, 1, 2}, gameBoard.getBattleshipLocation());
        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertTrue(gameBoard.isBattleshipInSequence(gameBoard.getBattleshipLocation()));
    }

    @Test
    public void gameBoardFailsAsBattleshipIsNotInConsecutiveOrder() throws RuntimeException {

        GameBoard gameBoard = new GameBoard();
        try {
            gameBoard.setBattleshipLocation(new int[]{0, 1, 3});
            fail();
        } catch (RuntimeException e) {
            assertEquals("error in setting battleship location", e.getMessage());
        }
    }

    @Test
    public void gameBoardFailsAsBattleshipContainsDuplicateLocation() throws RuntimeException {

        GameBoard gameBoard = new GameBoard();
        try {
            gameBoard.setBattleshipLocation(new int[]{0, 1, 1});
            fail();
        } catch (RuntimeException e) {
            assertEquals("error in setting battleship location", e.getMessage());
        }
    }

}
