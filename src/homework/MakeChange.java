package homework;

import java.util.*;

public class MakeChange {
	public static void main(String[] args) {

		promptUser();

	}

	public static void promptUser() {
		Scanner kb = new Scanner(System.in);
		String newItem;
		do {
			// We are going get the actual price and tendered amount here
			System.out.print("What is the price of the item: ");
			double price = kb.nextDouble();
			System.out.print("What is the amount tendered: ");
			double tendered = kb.nextDouble();

			// Now we compare the tendered amount and price to get the exact
			// amount
			// of change to print!

			if (tendered < price) {
				double difference = (price - tendered);
				System.out.println(
						"Sorry, you are short by $" + difference + "\nWould you like to pay the difference? (Y/N) ");
				String Check = kb.next();
				priceCheck(Check, kb, price, tendered);

			} else if (tendered == price) {
				System.out.print("Thank you, Come again!!");
			} else { // make another method to find the exact change to pay back
						// the
						// customer
				payBack(price, tendered);
				// System.out.print(change);
			}
			System.out.print("\nWould you like to purchase another item (Y/N): ");
			newItem = kb.next();

		} while (newItem.toUpperCase().equals("Y"));
		kb.close();
	}

	public static void payBack(double price, double tendered) {
		double[] bill = { 20, 10, 5, 1, 0.25, 0.10, 0.05, 0.01 };
		int[] change = new int[8];
		double difference = tendered - price;
		// I want to store the number that can divide the difference into an
		// array and then change the difference to the new remainder
		for (int i = 0; i < change.length; i++) {
			if (difference / bill[i] > 0) {
				change[i] = ((int) (difference / bill[i]));
				difference = difference % bill[i] + 0.001;
				// difference = Math.ceil(difference % bill[i]); doesn't round
				// to 100ths
				// I added 0.001 to fix the rounding error!
			}
			// System.out.print(difference + "\t");
		}
		System.out.println("Your change is: ");
		for (int j = 0; j < change.length; j++) {
			if ((int) change[j] != 0) {
				if ((bill[j]) >= 0) {
					System.out.print("$" + bill[j] + ":\t");
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

	// To allow the user to re-make the payment until its above the item price!!
	public static void priceCheck(String rePay, Scanner kb, double itemP, double t) {
		double addedSum = 0;
		outerloop:

		// newDiff is (tendered - price) from payBack method and addedSum is the
		// new payment
		while (true) {
			if (rePay.toUpperCase().equals("Y")) {
				System.out.print("Added amount: ");
				addedSum = kb.nextDouble();
				if ((addedSum + t) < itemP) {
					t = t + addedSum;
					System.out.print("\nSorry, but you are still short $" + ((itemP*100 - t*100 )/100)
							+ ".\n Would you like to pay the difference. (Y/N): ");
					rePay = kb.next();
					continue outerloop;
				} else if ((addedSum + t) > itemP) {
					payBack(itemP, (t + addedSum));
				} else if (addedSum + t == itemP) {
					System.out.print("Thank you, Come again!!\n");
					// NEED TO RETURN IT BACK!
				}
			} else {
				System.out.print("Thank you, and please come back when you have the money!");
				System.exit(0);;
			}break;
		}

	}
}
