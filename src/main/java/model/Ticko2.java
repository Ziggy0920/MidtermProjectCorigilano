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

    // playCell - takes a 1 or 2 (to know which player is making the move) and the cell
    //            position (0 - 8, left to right, top to bottom), and changes that part of the board accordingly,
    //            then throws an exception if the cell was an invalid play (due to an invalid input)
    // int, int -> void
    public void playCell(int cell, int player) throws IndexOutOfBoundsException, IllegalStateException{
        if(cell < 0 || cell > 8){ // checks if the int cell input is outside the range
            throw new IndexOutOfBoundsException("Invalid cell"); // throws out of range exception
        } else if(player != 1 && player != 2){ // checks if the player input is not 1 or 2
            throw new IndexOutOfBoundsException("Invalid player"); // throws out of range exception
        }

        // If the inputs are valid...
        // check for valid play

        if(cell == lastMove){ // checks if the play is invalid (the last move wasn't in the same spot)
            // throws illegal (cell) state exception
            throw new IllegalStateException("Cannot play in a cell played in the previous round");
        } else if(player == 1 && board[cell] == '|'){ // checks if player 1 is playing in a cell they already used
            // throws illegal (cell) state exception
            throw new IllegalStateException("Player 1 has already played in this cell");
        } else if(player == 2 && board[cell] == '-'){ // checks if player 2 is playing in a cell they already used
            // throws illegal (cell) state exception
            throw new IllegalStateException("Player 2 has already played in this cell");
        } else if(board[cell] == '+'){ // checks if he player is trying to play in a completed cell
            // throws illegal (cell) state exception
            throw new IllegalStateException("This cell is already completed");
        }

        // Else, since valid play...
        // If either player played a cell correctly, then it will be set to the cell number (0-8).
        lastMove = cell;
        if(player == 1){
            if(board[cell] == ' '){
                board[cell] = '|';
            } else if(board[cell] == '-'){
                board[cell] = '+';
            }
        } else{
            if(board[cell] == ' '){
                board[cell] = '-';
            } else if(board[cell] == '|'){
                board[cell] = '+';
            }
        }
    }

    // checkEndGame - checks if the game is completed. If not, returns a 0. If so, it returns a 1 if player 1 won,
    //                and a 2 if player 2 won
    // none -> int
    public int checkEndGame(){
        if(/*board is done*/ false){// FIXME: make this check if the board is complete, delete the temp condition
            if(/*player 1 won*/ false){// FIXME: make this check if P1 won, and delete the temp false condition
                return 1;
            } else{
                return 2;
            }
        } else{
            return 0;
        }
    }

    // getPlayer1Stats - returns an array of two values, player 1's wins at [0], and the loses at [1]
    // none -> int[]
    public int[] getPlayer1Stats(){
        int[] stats = new int[2];
        stats[0] = player1Wins;
        stats[1] = player1Loses;
        return stats;
    }

    // getPlayer2Stats - returns an array of two values, player 2's wins at [0], and the loses at [1]
    // none -> int[]
    public int[] getPlayer2Stats(){
        int[] stats = new int[2];
        stats[0] = player2Wins;
        stats[1] = player2Loses;
        return stats;
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
