package buildgame;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GameBoardTest {

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

    @Rule
    public ExpectedException expectedExceptionNotInOrder = ExpectedException.none();
    @Test
    public void gameBoardFailsAsBattleshipIsNotInConsecutiveOrder() throws Exception {
        expectedExceptionNotInOrder.expect(ExceptionBattleshipNotInConsecutiveOrder.class);
        expectedExceptionNotInOrder.expectMessage("Not in order error in setting battleship location");

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocation(new int[]{0, 1, 3});
    }

    @Rule
    public ExpectedException expectedExceptionDuplicate = ExpectedException.none();
    @Test
    public void gameBoardFailsAsBattleshipContainsDuplicateLocation() throws Exception {
        expectedExceptionDuplicate.expect(ExceptionBattleshipDuplicateLocation.class);
        expectedExceptionDuplicate.expectMessage("Duplicate error in setting battleship location");

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocation(new int[]{0, 1, 1});
    }

}