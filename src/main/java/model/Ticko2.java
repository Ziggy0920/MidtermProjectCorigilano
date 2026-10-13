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
    private char[] board;
    private int player1Wins;
    private int player2Wins;
    private int player1Loses;
    private int player2Loses;
    private int lastMove;
    private int playerTurn;

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
        lastMove = -1;
        playerTurn = 1;
    }

    // playCell - takes a 1 or 2 (to know which player is making the move) and the cell
    //            position (0 - 8, left to right, top to bottom), and changes that part of the board accordingly,
    //            then throws an exception if the cell was an invalid play (due to an invalid input)
    // int, int -> void
    public void playCell(int cell) throws IndexOutOfBoundsException, IllegalStateException{
        if(cell < 0 || cell > 8) { // checks if the int cell input is outside the range
            throw new IndexOutOfBoundsException("Invalid cell, please enter a valid cell number"); // throws out of range exception
        }

        // If the inputs are valid...
        // check for valid play
        if(cell == lastMove){ // checks if the play is invalid (the last move wasn't in the same spot)
            // throws illegal (cell) state exception
            throw new IllegalStateException("Cannot play in a cell played in the previous round");
        } else if(playerTurn == 1 && board[cell] == '|'){ // checks if player 1 is playing in a cell they already used
            // throws illegal (cell) state exception
            throw new IllegalStateException("Player 1 has already played in this cell");
        } else if(playerTurn == 2 && board[cell] == '-'){ // checks if player 2 is playing in a cell they already used
            // throws illegal (cell) state exception
            throw new IllegalStateException("Player 2 has already played in this cell");
        } else if(board[cell] == '+'){ // checks if he player is trying to play in a completed cell
            // throws illegal (cell) state exception
            throw new IllegalStateException("This cell is already completed");
        }

        // Else, since valid play...
        // If either player played a cell correctly, then it will be set to the cell number (0-8).
        lastMove = cell;
        if(playerTurn == 1){
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

        playerTurn = (playerTurn == 1) ? 2 : 1; // changes the player turn to the next player
    }

    // checkEndGame - checks if the game is completed. If not, returns a 0. If so, it returns a 1 if player 1 won,
    //                and a 2 if player 2 won
    // none -> int
    public int checkEndGame(){
        boolean isEndgame;
        boolean didPlayer1Win;
        final int THREE_PLUS_AS_INT_VAL = (int)'+' + (int)'+' + (int)'+';

        // checks if any full row, column, or diagonal is complete
        // turns the chars of the checked cells into ints, adds them up, and checks if it equals the value of three '+' chars
        if((int)board[0] + (int)board[1] + (int)board[2] == THREE_PLUS_AS_INT_VAL){ // checks the top row
            isEndgame = true;
        } else if((int)board[3] + (int)board[4] + (int)board[5] == THREE_PLUS_AS_INT_VAL){ // checks the middle row
            isEndgame = true;
        } else if((int)board[6] + (int)board[7] + (int)board[8] == THREE_PLUS_AS_INT_VAL){ // checks the bottom row
            isEndgame = true;
        } else if((int)board[0] + (int)board[3] + (int)board[6] == THREE_PLUS_AS_INT_VAL){ // checks the left column
            isEndgame = true;
        } else if((int)board[1] + (int)board[4] + (int)board[7] == THREE_PLUS_AS_INT_VAL){ // checks the middle column
            isEndgame = true;
        } else if((int)board[2] + (int)board[5] + (int)board[8] == THREE_PLUS_AS_INT_VAL){ // checks the right column
            isEndgame = true;
        } else if((int)board[0] + (int)board[4] + (int)board[8] == THREE_PLUS_AS_INT_VAL){ // checks the forward diagonal
            isEndgame = true;
        } else if((int)board[2] + (int)board[4] + (int)board[6] == THREE_PLUS_AS_INT_VAL){ // checks the backward diagonal
            isEndgame = true;
        } else{
            isEndgame = false;
        }

        if(isEndgame){ // If the game is over
            // checks who played the last turn (opposite of current turn), last player to play before an endgame wins
            if(playerTurn == 1){
                didPlayer1Win = false;
            } else{
                didPlayer1Win = true;
            }

            if(didPlayer1Win){// If player 1 won
                player1Wins++;
                player2Loses++;
                return 1;
            } else{ // If player 2 won
                player2Wins++;
                player1Loses++;
                return 2;
            }
        } else{
            return 0; // nobody won (no endgame)
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

    // getPlayerTurn - returns a 1 or 2 for which player is currently in play
    // none -> int
    public int getPlayerTurn(){
        return playerTurn;
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
        String boardDisplay = "";
        boardDisplay += "=============\n";
        boardDisplay += "|   |   |   |\n";
        boardDisplay += "| " + board[0] + " | " + board[1] + " | " + board[2] + " |\n";
        boardDisplay += "|   |   |   |\n";
        boardDisplay += "|===|===|===|\n";
        boardDisplay += "|   |   |   |\n";
        boardDisplay += "| " + board[3] + " | " + board[4] + " | " + board[5] + " |\n";
        boardDisplay += "|   |   |   |\n";
        boardDisplay += "|===|===|===|\n";
        boardDisplay += "|   |   |   |\n";
        boardDisplay += "| " + board[6] + " | " + board[7] + " | " + board[8] + " |\n";
        boardDisplay += "|   |   |   |\n";
        boardDisplay += "=============\n";
        boardDisplay += "Current turn: Player " + playerTurn + "\n";

        return boardDisplay;
    }
}
