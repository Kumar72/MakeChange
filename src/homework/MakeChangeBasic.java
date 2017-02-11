package homework;

import java.util.*;

public class MakeChangeBasic {
	public static void main(String[] args) {

		promptUser();

//		advancePromptUser();  gonna work on version 2 later
	}

	public static void promptUser() {
		Scanner kb = new Scanner(System.in);

		// We are going get the actual price and tendered amount here
		System.out.print("What is the price of the item: ");
		double price = kb.nextDouble();
		System.out.print("What is the amount tendered: ");
		double tendered = kb.nextDouble();

		// Now we compare the tendered amount and price to get the exact amount
		// of change to print!

		if (tendered < price) {
			System.out.println(
					"Sorry, you are short by $" + (price - tendered));
					// + "\nWould you like to pay the difference? ");
		} else if (tendered == price) {
			System.out.print("Thank you, Come again!!");
		} else { // make another method to find the exact change to pay back the
					// customer
					// String change =
			payBack(price, tendered);
			// System.out.print(change);
		}
		kb.close();
	}

	public static void payBack(double price, double tendered) {
		double[] bill = { 20, 10, 5, 1, 0.25, 0.10, 0.05, 0.01 };
		int[] change = new int[8];
		double difference = tendered - price;
		// I want to store the number that can divide the difference into an
		// array and then change the difference to the new remainder
		for (int i = 0; i < change.length; i++) {
			if (difference/bill[i] > 0) {
				change[i] = ((int) (difference / bill[i]));
				difference = difference%bill[i] +0.001;
		//		difference = Math.ceil(difference % bill[i]); doesn't round to 100ths
		// 		I added 0.001 to fix the rounding error!
			}
		//System.out.print(difference + "\t");
		}
		System.out.println("Your change is: ");
		for (int j = 0; j < change.length; j++) {
			if ((int) change[j] != 0) {
				if ((bill[j]) >= 0) {
					System.out.print("$"+bill[j] + ":\t");
					System.out.print(change[j]);
					System.out.print("\n");
				}
				if ((bill[j]) < 0) {
					System.out.print(bill[j] + "c:\t");
					System.out.print(change[j]);
					System.out.print("\n");
				}
			}
		}
	}
}
