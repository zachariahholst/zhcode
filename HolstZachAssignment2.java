import java.util.Scanner;

/*
 * Zach Holst
 * CS1150
 * Sept 12, 2019
 * Assignment 2
 *This program creates a water drip calculator to determine the 
 *number of gallons of water are wasted by dripping faucets in
 *2 different cities per day and per year
 */
public class HolstZachAssignment2 {

	public static void main(String[] args) 
	{
		// Create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Create a table for city 1 info
		System.out.println("City 1 Drip Information:");
		System.out.println("------------------------");
		
			//Ask user for number of homes in city 1
			System.out.print("Enter number of homes in city 1:  ");
			double city1Homes = input.nextDouble();
		
			//Ask user for average number of dripping faucets in each home
			System.out.print("Enter average number of dripping faucets in each home:  ");
			double city1Faucets = input.nextDouble();
		
			//Ask user for average number of drips per minute
			System.out.print("Enter average number of drips per minute:  ");
			double city1DripsPerMinute = input.nextDouble();
		
			//Calculate number of gallons wasted in one day and one year for city 1
			//first compute number of drips per day for one faucet
			//then compute number of drips per day for all faucets
		
			double city1DripsPerDay = city1DripsPerMinute * 86400;
		
			double city1DripsPerDayForAllHomes = city1DripsPerDay * city1Faucets * city1Homes;
		
			double city1GallonsPerDay = city1DripsPerDayForAllHomes / 15140;
		
			double city1GallonsPerYear = city1GallonsPerDay * 365;
		
		
		System.out.println(" ");
		//Create a table for city 2 info
		System.out.println("City 2 Drip Information:");
		System.out.println("------------------------");
		
			//Repeat for city 2
			//Ask user for number of homes in city 1
			System.out.print("Enter number of homes in city 2:  ");
			double city2Homes = input.nextDouble();
		
			//Ask user for average number of dripping faucets in each home
			System.out.print("Enter average number of dripping faucets in each home:  ");
			double city2Faucets = input.nextDouble();
		
			//Ask user for average number of drips per minute
			System.out.print("Enter average number of drips per minute:  ");
			double city2DripsPerMinute = input.nextDouble();
		
			//Repeat calculations for city 2
		
			double city2DripsPerDay = city2DripsPerMinute * 1440;
		
			double city2DripsPerDayForAllHomes = city2DripsPerDay * city2Faucets * city1Homes;
		
			double city2GallonsPerDay = city2DripsPerDayForAllHomes / 15140;
		
			double city2GallonsPerYear = city2GallonsPerDay * 365;
		
		
		System.out.println(" ");
		//Create a table for the results of the inputs
		System.out.println("****************************************************************************");
		System.out.println("			     Water Drip Calculator");
		System.out.println("****************************************************************************");
		System.out.println("");
		System.out.println("Homes		Faucets		Drips/Minute	Gallons/Day	Gallons/year");
		System.out.println("----------------------------------------------------------------------------");
			System.out.printf("%.0f", city1Homes);
			System.out.printf("\t\t%.0f", city1Faucets);
			System.out.printf("\t\t%.0f", city1DripsPerMinute);
			System.out.printf("\t\t%.2f", city1GallonsPerDay);
			System.out.printf("\t%.2f", city1GallonsPerYear);
		System.out.println("");
			System.out.printf("%.0f", city2Homes);
			System.out.printf("\t\t%.0f", city2Faucets);
			System.out.printf("\t\t%.0f", city2DripsPerMinute);
			System.out.printf("\t\t%.2f", city2GallonsPerDay);
			System.out.printf("\t\t%.2f", city2GallonsPerYear);
		System.out.println(" ");
		System.out.println("----------------------------------------------------------------------------");
			System.out.printf("\t\t\t\t\t\t%.2f", city1GallonsPerDay + city2GallonsPerDay);	//gives total number gallons per day of both cities
			System.out.printf("\t%.2f", city1GallonsPerYear + city2GallonsPerYear);			//gives total number gallons per year of both cities
			System.out.println("  ");
		
		input.close();

	}

}
