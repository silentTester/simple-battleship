package buildgame;

import org.junit.Test;

import static org.junit.Assert.*;


public class GameBoard2DTest {

    @Test
    public void gameBoardContainsSomeBattleships() throws Exception {

        String[][] expectedGameboardWithBattleships = new String[][]{
                {"A0", "A1", "A2", "A3", "A4", "A5"},
                {null, null, null, "B3", "B4", "B5"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
        };

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipOnTheGameboard("A1, A2, A3, A4, A5, A0");
        gameBoard.setBattleshipOnTheGameboard("B3, B4, B5");

        gameBoard.printGameBoard();

        assertArrayEquals(expectedGameboardWithBattleships, gameBoard.getAllCellsOnTheGameBoard());
        assertTrue(gameBoard.isBattleshipLocatedInCell("A5"));
        assertEquals(6, gameBoard.getAllCellsOnTheGameBoard().length);
        assertEquals(9, gameBoard.countNumberOfCellsOccupied());
        assertEquals(3, gameBoard.countNumberOfBattleships());
    }

    @Test
    public void gameBoardContainsSomeBattleshipsInConsecutiveOrder() throws Exception {

        String[][] expectedGameboardWithBattleships = new String[][]{
                {null, "A1", "A2", "A3", null, null},
                {"B0", "B1", "B2", null, null, null},
                {null, null, "C2", null, null, null},
                {null, "D1", "D2", null, null, null},
                {null, "E1", "E2", null, null, null},
                {null, "F1", null, null, null, null}
        };

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipOnTheGameboard("A1, A2, A3");
        gameBoard.setBattleshipOnTheGameboard("B2, B1, B0");
        gameBoard.setBattleshipOnTheGameboard("C2, D2, E2");
        gameBoard.setBattleshipOnTheGameboard("D1, E1, F1");

        gameBoard.printGameBoard();


        assertArrayEquals(expectedGameboardWithBattleships, gameBoard.getAllCellsOnTheGameBoard());
        assertTrue(gameBoard.isBattleshipLocatedInCell("A2"));
        assertEquals(6, gameBoard.getAllCellsOnTheGameBoard().length);
        assertEquals(12, gameBoard.countNumberOfCellsOccupied());
        assertEquals(4, gameBoard.countNumberOfBattleships());
    }

    @Test
    public void gameBoardContainsABattleshipInARandomLocation() throws Exception {
        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipOnTheGameboard(gameBoard.generateARandomCellForBattleship());
        gameBoard.printGameBoard();

        assertEquals(6, gameBoard.getAllCellsOnTheGameBoard().length);
        assertEquals(3, gameBoard.countNumberOfCellsOccupied());
        assertEquals(1, gameBoard.countNumberOfBattleships());
    }

}
