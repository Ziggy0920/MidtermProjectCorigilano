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

            // start asking for actions
            // check if action is valid, if not then ask again

            // if action is 1, start a game in the game loop
            // if action is 2, show the player stats then return to action selection
            // if action is 3, stop the main loop

            // game loop
        }
    }

    public static String[] getUsernames(){
        String[] usernames = new String[2];
        // gets usernames through input dialogs
        return usernames;
    }

    public static void displayStats(Ticko2 t){
        // shows the player stats using a built String in a normal message dialog window
    }
}
