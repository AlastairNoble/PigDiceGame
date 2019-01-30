// Alastair Noble; 20079187

import java.util.Random;
import java.util.Scanner;

public class assignment1take2 {
	public static void main(String[] args){
		System.out.println("Welcome to the game of pig. if you want intsructions use google lol \n" + 
		"everything runs until you have to choose to roll again or not, so you may have to scroll up to read previous moves\n" + 
		"to choose to roll again hit y and then enter for yes or n and then enter for no\n" +		
		"its your turn first: \n"); 						//instructions
		int playerScore=0; 									//initialize scores
		int compScore=0;
		while(true) { 										//i dont think alan would approve of while true but i thought this made sense 
			playerScore += turn(playerScore, "you");		//player goes first, and turn returns the score for each turn, so it increments
			if(playerScore>99) { 							//if player wins, tell them and then break
				System.out.println("you win");
				break;
			}
			System.out.printf("\nscore at the end of your turn: %d \n\n", playerScore);//print total score at the end of player turn
			compScore += turn(compScore, "comp"); 			//same as player turn but pass in different user.
			if (compScore>99) {
				System.out.println("comp wins");			//break and tell user if comp won
				break;
			}
			System.out.printf("\nscore at the end of computers turn: %d \n\n", compScore);//display final scores
		}
	}
	
	static int turn(int score, String user){				//method used by both comp and player for a turn
		int turnScore=0;									// initialize turn score to 0
		boolean rollAgain=true;								// parameter for the following loop, starts as true
		while (rollAgain) {
			int rollScore = roll(turnScore, user);			//start loop with calling method roll
			turnScore +=  rollScore;						//increment local turn score
			System.out.printf(user + " scored %d this roll \n", rollScore);//display roll score
			if (rollScore+score>=100) break; 				//check if user won
			if (rollScore == 0) {							//if roll returns 0, ie rolled one 1, reset turn score and break loop, ending turn
				turnScore=0;
				break;
			}
			System.out.printf(user + " current score is %d \n", turnScore+score);
			if (turnScore+score>=100) break;				//check if user won again
			if (rollScore == 25) rollAgain=true;			//auto roll again for double ones
			else if (user == "comp") rollAgain=compRollAgain(score, turnScore);//if its the comp rolling, consult the algorithm for rollagain
			else rollAgain= rollagain();					//otherwise it is the user, so consult the user rollagain method
		}
		return turnScore; 									//do i need to explain this?no
	}
	
	static int roll(int turnScore, String user) {
		int roll1 = roll();
		int roll2 = roll();									// roll 2 different dice
		System.out.printf(user + " rolled: %d and %d \n", roll1, roll2);//display rolls
		if (roll1 == 1 && roll2 == 1) {						//return 25 for double ones
			return 25;
		} else if(roll1==1 || roll2==1) { 					//return 0 for single one
			return 0;
		} else if(roll1==roll2) { 							//return 2x roll for doubles
			return 4*roll1;
		} else { 											// default roll
			return roll1+roll2;
		}
	}	
		
	static boolean rollagain() {
		boolean realAnswer=true;
		while (realAnswer) {								//loop in case user gives bad input
			Scanner screen = new Scanner(System.in);		//I know the screen never closes but i like to live life on the edge
			System.out.print("roll again? (y/n)\n");		//ask for another roll
			char character = screen.next().charAt(0);		//save user input to character
			if (character=='y')return true;					//if y, then rollagain
			else if (character=='n')return false;			//if n, then dont roll again
			int rand=new Random().nextInt()%5;				// get rand number for switch case
			switch (rand) {									//i used a switch so the loop would be more entertaining with random answers
			case 0: System.out.printf("answer properly you monster\n");
				break;
			case 1:	System.out.printf("come on buddy\n");
				break;
			case 2: System.out.printf("really bro?\n");
				break;
			case 3:	System.out.printf("oooh we got a wise guy. sick\n");
				break;
			default: System.out.printf("cant you read? idiot\n");
					break;
			}
		}
		return false;							// code never reaches this but it keeps the compiler happy
	}
	
	static boolean compRollAgain(int score, int turnScore) {//computer algorithm, uncommented because it is a secret
		if (turnScore+score>90) return true;	//lol
		if (turnScore>20) {System.out.println("comp decided to end turn");return false;}else return true;}
	
	static int roll() {							//i didnt really like the strategy alan gave us for random numbers and this orks just fine 
		Random rand = new Random();				//get large random number
		int num2 = rand.nextInt();				//convert from long to int
		return Math.abs(num2%6)+1;				//num between 1 and 6
	}
}// 100 lines baby