/*
 * Zach Holst
 * CS 1450 
 * January 30, 2020
 * Assignment 1
 * The purpose of this assignment is to get more practice with
 * arrays and reading/writing from a file.  2 arrays are created
 * with a random length between 1-10, filled with random numbers
 * between 1-25.  the values of the arrays are written to a file
 * in a sorted order, from least to greatest.  Then, the values
 * are read from the newly written file and printed out in
 * a sorted order, removing any duplicate values in the array.
 * 
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class HolstZachAssignment1 {

	public static void main(String[] args) throws IOException {

		//fill two integers with a random number between 1 and 10
		int size1 = (int)(1 + Math.random() * 10);

		int size2 = (int)(1 + Math.random() * 10);

		//use the random number from both integers to determine the length
		//of two arrays.  Fill both arrays with random numbers between 1 to 25
		//using for loops
		int[] array1 = new int[size1];

		for(int count = 0; count < array1.length; count++) {

			array1[count] = (int)(1 + Math.random() * 25);

		}

		int[] array2 = new int[size2];

		for(int count = 0; count < array2.length; count++) {

			array2[count] = (int)(1 + Math.random() * 25);

		}

		//Sort both arrays using the sort method in the arrays class

		Arrays.sort(array1);

		Arrays.sort(array2);

		//print the values of both the integers from the start of the program
		//and print the values in both sorted arrays to the console

		System.out.println("size1 = " + size1);
		System.out.println("size2 = " + size2);
		System.out.println(" ");

		System.out.println("First array with " + size1 + " sorted random values");
		System.out.println("---------------------------------------");

		for(int count = 0; count < array1.length; count++) {

			System.out.println(array1[count]);

		}

		System.out.println(" ");
		System.out.println("Second array with " + size2 + " sorted random values");
		System.out.println("-----------------------------------------");

		for(int count = 0; count < array2.length; count++) {

			System.out.println(array2[count]);

		}

		//create a file named assignment1.txt

		File fileName = new File("assignment1.txt");

		//Open the file so that you can write to it

		PrintWriter resultsFile = new PrintWriter (fileName);

		//Write the values from both arrays to the file in sorted order,
		//displaying in the console what numbers are being written, using
		//a while loop.  once one of the arrays is out of values, determine
		//which array still has values and use a while loop to finish writing
		//the values of that array to the file

		System.out.println("");
		System.out.println("Writing values to the file");
		System.out.println("--------------------------");

		int countArray1 = 0;
		int countArray2 = 0;

		while(countArray1 < array1.length && countArray2 < array2.length) {

			if(array1[countArray1] < array2[countArray2]) {

				System.out.println("Writing: " + array1[countArray1]);

				resultsFile.println(array1[countArray1]);

				countArray1++;

			}

			else {

				System.out.println("Writing: " + array2[countArray2]);

				resultsFile.println(array2[countArray2]);

				countArray2++;

			}


		}

		//add the two while loops to determine which array still has
		//values and finish writing those values to the file

		while(countArray1 < array1.length) {

			System.out.println("Writing: " + array1[countArray1]);

			resultsFile.println(array1[countArray1]);

			countArray1++;

		}

		while(countArray2 < array2.length) {

			System.out.println("Writing: " + array2[countArray2]);

			resultsFile.println(array2[countArray2]);

			countArray2++;

		}

		resultsFile.close();

		//create a scanner object to be able to read the values
		//that were written to the file and display them in order,
		//removing any duplicate numbers that were in the previous arrays
		//add the values to a new array before printing

		Scanner readFile = new Scanner (fileName);

		System.out.println("");
		System.out.println("Array with no duplicate values");
		System.out.println("------------------------------");

		int finalArrayLength = (array1.length + array2.length);

		int[] finalSortedArray = new int[finalArrayLength];

		int count = 1;

		finalSortedArray[0] = readFile.nextInt();

		System.out.println(finalSortedArray[0]);

		while(count < (array1.length + array2.length)) {

			finalSortedArray[count] = readFile.nextInt();

			if (finalSortedArray[count] == finalSortedArray[count-1]){
				
				finalArrayLength--;

				count++;

			}

			else {

				System.out.println(finalSortedArray[count]);

				count++;

			}

		}

		//close the scanner object 
		readFile.close();
	}

} //Assignment 1 end
