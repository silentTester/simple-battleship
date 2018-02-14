package battleship.buildgame;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class GameBoardTest {

    @Test
    public void gameBoardContainsThreeHits() {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setLocationOfBattleship(new int[] {1,2,3});

        assertArrayEquals(new int[]{1,2,3}, gameBoard.getLocationOfBattleship());
    }

    @Test
    public void gameBoardContainsThreeOtherHits() {

        GameBoard gameBoard = new GameBoard();

        gameBoard.setLocationOfBattleship(new int[] {0,1,2});

        assertArrayEquals(new int[]{0,1,2}, gameBoard.getLocationOfBattleship());
    }

//    @Test
//    public void hitsAreNotNonConsecutiveNumbers() {
//
//        GameBoard gameBoard = new GameBoard();
//
//        gameBoard.setLocationOfBattleship(new int[] {0,2,3});
//
//        assertTrue(gameBoard.isSequential(gameBoard.getLocationOfBattleship()));
//    }
}
