package buildgame;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GameBoardTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void gameBoardContainsABattleshipInConsecutiveOrder() throws ExceptionBattleshipDuplicateLocation, ExceptionBattleshipNotInConsecutiveOrder {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocation(new int[]{1, 2, 3});

        assertArrayEquals(new int[]{1, 2, 3}, gameBoard.getBattleshipLocation());
        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertTrue(gameBoard.isBattleshipInSequence(gameBoard.getBattleshipLocation()));
    }

    @Test
    public void gameBoardContainsAnotherBattleshipUnSorted() throws ExceptionBattleshipDuplicateLocation, ExceptionBattleshipNotInConsecutiveOrder {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocation(new int[]{1, 0, 2});

        assertArrayEquals(new int[]{0, 1, 2}, gameBoard.getBattleshipLocation());
        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertTrue(gameBoard.isBattleshipInSequence(gameBoard.getBattleshipLocation()));
    }

    @Test
    public void gameBoardFailsAsBattleshipIsNotInConsecutiveOrder() throws Exception {
        expectedException.expect(ExceptionBattleshipNotInConsecutiveOrder.class);
        expectedException.expectMessage("Not in order error in setting battleship location");
        GameBoard gameBoard = new GameBoard();
        gameBoard.setBattleshipLocation(new int[]{0, 1, 3});
    }

    @Test
    public void gameBoardFailsAsBattleshipContainsDuplicateLocation() throws Exception {
        expectedException.expect(ExceptionBattleshipDuplicateLocation.class);
        expectedException.expectMessage("Duplicate error in setting battleship location");

        GameBoard gameBoard = new GameBoard();
        gameBoard.setBattleshipLocation(new int[]{0, 1, 1});
    }

}