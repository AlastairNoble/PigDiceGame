import java.util.Random;
import java.util.Scanner;

public class assignment1 {

	public static void main(String[] args) {
		int playerScore=0;
		int compScore=0;
		while (true) {
			playerScore += playerturn(playerScore);
			if(playerScore<0) {
				break;
			}
			compScore += compturn();
			if (playerScore>100) {
				System.out.printf("nice job i guess");
				break;
			}
			if(compScore>100) {
				System.out.printf("beat by a damn computer");
				break;
			}
		}
	}
	static int compturn() {
		int compscore = 0;
		boolean rollagain=true;
		while(rollagain) {
			compscore = comproll(compscore);
			if (compscore>100) {
				System.out.printf("computer wins idiot\n");
				return 2;
			} else if (compscore < 1) {
				System.out.printf("ur turn again, silly computer has 0 \n");
				return 0;
			}
				
		System.out.printf("\n");	
		}
		return 0;		
	}	
	
	static int playerturn(int playerScore) {
		int turnScore=0;
		boolean rollagain=true;
		char yorn;
		while(rollagain) {
			turnScore += playerroll(turnScore);
			if (playerScore+turnScore>100) {
				return turnScore;
			}
			if (turnScore == 0) {
				System.out.printf("sorry dog computers turn \n\n");
				return 0;
			}
			yorn = scanforchar();
			if (yorn == 'n') {
				int compwin = compturn();
				if(compwin ==2) {
					System.out.printf("dumb giving your turn away buddy the comp won");
					return 0;
				};
			} else if (yorn != 'y'){
				System.out.print("very mature of you. you dont get another roll you fuck and ur score is 0\n");
				rollagain = false;
			}	

		System.out.printf("\n");
		}
		return turnScore;
	}
	static char scanforchar() {
		Scanner screen = new Scanner(System.in);
		System.out.print("roll again? (y/n)");
		char character = screen.next().charAt(0);
		return character;
	}
	
	static int playerroll(int playerScore){
//		int playerScore=0;
		if(playerScore>100)return playerScore;
		int roll1 = roll();
		int roll2 = roll();
		System.out.printf("you rolled: %d and %d \n", roll1, roll2);
		
		if (roll1 == 1 && roll2 == 1) {
			return ones(playerScore);
		} else if(roll1==1 || roll2==1) {
			return oneone(playerScore);
		} else if(roll1==roll2) {
			return doubles(playerScore, roll1);
		} else {
			return normal(playerScore, roll1, roll2);
		}
	}	
	
	static int comproll(int compscore){
		if(compscore>100) return compscore;
		int roll1 = roll();
		int roll2 = roll();
		System.out.printf("computer rolled: %d and %d \n", roll1, roll2);
		
		if (roll1 == 1 && roll2 == 1) {
			return compones(compscore);
		} else if(roll1==1 || roll2==1) {
			return componeone(compscore);
		} else if(roll1==roll2) {
			return compdoubles(compscore, roll1);
		} else {
			return compnormal(compscore, roll1, roll2);
		}
	}	
	
	static int ones(int playerScore){
		System.out.printf("you got 25 points \n");
		playerScore +=25;
		System.out.printf("your new score is: %d \n", playerScore);
		System.out.printf("roll again \n\n");
		playerScore += playerroll(playerScore);
		return playerScore;
	}
	
	static int oneone(int playerScore) {
		System.out.printf("turn score reset to 0 \n");
		return 0;
	}
	
	static int doubles(int playerScore, int roll) {
		System.out.printf("you got %d points \n", 4*roll);
		playerScore += 4*roll;
		System.out.printf("new score is: %d \n", playerScore);
		return playerScore;
	}
	
	static int normal(int playerScore, int roll1, int roll2) {
		System.out.printf("you got %d points \n", roll1+roll2);
		playerScore += (roll1+roll2);
		System.out.printf("new score is: %d \n", playerScore);
		return playerScore;
	}
		
	static int compones(int compscore){
		System.out.printf("comp got 25 points \n");
		compscore +=25;
		System.out.printf("comps new score is: %d \n", compscore);
		System.out.printf("roll again \n\n");
		compscore += comproll(compscore);
		return compscore;
	}
	
	static int componeone(int compscore) {
		System.out.printf("comp score reset to 0 \n\n");
		return -compscore;
	}
	
	static int compdoubles(int compscore, int roll) {
		System.out.printf("comp got %d points \n", 4*roll);
		compscore += 4*roll;
		System.out.printf("comps new score is: %d \n", compscore);
		return compscore;
	}
	
	static int compnormal(int compscore, int roll1, int roll2) {
		System.out.printf("comp got %d points \n", roll1+roll2);
		compscore += (roll1+roll2);
		System.out.printf("comps new score is: %d \n", compscore);
		return compscore;
	}	
	
	static int roll() {
		Random rand = new Random();
		int num2 = rand.nextInt();
		return Math.abs(num2%6)+1;
	}
}