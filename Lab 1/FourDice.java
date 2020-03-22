// Name : Aaron Hernandez
// Email : aah007@ucsd.edu
// ID : A12623880

import java.lang.Math;
import java.util.Scanner;
import java.util.Random;

public class FourDice {
	public static int leastOfFour(int roll1,int roll2,int roll3,int roll4) {
		return (Math.min(Math.min(Math.min(roll1,roll2),roll3),roll4));
	}
	
	public static int greatestOfFour(int roll1,int roll2,int roll3,int roll4) {
		return (Math.max(Math.max(Math.max(roll1,roll2),roll3),roll4));
	}

	public static boolean ofAKind(int roll1,int roll2,int roll3,int roll4) {
		if ((roll1==roll2) && (roll1==roll3) && (roll1 ==roll4)) {
			System.out.println("Go to Vegas!");
			return true;
		}
		else if ((roll1==roll2) && (roll1==roll3)) {
			System.out.println("Three of a kind.");
			return true;
		}
		else if ((roll1==roll2) && (roll1==roll4)) {
                        System.out.println("Three of a kind.");
                        return true;
		}
                else if ((roll1==roll3) && (roll1==roll4)) {
                        System.out.println("Three of a kind.");
                        return true;
        	}
                else if ((roll2==roll3) && (roll2==roll4)) {
                        System.out.println("Three of a kind.");
                        return true;
        	}
		else {
			System.out.println("Not so lucky.");
			return false;
		}
	}
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		int seed =0;
		seed = scan.nextInt();
		System.out.println("Seed : " + seed);

                Random randNum = new Random();
		if (seed>0) {
                	randNum.setSeed(seed);
		}

		int roll1 = 0;
		int roll2 = 0;
		int roll3 = 0;
		int roll4 = 0;
		boolean lucky = true;
		roll1 = (randNum.nextInt(6) + 1);
                roll2 = (randNum.nextInt(6) + 1);
                roll3 = (randNum.nextInt(6) + 1);
                roll4 = (randNum.nextInt(6) + 1);

		System.out.println("Roll 1 : " + roll1);
                System.out.println("Roll 2 : " + roll2);
                System.out.println("Roll 3 : " + roll3);
                System.out.println("Roll 4 : " + roll4);

		System.out.println("Smallest Roll : " + leastOfFour(roll1, roll2, roll3, roll4));
                System.out.println("Largest Roll : " + greatestOfFour(roll1, roll2, roll3, roll4));

		lucky = ofAKind(roll1, roll2, roll3, roll4);
	}
}
