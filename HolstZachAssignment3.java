/*
 * Zach Holst
 * CS1150
 * Sept 19, 2019
 * Assignment 3
 * This program creates a tuition cost calculator, allowing the user
 * to input their class level and number of credit hours to determine
 * the cost of tuition and the total fee per credit hour
 */
import java.util.Scanner;

public class HolstZachAssignment3 
{

	public static void main(String[] args) 
	{
		//Declare constants that represent the cost per credit hour
		//for the selected class level
		//Declare constant for base fee
		final double FRESHMAN_TUITION = 380.0;
		final double SOPHOMORE_TUITION = 400.0;
		final double JUNIOR_TUITION = 430.0;
		final double SENIOR_TUITION = 451.0;
		final double BASE_FEE = 200.0;
		//create a new scanner
		Scanner input = new Scanner(System.in);
		//create menu with options for class levels and cost per credit hour
		System.out.println("Welcome to Tuition Calculator");
		System.out.println(" ");
		System.out.println("Option     Level               Cost Per Credit Hour");
		System.out.println("---------------------------------------------------");
		System.out.println("1          Freshman	       380.0");
		System.out.println("2          Sophomore           400.0");
		System.out.println("3          Junior              430.0");
		System.out.println("4          Senior              451.0");
		System.out.println("---------------------------------------------------");
		System.out.println(" ");
		//ask user what class level
		System.out.print("Select class level: 1, 2, 3, 4: ");
		{int classLevel = input.nextInt();
		//create a nested if statement for the class level and the numCreditHours
		//each if statement includes every equation needed to be put into the table
		//the first if statement determines if the input value for Class value 
		//is valid or not
		//if invalid, it will skip all other if statements, display an error
		//message and not compute anything else
		//if valid, it will continue on and ask the user for number of credit hours
		if((classLevel >=1) && (classLevel <=4)) {
			System.out.print("How man credit hours (1-30)?  ");
			int numCreditHours = input.nextInt();
			System.out.println("");
			//if the user input class level as 1 and had a valid number of credit hours
			if((classLevel == 1) && ((numCreditHours >= 1) && (numCreditHours <= 30))) {
				//if the values are true print into the table
				System.out.println("-------------------------");
				System.out.println("Level            Freshman");
				System.out.println("Credit hours     " + numCreditHours);
				//create an equation with the constant created earlier to find
				//total cost of tuition
				double totalCostTuition = FRESHMAN_TUITION * numCreditHours;
				System.out.println("Tuition          " + totalCostTuition);
				//create an equation to determine the total fees
				//based on the total number of credit hours
				double totalCostFees = BASE_FEE + numCreditHours * 20.00;
				System.out.println("Fees             " + totalCostFees);
				System.out.println("-------------------------");
				//calculate the final cost
				double finalCost = totalCostFees + totalCostTuition;
				System.out.println("Total            " + finalCost);
			}
			else {
				//if the user input class level as 2 and had a valid number of credit hours
				if((classLevel == 2) && ((numCreditHours >= 1) && (numCreditHours <= 30))) {
					//if the values are true print into the table
					System.out.println("-------------------------");
					System.out.println("Level             Sophomore");
					System.out.println("Credit hours      " + numCreditHours);
					//create an equation with the constant created earlier to find
					//total cost of tuition
					double totalCostTuition = SOPHOMORE_TUITION * numCreditHours;
					System.out.println("Tuition           " + totalCostTuition);
					//create an equation to determine the total fees
					//based on the total number of credit hours
					double totalCostFees = BASE_FEE + numCreditHours * 20.00;
					System.out.println("Fees              " + totalCostFees);
					System.out.println("--------------------------");
					//calculate the final cost
					double finalCost = totalCostFees + totalCostTuition;
					System.out.println("Total             " + finalCost);
				}
				else {
					//if the user input class level as 3 and had a valid number of credit hours
					if((classLevel == 3) && ((numCreditHours >= 1) && (numCreditHours <= 30))) {
						//if the values are true print into the table
						System.out.println("-------------------------");
						System.out.println("Level             Junior");
						System.out.println("Credit hours      " + numCreditHours);
						//create an equation with the constant created earlier to find
						//total cost of tuition
						double totalCostTuition = JUNIOR_TUITION * numCreditHours;
						System.out.println("Tuition           " + totalCostTuition);
						//create an equation to determine the total fees
						//based on the total number of credit hours
						double totalCostFees = BASE_FEE + numCreditHours * 20.00;
						System.out.println("Fees              " + totalCostFees);
						System.out.println("--------------------------");
						//calculate the final cost
						double finalCost = totalCostFees + totalCostTuition;
						System.out.println("Total             " + finalCost);
					}
					else {
						//if the user input class level as 4 and had a valid number of credit hours
						if((classLevel == 4) && ((numCreditHours >= 1) && (numCreditHours <= 30))) {
							//if the values are true print into the table
							System.out.println("-------------------------");
							System.out.println("Level             Senior");
							System.out.println("Credit hours      " + numCreditHours);
							//create an equation with the constant created earlier to find
							//total cost of tuition
							double totalCostTuition = SENIOR_TUITION * numCreditHours;
							System.out.println("Tuition           " + totalCostTuition);
							//create an equation to determine the total fees
							//based on the total number of credit hours
							double totalCostFees = BASE_FEE + numCreditHours * 20.00;
							System.out.println("Fees              " + totalCostFees);
							System.out.println("--------------------------");
							//calculate the final cost
							double finalCost = totalCostFees + totalCostTuition;
							System.out.println("Total             " + finalCost);
						}
						else {
							//if the number of credit hours was invalid, an error message will be displayed
							//and nothing will be computed
							System.out.println(numCreditHours + " is an invalid number of credit hours, please run the program again.");
						}
					}
				}
			}
		}
		else {
			System.out.println(classLevel + " is an invalid menu item, please run the program again.  ");
		}
		}

		//close scanner
		input.close();
	}
}

