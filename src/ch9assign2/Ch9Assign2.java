 /*
 Game that simulates a simplified bowling game

 */
package ch9assign2;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cecilia
 */
public class Ch9Assign2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //decl vars 
        
        ArrayList <String> playerNamesList;
        String playerName;
        Scanner input = new Scanner(System.in);
        int frameNum;
        boolean addPlayers; //boolean to exit add player loop
        String playAgain = ""; 
        
        //prompt for players until user is done entering names using AddOnePlayer method 
        do {
            System.out.println("Thanks for playing Celia's Amazing Bowling Bonanza!");
            playerNamesList = new ArrayList<>();
            frameNum = 0;
            addPlayers = true;
            while (addPlayers) {

                addPlayers = AddOnePlayer(playerNamesList);

            }//end while add more players

            //display all the players
            System.out.println("Players competing in this game are: ");
            for (String str: playerNamesList) { //later, print format so that it doesn't print a coma after the last item. 
                System.out.println(str);
            }//end enhanced for


            //create an array/ ArrayList for each player's scores 
            int[][] frameScores = new int [10][playerNamesList.size()];

            //create for loop that continues until frame > 10
            frameNum = 0;
            while (frameNum < 10) { 
                for (int i = 0; i < playerNamesList.size(); i++) { //loop through all players for all 10 frames

                    frameScores [frameNum][i] = PlayersTurn(i, playerNamesList, frameNum);

                }//end for loop each player's turn

                    PrintScores(playerNamesList, frameScores, frameNum);            
                    frameNum++;

            }// end outer while loop (frameNum < 11), and end game

            //determine winner 
            System.out.println();

            do {
                System.out.println("Would you like to play again? ");//ask if they would like to play again
                try {
                    playAgain = input.nextLine().toLowerCase();
                }//end try 
                catch (StringIndexOutOfBoundsException err) {
                    System.err.println("No input detected. Please try again.");
                }//end catch 
            } while (playAgain.equals(""));
        } while (playAgain.equals("y")); //why isn't this condition working?
        
        System.out.println("Thank you for playing!");
        
    }//end main
    
    //method to add players to the bowling game 
    public static boolean AddOnePlayer(ArrayList <String> playerNamesList) {
        Scanner input = new Scanner(System.in);
        boolean addPlayers = true; 
        String playerName = null; 
        
            while (playerName == null) {
                System.out.println("Add a player's name or press 1 to start your game: ");
                try {
                    playerName = input.nextLine();
                
                    if (playerName.charAt(0) == '1') {
                        addPlayers = false; 
                    }//end if
                    else {
                        playerNamesList.add(playerName);
                        //System.out.println(playerNamesList); //print to test if names are added to ArrayList properly
                    }//end else 

                }//end try 
                
                catch (StringIndexOutOfBoundsException err) {
                    System.err.println("No input detected. Please try again.");
                }//end catch
            }                                
                return addPlayers;
        
    }//end playerNamesList method
    
    //Method PlayersTurn simulates two throws and returns a score for the frame. arguments (int playerNum, ArrayList<String> 
    public static int PlayersTurn(int playerNum, ArrayList <String> playerNamesList, int frameNum) {
        
        char go;
        int throw1;
        int throw2;
        int frameScore = 0;
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nFrame " + (frameNum + 1)  + "\n");
        System.out.println("It's your turn, " + playerNamesList.get(playerNum) + ". \nPress any key then Enter to continue.");
        go = input.next().charAt(0);
        
        throw1 = (int)(Math.random() * 11); //throw1 is assigned a random num. If the random num is 10, they got a strike and get 20 points. Their turn ends. 
        //System.out.println(throw1);//TESTING print to see what the results of the random # were
        
        if (playerNamesList.get(playerNum).equalsIgnoreCase("Celia")) { //I  get a strike every time
            frameScore = 20;
            System.out.println("Awesome, Celia! You got a strike!");
            return frameScore;
        }//end Celia Advantage

        
            if (throw1 <10) {
                if (throw1 == 9 ) {
                    System.out.println(playerNamesList.get(playerNum) + " scores a " + throw1 + " on the first throw.\n"
                        + "There is " + (10-throw1) + " pin left.");
                }//end if there's only 1 pin left
                
                else {
                    System.out.println(playerNamesList.get(playerNum) + " scores a " + throw1 + " on the first throw.\n"
                        + "There are " + (10-throw1) + " pins left.");
                }//end else more than 1 pin left
            }//end else throw 1
            
            else if (throw1 == 10) {
                System.out.println("Amazing! " + playerNamesList.get(playerNum) + " scored a Strike!\n"
                        + "10 pins down, plus 10 extra points!");
                frameScore = 20;
                return frameScore;
            }//end if statement
            
        System.out.println("\nIt's still your turn " + playerNamesList.get(playerNum) + ". \nPress any key then Enter to continue.");
        go = input.next().charAt(0);
        
        throw2 = (int) (Math.random() * (11-throw1)); //throw2 only occurs if throw1 wasn't a strike. Throw2 will be a random # between (10-throw1) and 0. 
        frameScore = throw1 + throw2;
        //System.out.println(throw2); //TESTING see what the throw2 results were
                
        if (frameScore <10) {
            
            if (frameScore == 9 ) {
                    System.out.println(playerNamesList.get(playerNum) + " knocked over " + throw2 + " more pin(s) on the second throw.\n"
                    + "There is " + (10-frameScore) + " pin left.");
                }//end if there's only 1 pin left
            
            else {
                System.out.println(playerNamesList.get(playerNum) + " knocked over " + throw2 + " more pin(s) on the second throw.\n"
                    + "There are " + (10-frameScore) + " pins left.");
            }//end else frame score is 9
            
        }//end else throw 1
        
        else if (frameScore == 10) {
            frameScore = 15; 
            System.out.println("Good job! " + playerNamesList.get(playerNum) + " scored a Spare!\n"
                    + "10 pins down, plus 5 extra points!");
            return frameScore;
        }//end else if statement

        return frameScore;
            
    }//end PlayersTurn
    
    //method PrintScores 
    public static void PrintScores (ArrayList <String> playerNamesList, int[][] frameScores, int frameNum) {
        
        int numPlayers = playerNamesList.size(); 

        //System.out.println(playerNamesList.get(0));
        
        System.out.println("\nScores stand thusly after Frame " + (frameNum +1) + ": ");
        System.out.print("\t");
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("\t" + playerNamesList.get(i));
        }//end for loop
        
        for (int j = 0; j < 10; j++) {
            System.out.print ("\nFrame " + (j+1) + ": "); 
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("\t" + frameScores[j][i]);
            }//end for loop
        }//end print 10 frames for loop
        System.out.println();
        
        FinalScores(playerNamesList, frameScores, frameNum);
        
    }//end method PrintScores

    public static void FinalScores (ArrayList <String> playerNamesList, int[][] frameScores, int frameNum) {
        //vars
        int playerScore = 0; //used to increment player scores for total score in loop
        int winningScore = 0; 
        String winningPlayer = null;
        
        //create outer loop that tracks the player, print total after inner loop
        System.out.print("Total Scores: ");
        for (int k = 0; k < playerNamesList.size(); k++) {
            //create inner loop that increments values in each row of that player's frame scores
            int l = 0; 
            while (l < 10) {
                playerScore += frameScores[l][k];
                l++;
            }//end inner while, printing frames        
            System.out.print("\t" + playerScore);//increment the player variable and print the total for that player
            if (playerScore > winningScore){
                winningScore = playerScore;
                winningPlayer = playerNamesList.get(k);
            }//end if statement
            playerScore = 0; 
        }//end outer for loop to display scores
        System.out.println();
        
        if (frameNum == 9) {
            System.out.println("\nGame Over!"
                    + "\nThe winner is: " + winningPlayer + ", with " + winningScore + " points!");
        }
            
        
    }//end method Final Scores display 
    
}//end class
