package buildgame;

import org.junit.Test;

import java.util.Arrays;


public class GameBoard2DTest {

    @Test
    public void gameBoardContainsABattleshipInConsecutiveOrder() {

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipLocationCells2D("A1, A2, A3");

        System.out.println(Arrays.deepToString(gameBoard.getBattleshipLocation()));

        gameBoard.setBattleshipLocationCells2D("C2, C3, C4");

        System.out.println(Arrays.deepToString(gameBoard.getBattleshipLocation()));
    }
}
