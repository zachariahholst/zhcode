
/*
 * Zach Holst
 * CS 1450 Sec 001
 * Assignment 3
 * February 13, 2020
 * The purpose of this assignment is to create a program
 * that creates insect objects with different parameters
 * and abilities, making use of interfaces for attributes 
 * of the objects.  It also gives practice into
 * making an ArrayList to hold objects
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HolstZachAssignment3 {

	public static void main(String[] args) throws IOException {

		//create a file object for insect file
		File inputFileName = new File("insects.txt");

		//create a scanner to read from the file
		Scanner inputFile = new Scanner(inputFileName);

		//create polymorphic array to hold insect objects
		Insect[] insects = new Insect[inputFile.nextInt()];

		String type = "";
		String name = "";
		int pollinateAbility = 0;
		int buildAbility = 0;
		int predatorAbility = 0;
		int decomposerAbility = 0;

		//using a for loop, go through the file and create
		//necessary insect objects based off of their information,
		//using if statements to determine the type and which
		//type of insect to create
		for(int count = 0; count < insects.length; count++) {

			type = inputFile.next();
			name = inputFile.next();
			pollinateAbility = inputFile.nextInt();
			buildAbility = inputFile.nextInt();
			predatorAbility = inputFile.nextInt();
			decomposerAbility = inputFile.nextInt();

			if(type.equals("h")) {

				insects[count] = new Honeybee(name, pollinateAbility, buildAbility);

			}

			else if(type.equals("l")) {

				insects[count] = new Ladybug(name, pollinateAbility, predatorAbility);

			}

			else if(type.equals("a")) {

				insects[count] = new Ant(name, buildAbility, predatorAbility, decomposerAbility);

			}

			else if(type.equals("p")) {

				insects[count] = new PrayingMantis(name, predatorAbility);

			}
		}

		//print to the console all of the insects that don't help
		//with decomposition, calling the doNotDecompose method
		//and creating an ArrayList to find the indexes of the insects
		//that don't help decompose

		System.out.println("INSECTS THAT DON'T HELP WITH DECOMPOSITION!");
		System.out.println("-------------------------------------------");
		
		ArrayList<Insect> doNotDecompose = findDoNotDecompose(insects);

		for(int count = 0; count < doNotDecompose.size(); count++) {
			
			displayAbilities(doNotDecompose.get(count));
			
		}
		
		//now print out the most able insect, finding the index
		//of the object with the findMostAble method
		System.out.println("");
		System.out.println("INSECTS WITH MOST ABILITIES!");
		System.out.println("----------------------------");
		
		int mostAbleIndex = findMostAble(insects);
		
		System.out.println("The winner is " + insects[mostAbleIndex].getName() + " the " + insects[mostAbleIndex].getType());
		
		displayAbilities(insects[mostAbleIndex]);

		//close the file
		inputFile.close();
		
	}	//end main

	//this method goes through all of the insect objects in the
	//polymorphic array and determines which objects do not
	//have a decompose ability.  Each object without a 
	//decompose ability is added to an ArrayList 
	public static ArrayList<Insect> findDoNotDecompose(Insect[] insects) {

		ArrayList<Insect>doNotDecompose = new ArrayList<>();

		for(int count = 0; count < insects.length; count++) {

			if (!(insects[count] instanceof Decomposer)) {
				
				doNotDecompose.add(insects[count]);
				
			}
		}

		return doNotDecompose;

	}

	//This method goes through all of the insect objects in
	//the polymophic array and determines which insect has the
	//greatest number of abilities.
	public static int findMostAble(Insect[] insects) {

		int mostAble = 0;
		int mostAbleSoFar = 0;
		int mostAbleIndex = 0;

		for(int count = 0; count < insects.length; count++) {

			if(insects[count] instanceof Pollinator) {

				mostAble = mostAble + ((Pollinator) insects[count]).pollinate();
				
			}

			if(insects[count] instanceof Builder) {

				mostAble = mostAble + ((Builder) insects[count]).build();
				
			}

			if(insects[count] instanceof Predator) {

				mostAble = mostAble + ((Predator) insects[count]).predator();
				
			}

			if(insects[count] instanceof Decomposer) {

				mostAble = mostAble + ((Decomposer) insects[count]).decompose();
				
			}
			
			if(mostAble > mostAbleSoFar) {
				
				mostAbleSoFar = mostAble;
				mostAbleIndex = count;
				
			}
				
			mostAble = 0;
		}

		return mostAbleIndex;

	}

	//Displays all information from the insect objects.
	//their name, type, abilities, purpose, and the 
	//numeric ability value.  
	public static void displayAbilities(Insect insect) {

		if(!(insect instanceof Decomposer)) {
			
			System.out.println(insect.getName() + " is a " + insect.getType() +" and does not help with decomposition");
			
		}
		
		insect.purpose();

		if(insect instanceof Pollinator){

			System.out.println("Pollinating ability " + ((Pollinator) insect).pollinate());

		}

		if(insect instanceof Builder) {

			System.out.println("Building ability " + ((Builder) insect).build());

		}

		if(insect instanceof Predator) {

			System.out.println("Predator ability " + ((Predator) insect).predator());

		}

		if(insect instanceof Decomposer) {

			System.out.println("Decompose ability " + ((Decomposer) insect).decompose());

		}

		System.out.println("");

	}

} //end Assignment 3

//create interfaces for four abilities that insects have
//pollinate, build, predator, decompose
//the methods are abstract
interface Pollinator {

	public abstract int pollinate();

}

interface Builder {

	public abstract int build();

}

interface Predator {

	public abstract int predator();

}

interface Decomposer {

	public abstract int decompose();

}

//creates an insect class, this is the super class
//has methods to get the objects name, type, and purpose
abstract class Insect {

	private String type;
	private String name;

	public String getType() {

		return type;

	}

	public String getName() {

		return name;

	}

	public void setType(String type) {

		this.type = type;

	}

	public void setName(String name) {

		this.name = name;

	}

	public abstract void purpose();

}

//create subclasses for each type of insect to extend
//the insect class.  Implement needed interfaces based
//off of the insects abilities.  
//should override needed interface methods and the purpose
//method
class Honeybee extends Insect implements Pollinator, Builder {

	private int pollinateAbility;
	private int builderAbility;

	public Honeybee(String name, int pollinateAbility, int builderAbility) {
		
		setName(name);
		setType("Honeybee");
		
		this.pollinateAbility = pollinateAbility;
		this.builderAbility = builderAbility;
 
	}

	@Override
	public void purpose() {
		

		System.out.println("I'm popular for producing honey but I also pollinate ");
		System.out.println("35% of the crops! Without me, 1/3 of the food ");
		System.out.println("you eat would not be available!");
	}

	@Override
	public int pollinate() {

		return pollinateAbility;

	}

	@Override
	public int build() {

		return builderAbility;

	}
}

class Ladybug extends Insect implements Pollinator, Predator {

	private int pollinateAbility;
	private int predatorAbility;

	public Ladybug(String name, int pollinateAbility, int predatorAbility) {
		
		setName(name);
		setType("Ladybug");
		
		this.pollinateAbility = pollinateAbility;
		this.predatorAbility = predatorAbility;

	}

	@Override
	public void purpose() {

		System.out.println("Named after the Virgin Mary, I'm considered good ");
		System.out.println("luck if I land on you! I'm a pest control expert eating up to 5,000 ");
		System.out.println("plant pests during my life span.");
		
	}

	@Override
	public int pollinate() {

		return pollinateAbility;

	}

	@Override
	public int predator() {

		return predatorAbility;

	}
}

class Ant extends Insect implements Builder, Predator, Decomposer {

	private int builderAbility;
	private int predatorAbility;
	private int decomposerAbility;

	public Ant(String name, int builderAbility, int predatorAbility, int decomposerAbility) {
		
		setName(name);
		setType("Ant");
		
		this.builderAbility = builderAbility;
		this.predatorAbility = predatorAbility;
		this.decomposerAbility = decomposerAbility;
		
	}

	@Override
	public void purpose() {

		System.out.println("Don't squash me, I'm an ecosystem engineer!");
		System.out.println("Me and my 20 million friends accelerate decomposition of dead wood,");
		System.out.println("aerate soil, improve drainage, and eat insects like ticks and termites!");

	}

	@Override
	public int build() {

		return builderAbility;

	}

	@Override
	public int predator() {

		return predatorAbility;

	}

	@Override
	public int decompose() {

		return decomposerAbility;

	}
}

class PrayingMantis extends Insect implements Predator {

	private int predatorAbility;

	public PrayingMantis(String name, int predatorAbility) {
		
		setName(name);
		setType("Praying Mantis");
		
		this.predatorAbility = predatorAbility;

	}

	@Override
	public void purpose() {

		System.out.println("I'm an extreme predator quick enough to catch a fly. ");
		System.out.println("Release me in a garden and I'll eat beetles, ");
		System.out.println("grasshoppers, crickets and even pesky moths.");

	}

	@Override
	public int predator() {

		return predatorAbility;

	}
}