package buildgame;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void gameBoardContainsABattleshipInConsecutiveOrder() throws BattleshipDuplicateLocationException, BattleshipNotInConsecutiveOrderException {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocationCells(new int[]{1, 2, 3});

        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertArrayEquals(new int[]{1, 2, 3}, gameBoard.getBattleshipLocation());
    }

    @Test
    public void gameBoardContainsAnotherBattleshipUnSorted() throws BattleshipDuplicateLocationException, BattleshipNotInConsecutiveOrderException {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocationCells(new int[]{1, 0, 2});

        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertArrayEquals(new int[]{0, 1, 2}, gameBoard.getBattleshipLocation());
    }

    @Test
    public void gameBoardContainsABattleshipInARandomLocation() throws BattleshipDuplicateLocationException, BattleshipNotInConsecutiveOrderException {

        GameBoard gameBoard = new GameBoard();

        int[] randomLocation = gameBoard.getARandomLocation();

        gameBoard.setBattleshipLocationCells(randomLocation);

        assertEquals(3, gameBoard.getBattleshipLocation().length);
        assertArrayEquals(randomLocation, gameBoard.getBattleshipLocation());
    }

    @Rule
    public ExpectedException expectedExceptionNotInOrder = ExpectedException.none();

    @Test
    public void gameBoardFailsAsBattleshipIsNotInConsecutiveOrder() throws Exception {
        expectedExceptionNotInOrder.expect(BattleshipNotInConsecutiveOrderException.class);
        expectedExceptionNotInOrder.expectMessage("Not in order error in setting battleship location");

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocationCells(new int[]{0, 1, 3});
    }

    @Rule
    public ExpectedException expectedExceptionDuplicate = ExpectedException.none();

    @Test
    public void gameBoardFailsAsBattleshipContainsDuplicateLocation() throws Exception {
        expectedExceptionDuplicate.expect(BattleshipDuplicateLocationException.class);
        expectedExceptionDuplicate.expectMessage("Duplicate error in setting battleship location");

        GameBoard gameBoard = new GameBoard();

        gameBoard.setBattleshipLocationCells(new int[]{0, 1, 1});
    }

}