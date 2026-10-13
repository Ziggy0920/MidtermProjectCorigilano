/*
 * Jonas Corigliano - jbcorigliano@dmacc.edu
 * CIS171 Mon Afternoon
 * Date: 10/5/2026
 * Operating System: Windows 11
 * IDE: IntelliJ IDEA
 * Program Description(short): Using Java GUI, you can play a text-based Ticko 2 (Tic-Oat-Two) with 2 players
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or unmodified
 * Documentation of Resources Used:
 *      1) JOptionPane font imports and implementation: https://stackoverflow.com/questions/17059575/how-to-change-the-font-in-joptionpane-showinputdialog-jtextfield
 */

package driver;

import model.Ticko2;

import javax.swing.UIManager; // import from research (1)
import java.awt.Font; // import from research (1)
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main{
    public static final int MAX_USERNAME_LENGTH = 12;

    static void main(String[] args){
        boolean isPlaying = false;
        boolean isRunning = true;
        String action;
        boolean isValidInput;
        String player1Name;
        String player2Name;
        String cellChoice;
        Ticko2 to2 = new Ticko2();

        Font monoFont = new Font("Monospaced", Font.BOLD, 14);
        UIManager.put("OptionPane.messageFont", monoFont);

        showMessageDialog(null, "Welcome to Ticko2! (Tic-Oat-Two)\nThe sequel to Tic-Tac-Toe");
        // get usernames
        player1Name = getUsername("Player 1");
        player2Name = getUsername("Player 2");
        while(isRunning){ // main loop
            // start asking for actions
            isValidInput = false;
            action = "";
            while(!isValidInput){
                // check if action is valid, if not then ask again
                try{
                    action = showInputDialog(null, "Select an action by entering it's number:\n" +
                            "1: Play game\n" +
                            "2: View player stats\n" +
                            "3: How to play\n" +
                            "4: Quit program");
                    if(action.equals("1") ||  action.equals("2") || action.equals("3") || action.equals("4")){
                        isValidInput = true;
                    } else{
                        throw new Exception("Invalid: input must be a \"1\", \"2\", \"3\", or \"4\"");
                    }
                } catch(Exception e){
                    showMessageDialog(null, e.getMessage());
                }
            }

            switch(action){
                // if action is 1, start a game in the game loop
                case "1":
                    isPlaying = true;
                    break;
                // if action is 2, show the player stats then return to action selection
                case "2":
                    displayStats(to2, player1Name, player2Name);
                    break;
                // if action is 3, show instructions on how to play
                case "3":
                    displayGameRules(player1Name, player2Name);
                    break;
                // if action is 4, stop the main loop
                case "4":
                    isRunning = false;
                    break;
                default:
                    isRunning = false;
            }

            // game loop
            if(isPlaying){
                to2.newGame();
                while(isPlaying){
                    isValidInput = false;
                    while(!isValidInput){ // forces valid input
                        try{
                            if(to2.getPlayerTurn() == 1){
                                cellChoice = showInputDialog("Enter any number 1 through 9 to place a line.\n" +
                                        "In increasing order, the cells are numbered from left to right, and top to bottom.\n" +
                                        to2.getBoard() +
                                        "It is " + player1Name + "'s turn\n" +
                                        "Enter a 0 to exit the game");
                            } else{
                                cellChoice = showInputDialog("Enter any number 1 through 9 to place a line.\n" +
                                        "In increasing order, the cells are numbered from left to right, and top to bottom.\n" +
                                        to2.getBoard() +
                                        "It is " + player2Name + "'s turn\n" +
                                        "Enter a 0 to exit the game");
                            }
                            if(Integer.parseInt(cellChoice) == 0){
                                showMessageDialog(null, "Game exited, no stats changed");
                                isPlaying = false;
                                break;
                            }
                            to2.playCell(Integer.parseInt(cellChoice) - 1);
                            isValidInput = true;
                        } catch(IndexOutOfBoundsException e){
                            showMessageDialog(null, e.getMessage());
                        } catch(IllegalStateException e){
                            showMessageDialog(null, e.getMessage());
                        } catch(Exception e){
                            showMessageDialog(null, "Enter a valid integer");
                        }
                    }

                    // checks endgame
                    isPlaying = false;
                    switch(to2.checkEndGame()){
                        case 1:
                            showMessageDialog(null, to2.getBoard() +
                                    "\n" + player1Name + " has won the game!");
                            break;
                        case 2:
                            showMessageDialog(null, to2.getBoard() +
                                    "\n" + player2Name + " has won the game!");
                            break;
                        default:
                            isPlaying = true;
                            break;
                    }
                }
            }
        }
    }

    // getUsername - uses input dialog windows to get a username
    // none -> String
    public static String getUsername(String title) {
        String username = "";
        boolean isValidUsername = false;
        // gets username through input dialogs
        while (!isValidUsername){
            try {
                username = showInputDialog("Enter a username for " + title + ":");
                if(username == null || username.isEmpty() || username.isBlank()){ // checks for a valid name (not null)
                    throw new Exception("Username is empty");
                } else if(username.length() > MAX_USERNAME_LENGTH){
                    throw new Exception("Username is too long");
                } else{
                    isValidUsername = true;
                    showMessageDialog(null, "You have entered: " + username + "\nfor " + title);
                }
            } catch(Exception e){
                showMessageDialog(null, e.getMessage() +
                        "\nPlease enter a valid username for " + title);
            }
        }
        return username;
    }

    // displayGameRules - tells the players how to play using message dialog windows
    // none -> void
    public static void displayGameRules(String player1Username, String player2Username){
        showMessageDialog(null, "How to play Ticko2:\n" +
                "The board is a 3-by-3 grid.\n" +
                "Instead of X's and O's, " + player1Username + " will use vertical lines,\n" +
                "and " + player2Username + " will use horizontal lines.\n" +
                "You may place your lines on top of the other player's line,\n" +
                "creating a \"completed\" cell on the grid. (+)\n" +
                "However, you may not place a line on a cell played on\n" +
                "by the other player in the previous round.\n" +
                "To reach the endgame, any 3 consecutive cells must be completed.\n" +
                "The winner is decided by the player to place a line on the\n" +
                "last needed cell, completing it.");
    }

    // displayStats - will use a message dialog window to show the players' wins and loses, using a manually-built String
    // Ticko2 (Object) -> void
    public static void displayStats(Ticko2 t, String player1Username, String player2Username){
        String stats = "";
        // shows the player stats using a built String in a normal message dialog window
        stats += player1Username; // build player 1 stat block
        for(int i = 0; i <= (MAX_USERNAME_LENGTH - player1Username.length()); i++){
            stats += " ";
        }
        stats += "| Wins:Loses >> " + t.getPlayer1Stats()[0] + ":" + t.getPlayer1Stats()[1] + "\n";

        for(int i = 0; i < MAX_USERNAME_LENGTH; i++){
            stats += "=";
        }
        stats += "========================\n";

        stats += player2Username; // build player 2 stat block
        for(int i = 0; i <= (MAX_USERNAME_LENGTH - player2Username.length()); i++){
            stats += " ";
        }
        stats += "| Wins:Loses >> " + t.getPlayer2Stats()[0] + ":" + t.getPlayer2Stats()[1];

        showMessageDialog(null, stats);
    }
}
