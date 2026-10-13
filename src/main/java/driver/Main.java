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

        showMessageDialog(null, "Welcome to Ticko2! (Tic-Oat-Two)\nThe sequel to Tic-Tac-Toe");
        while(isRunning){ // main loop
            // get usernames
            player1Name = getUsername("Player 1");
            player2Name = getUsername("Player 2");

            // start asking for actions
            isValidInput = false;
            action = "";
            while(!isValidInput){
                // check if action is valid, if not then ask again
                try{
                    action = showInputDialog(null, "Select an action by entering it's number:\n" +
                            "1: Play game\n" +
                            "2: View player stats\n" +
                            "3: Quit program");
                    if(action.equals("1") ||  action.equals("2") || action.equals("3")){
                        isValidInput = true;
                    } else{
                        throw new Exception("Invalid: input must be a \"1\", \"2\", or \"3\"");
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
                    displayStats(to2);
                    break;
                // if action is 3, stop the main loop
                case "3":
                    isRunning = false;
                    break;
                default:
                    isRunning = false;
            }

            // game loop
            if(isPlaying){
                to2.newGame();
            }
            while(isPlaying){

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
                if(username.isEmpty() || username.isBlank()){ // checks for a valid name (not null)
                    throw new Exception("Username is empty");
                } else{
                    isValidUsername = true;
                    showMessageDialog(null, "You have entered: " + username + "\nfor " + title);
                }
            } catch(Exception e){
                showMessageDialog(null, e.getMessage() + "\nPlease enter a username for " + title);
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
