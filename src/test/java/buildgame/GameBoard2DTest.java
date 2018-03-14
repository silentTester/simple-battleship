package buildgame;

import org.junit.Test;

import static org.junit.Assert.*;


public class GameBoard2DTest {

    @Test
    public void gameBoardContainsABattleshipInConsecutiveOrder() throws Exception{

        String [][] expected = new String[][] {{null, "A1", "A2", "A3", null, null}, {"B0", "B1", "B2", null, null, null}, {null, null, "C2", null, null, null}, {null, "D1", "D2", null, null, null}, {null, "E1", "E2", null, null, null}, {null, "F1", null, null, null, null}};

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipLocationCells2D("A1, A2, A3");
        gameBoard.setBattleshipLocationCells2D("B2, B1, B0");
        gameBoard.setBattleshipLocationCells2D("C2, D2, E2");
        gameBoard.setBattleshipLocationCells2D("D1, E1, F1");

//        System.out.println(Arrays.deepToString(gameBoard.getAllBattleships()));
        gameBoard.printGameBoard();

        assertEquals(6, gameBoard.getAllBattleships().length);
        assertTrue(gameBoard.isBattleshipLocatedInCell("A2"));
        assertArrayEquals(expected, gameBoard.getAllBattleships());
    }

}
