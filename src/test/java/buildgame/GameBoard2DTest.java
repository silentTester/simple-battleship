package buildgame;

import org.junit.Test;

import java.util.Arrays;


public class GameBoard2DTest {

    @Test
    public void gameBoardContainsABattleshipInConsecutiveOrder() throws Exception{

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipLocationCells2D("A1, A2, A3");
        gameBoard.setBattleshipLocationCells2D("C2, D2, E2");
        gameBoard.setBattleshipLocationCells2D("D1, E1, F1");
        gameBoard.setBattleshipLocationCells2D("B2, B1, B0");

        System.out.println(Arrays.deepToString(gameBoard.getAllBattleships()));
    }
}
