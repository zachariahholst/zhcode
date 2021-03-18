/*
 * Zach Holst
 * CS1150
 * October 3, 2019
 * Assignment 5
 * The purpose of this program is to allow a user to enter sales amounts
 * for different coffee shops to estimate certain statistics about the
 * sales amounts.
 */
import java.util.Scanner;
public class HolstZachAssignment5 {

	public static void main(String[] args) {

		//Create a new Scanner
		Scanner input = new Scanner(System.in);

		//prompt user to enter all sales
		System.out.print("Enter sales from all stores, end input by entering -1: ");

		//Define integers and doubles
		int numSales = 1;
		int userSalesInput = 0;
		int salesLessThanOneThousand = 0;
		int salesGreaterThanFiveThousand = 0;
		int salesBetweenOneAndFiveThousand = 0;
		double largest = 0;
		double smallest = 0;
		double range = 0;
		double totalSalesAmount = 0;

		//create a while loop that will end when the user inputs -1
		while(userSalesInput != -1) {

			//prompt for user input
			userSalesInput = input.nextInt();
			System.out.print(" ");

			//use an if statement for when a sale is input to give certain variables their
			//values for the statistics
			if(userSalesInput != -1) {
				System.out.print("Sales #" + numSales + ": ");

				//use Math class to determine largest input and an if statement
				//to find the smallest. use these to find the range

				largest = Math.max(largest, userSalesInput);

				if(userSalesInput < largest) {
					smallest = userSalesInput;
				}
				range = largest - smallest;

				//give total sales amount variable a value based on all inputs besides -1
				totalSalesAmount = totalSalesAmount + userSalesInput;

				//create another if statement to determine number of sales within different ranges
				if(userSalesInput < 1000) {
					salesLessThanOneThousand++;
				}

				else if((userSalesInput >= 1000) && (userSalesInput <= 5000)) {
					salesBetweenOneAndFiveThousand++;
				}

				else if(userSalesInput > 5000) {
					salesGreaterThanFiveThousand++;
				}

				//create another while loop to create the bar chart based on amount sale.
				//each * is equivalent to $100 of sales
				while(userSalesInput > 99) {

					System.out.print("*");
					userSalesInput = userSalesInput - 100;
				}

				//increase numSales variable by one to keep track of how many sales input
				//the user has entered
				System.out.println("");
				numSales++;
			}
			//prevents from counting -1 as a sale
			else {
				numSales--;
			}

		}

		//create an if statement in case no sales were input and only -1 was input
		if(numSales == 0) {
			System.out.println("No sales amounts were entered except -1");
		}

		//if at least one sale input was entered, create table of statistics based off values
		//found beforehand
		else {
			System.out.println("");
			System.out.println("Number of sales amount     = " + numSales);
			System.out.println("Largest sales amount       = " + largest);
			System.out.println("Smallest sales amount      = " + smallest);
			System.out.println("Sum of all sales amounts   = " + totalSalesAmount);
			System.out.println("Range (largest - smallest) = " + range);
			System.out.printf("Average                    = %.2f", totalSalesAmount/numSales);
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Number of sales amounts less than $1000:  = " + salesLessThanOneThousand);
			System.out.println("Number of sales amounts between $1000 and $5000:  = " + salesBetweenOneAndFiveThousand);
			System.out.println("Number of sales amounts more than $5000:  = " + salesGreaterThanFiveThousand);

		}

		//close scanner and end program
		input.close();

	}

}
