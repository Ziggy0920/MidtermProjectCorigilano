/*
 * Jonas Corigliano - jbcorigliano@dmacc.edu
 * CIS171 Mon Afternoon
 * Date: 10/5/2026
 * Operating System: Windows 11
 * IDE: IntelliJ IDEA
 * Program Description(short): Using Java GUI, you can play a text-based Ticko 2 (Tic-Oat-Two) with 2 players
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or unmodified
 * Documentation of Resources Used:  (AI, websites, YouTube, peers, etc)
 */

package model;

public class Ticko2{
    char[] board;
    int player1Wins;
    int player2Wins;
    int player1Loses;
    int player2Loses;
    int lastMove;


    // Ticko2 - default constructor, sets everything to their default values
    // none -> Object
    public Ticko2(){
        board = new char[9];
        newGame();
        player1Wins = 0;
        player2Wins = 0;
        player1Loses = 0;
        player2Loses = 0;
    }

    // newGame - clears the board's cell states
    // none -> void
    public void newGame(){
        for(int i = 0; i < board.length; i++){
            board[i] = ' ';
        }
        lastMove = 0;
    }

    // getCellState - returns the char stored in the requested cell (0 - 8, left to right, top to bottom)
    // int -> char
    public char getCellState(int cell){
        return board[cell];
    }

    // getBoard - an alternative to the toString method below
    // none -> String
    public String getBoard(){
        return toString();
    }

    // toString - overrides the toString method and returns the board as a customized format multi-line String
    // none -> String
    public String toString(){
        return "board\narray\nhere"; // TODO: make this return the board as shown on the pre-approval doc
    }
}
