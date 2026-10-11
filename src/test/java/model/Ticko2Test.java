package model;

import static org.junit.jupiter.api.Assertions.*;

class Ticko2Test{
    Ticko2 to2 = new Ticko2();

    @org.junit.jupiter.api.Test
    void testCheckEndGame(){
        to2.newGame(); // resets the Ticko2 object
        to2.playCell(4); // player 1 plays the middle cell
        assertEquals(0, to2.checkEndGame()); // no row, column, or diagonal is completed, so no endgame
        to2.playCell(0); // player 2 plays the top left cell
        assertEquals(0, to2.checkEndGame()); // should have no endgame
        to2.playCell(8); // player 1 plays bottom right cell
        assertEquals(0, to2.checkEndGame()); // should have no endgame
        to2.playCell(4); // player 2 plays middle cell (completing it)
        assertEquals(0, to2.checkEndGame()); // should have no endgame
        to2.playCell(0); // player 1 plays top left cell (completing it)
        assertEquals(0, to2.checkEndGame()); // should have no endgame
        to2.playCell(8); // player 2 plays bottom right cell (completing it), completing the backward diagonal
        assertEquals(2, to2.checkEndGame()); // backwards diagonal is completed, player 2 should win endgame
    }

    @org.junit.jupiter.api.Test
    void testGetPlayerTurn(){

    }

    @org.junit.jupiter.api.Test
    void testGetCellState(){

    }

    @org.junit.jupiter.api.Test
    void testGetBoard(){

    }
}