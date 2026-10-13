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

package driver;

import model.Ticko2;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

// IMPORTANT: REMEMBER TO USE MODULE 4 TO RE-LEARN JOptionPane

public class Main{
    static void main(String[] args){
        boolean isPlaying = false;
        boolean isRunning = true;
        String action;
        boolean isValidInput;
        String player1Name;
        String player2Name;
        Ticko2 to2 = new Ticko2();

        JOptionPane.showMessageDialog(null, "Welcome to Ticko2! (Tic-Oat-Two)\nThe sequel to Tic-Tac-Toe");
        while(isRunning){ // main loop
            // get usernames
            player1Name = getUsername("Player 1");
            player2Name = getUsername("Player 2");

            // start asking for actions
            // check if action is valid, if not then ask again

            // if action is 1, start a game in the game loop
            // if action is 2, show the player stats then return to action selection
            // if action is 3, stop the main loop

            // game loop
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
                if(username.isEmpty() || username.isBlank()){ // checks for a valid name (not null)
                    throw new Exception("Username is empty");
                } else{
                    isValidUsername = true;
                    JOptionPane.showMessageDialog(null, "You have entered: " + username + "\nfor " + title);
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nPlease enter a username for " + title);
                isValidUsername = false;
            }
        }
        return username;
    }

    // displayStats - will use a message dialog window to show the players' wins and loses, using a manually-built String
    // Ticko2 (Object) -> void
    public static void displayStats(Ticko2 t){
        // shows the player stats using a built String in a normal message dialog window
    }
}
