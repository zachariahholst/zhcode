/*
 * Zach Holst
 * CS1150
 * Sept 26, 2019
 * Assignment 4
 * The purpose of this assignment is to create a random
 * credit card generator by taking the input of the user
 * as a string and using the Math.random() function
 * to generate random numbers based off of the input.
 */

import java.util.Scanner;
public class HolstZachAssignment4 {

	public static void main(String[] args) {
		//create a new scanner
		Scanner input = new Scanner(System.in);
		
		//define constants for creditCard types and credit levels
		final char VISA = 'V';
		final char AMERICAN_EXPRESS = 'A';
		final char FAKE_INTERNATIONAL = 'F';
		final char REGULAR = '1';
		final char PLATINUM = '2';
		final char SIGNATURE = '3';
		
		//create a menu with options for the user
		System.out.println("Enter two characters");
		System.out.println("-----------------------------------------------------");
		System.out.println("V: Visa   A: American Express   F: Fake International");
		System.out.println("1: Regular   2: Platinum   3:Signature");
		System.out.println("-----------------------------------------------------");
		
		//prompt user to enter creditCard type and store in a string
		String creditCardType = input.nextLine();
		
		//prompt user to enter credit level and store in a string
		String creditLevel = input.nextLine();
		
		//store values in a string
		String creditCard = creditCardType.concat(creditLevel);
		
		//being if statement
		//determine if creditCard type is valid
		//allow both upper case and lower case values to be input
		if(((Character.toUpperCase(creditCard.charAt(0)) != VISA)) && ((Character.toUpperCase(creditCard.charAt(0)) != AMERICAN_EXPRESS)) && ((Character.toUpperCase(creditCard.charAt(0)) != FAKE_INTERNATIONAL))){
			System.out.println("Invalid input for credit card type");
		}
		
		//if credit card type has been validated, now validate credit level
		if((creditCard.charAt(1) != REGULAR) && (creditCard.charAt(1) != PLATINUM) && (creditCard.charAt(1) != SIGNATURE)) {
			System.out.println("Invalid input for credit level");
		}
		
		//if both values are validated, continue on
		//create a new string to be used later for the final credit card name
		String finalCreditCard = "";
		
		//if V was the given value
		if((Character.toUpperCase(creditCard.charAt(0)) == VISA)) {
			finalCreditCard = "Visa";
		}
		
		//if A was the given value
		else if((Character.toUpperCase(creditCard.charAt(0)) == AMERICAN_EXPRESS)) {
			finalCreditCard = "American Express";
		}
		
		//if F was the given value
		else if((Character.toUpperCase(creditCard.charAt(0)) == FAKE_INTERNATIONAL)) {
			finalCreditCard = "Fake International";
		}
		
		//create a switch statement for printing out the credit level and the final credit card type
		switch (creditCard.charAt(1)){
		case '1':
			System.out.print("" + finalCreditCard + " ");
			break;
		case '2':
			System.out.print("Platinum " + finalCreditCard + " ");
			break;
		case '3':
			System.out.print("Signature " + finalCreditCard + " ");
			break;
		}
		
		//create another switch statement to determine the random numbers for the credit card
		//based off of the design of each type of card
		//Fake international uses char because it has random letters in the first group of four
		switch (Character.toUpperCase(creditCard.charAt(0))) {
		case 'V':
			System.out.print("4");
			System.out.print((int)(100 + Math.random()*100) + " ");
			System.out.print((int)(1000 + Math.random()*8999) + " ");
			System.out.print((int)(1000 + Math.random()*8999) + " ");
			System.out.print((int)(1000 + Math.random()*8999) + " ");
			break;
		case 'A':
			System.out.print("37");
			System.out.print((int)(10 + Math.random()*89) + " ");
			System.out.print((int)(100000 + Math.random()*899999) + " ");
			System.out.print((int)(10000 + Math.random()*89999) + " ");
			break;
		case 'F':
			//A is 65, Z is 90 in ASCII
			System.out.print((char)(65 + Math.random() * 25));
			System.out.print((char)(65 + Math.random() * 25));
			System.out.print((char)(65 + Math.random() * 25));
			System.out.print((char)(65 + Math.random() * 25) + " ");
			System.out.print((int)(1000 + Math.random()*8999) + " ");
			System.out.print((int)(10000 + Math.random()*89999) + " ");
			break;
		}

		//close scanner and end program
		input.close();
	}


}


