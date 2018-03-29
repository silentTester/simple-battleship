package buildgame;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

        System.out.println(gameBoard.displayTheGameBoard());

        assertArrayEquals(expectedGameboardWithBattleships, gameBoard.getAllCellsOnTheGameBoard());
        assertTrue(gameBoard.isPartOfTheBattleshipInCell("A5"));
        assertTrue(gameBoard.isPartOfTheBattleshipInCell("B5"));
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

        System.out.println(gameBoard.displayTheGameBoard());

        assertArrayEquals(expectedGameboardWithBattleships, gameBoard.getAllCellsOnTheGameBoard());
        assertTrue(gameBoard.isPartOfTheBattleshipInCell("A2"));
        assertEquals(6, gameBoard.getAllCellsOnTheGameBoard().length);
        assertEquals(12, gameBoard.countNumberOfCellsOccupied());
        assertEquals(4, gameBoard.countNumberOfBattleships());
    }

    @Test
    public void gameBoardContainsABattleshipInARandomLocationHorizontally() throws Exception {
        String randomCoOrdinates;
        GameBoard2 gameBoard = new GameBoard2();

        randomCoOrdinates = gameBoard.generateARandomCellForBattleship('H');
        gameBoard.setBattleshipOnTheGameboard(randomCoOrdinates);

        System.out.println(gameBoard.displayTheGameBoard());

        assertEquals(6, gameBoard.getAllCellsOnTheGameBoard().length);
        assertEquals(3, gameBoard.countNumberOfCellsOccupied());
        assertEquals(1, gameBoard.countNumberOfBattleships());
    }

    @Test
    public void gameBoardContainsABattleshipInARandomLocationVertically() throws Exception {
        String randomCoOrdinates;
        GameBoard2 gameBoard = new GameBoard2();

        randomCoOrdinates = gameBoard.generateARandomCellForBattleship('V');
        gameBoard.setBattleshipOnTheGameboard(randomCoOrdinates);

        System.out.println(gameBoard.displayTheGameBoard());

        assertEquals(6, gameBoard.getAllCellsOnTheGameBoard().length);
        assertEquals(3, gameBoard.countNumberOfCellsOccupied());
        assertEquals(1, gameBoard.countNumberOfBattleships());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void gameBoardFailsAsBattleshipNotSetInSequenceVertically() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Error setting boat - either out of sequence or location is unavailable");

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipOnTheGameboard("A1, B1, F2");
    }

    @Test
    public void gameBoardFailsAsBattleshipNotSetInSequenceHorizontally() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Error setting boat - either out of sequence or location is unavailable");

        GameBoard2 gameBoard = new GameBoard2();

        gameBoard.setBattleshipOnTheGameboard("B0, B1, B3");
    }

    @Test
    public void gameBoardFailsAsBattleshipLocationIsUnavailable() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Error setting boat - either out of sequence or location is unavailable");

        GameBoard2 gameBoard = new GameBoard2();
        gameBoard.setBattleshipOnTheGameboard("C3, D3, E3");

        gameBoard.setBattleshipOnTheGameboard("C1, C2, C3");
    }

}
