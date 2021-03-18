/*
 * Zach Holst
 * CS1150
 * November 7, 2019
 * Assignment 9
 * the purpose of this assignment is to create a program
 * that manipulates arrays in different ways, such as 
 * filling arrays, merging arrays, sorting arrays, and 
 * displaying the arrays
 */

//import array utility package
import java.util.Arrays;
public class HolstZachAssignment9 {

	public static void main(String[] args) {

		//declare a constant for length of the arrays
		int MAX_ARRAY_LENGTH = 10;

		//create all arrays needed throughout the program
		int[] even = new int[MAX_ARRAY_LENGTH];
		int[] odd = new int[MAX_ARRAY_LENGTH];
		int[] merged = new int[even.length + odd.length];
		int[] array1 = new int[6];
		int[] array2 = new int[6];

		//calls method to fill even array with random even values
		fillWithEvenValues(even);

		//calls method to display the values from the even array
		displayList(even, "Even Array Sorted");

		//calls method to fill odd array with random odd values
		fillWithOddValues(odd);

		//calls method to display the values from the odd array
		displayList(odd, "Odd Array Sorted");

		//calls method to merge the even and odd arrays into one array
		mergeArrays(even, odd, merged);

		//calls method to display the now merged array with the values
		//in descending order.  the sorting is done through the arrays class
		descendingOrderSort(merged);

		//fills array1 and array2 with a specific table of values. it then merges the 
		//two arrays into one. then it sorts the two arrays in descending order manually,
		//not using the arrays class. it then displays the array values
		sortedMerge(array1, array2, merged);

	}
	//this method fills an array with random even values
	public static void fillWithEvenValues (int[] list) {


		for(int count = 0; count < list.length; count++) {

			list[count] = ((int)(Math.random() * 72) + 2);

			if(list[count] % 2 != 0) {
				list[count] = list[count] + 1;

			}
		}

		Arrays.sort(list);


	}

	//this method fills an array with random odd values
	public static void fillWithOddValues (int[] list) {

		for(int count = 0; count < list.length; count++) {

			list[count] = (int)(Math.random() * 74) + 1;

			if(list[count] % 2 == 0) {

				list[count] = list[count] + 1;

			}
		}

		Arrays.sort(list);

	}

	//this method displays the name of an array and displays the values
	//within that array
	public static void displayList (int[] list, String arrayName) {

		System.out.println(arrayName);
		System.out.println("--------------------------------------------------");

		for (int count = 0; count < list.length; count++) {
			System.out.println("array[" + count + "] = " + list[count]);
		}

		System.out.println("");
		System.out.println("");

	}

	//this method merges two arrays into one array
	public static void mergeArrays(int[] list1, int[] list2, int[] merged) {

		int index = 0;

		for (int count1 = 0; count1 < list1.length; count1++) {

			merged[index] = list1[count1];

			index++;

		}

		for (int count2 = 0; count2 < list2.length; count2++) {

			merged[index] = list2[count2];

			index++;

		}

	}

	//this method sorts an array into descending order, making use of
	//the arrays class, and then displays the values of the array
	public static void descendingOrderSort (int[] list) {

		System.out.println("Merged Array Sorted in Descending Order (largest to smallest)");
		System.out.println("-------------------------------------------------------------");

		Arrays.sort(list);

		for (int count = (list.length-1); count >= 0; count--) {
			System.out.println("array[" + count + "] = " + list[count]);
		}

		System.out.println("");
		System.out.println("");
	}

	//this method fills two arrays with a specific list of values,
	//merges those values into one array while simultaneously manually
	//sorting the values in a descending order. it then displays
	//the values within the array
	public static void sortedMerge(int[] list1, int[] list2, int[] merged) {

		list1[0] = 10;
		list1[1] = 12;
		list1[2] = 15;
		list1[3] = 42;
		list1[4] = 68;
		list1[5] = 102;

		list2[0] = 14;
		list2[1] = 16;
		list2[2] = 18;
		list2[3] = 101;
		list2[4] = 107;
		list2[5] = 108;

		merged = new int[12];

		boolean moreValuesInList1 = true;
		boolean moreValuesInList2 = true;

		int count1 = 5;
		int count2 = 5;
		int countMerged = 11;

		//begin by looking at both arrays to start filling in the merged array
		while (moreValuesInList1 && moreValuesInList2 && count1 >= 0 && count2 >= 0) {

			if(list1[count1] > list2[count2]) {

				merged[countMerged] = list1[count1];
				countMerged--;
				count1--;

			}
			else {

				merged[countMerged] = list2[count2];
				countMerged--;
				count2--;
			}
		}

		//once one array is empty, if statements are used to determine which array still
		//has values to put in the merged array
		if (moreValuesInList1) {

			while(moreValuesInList1 && count1 >= 0) {

				merged[countMerged] = list1[count1];
				countMerged--;
				count1--;

			}
		}
		else if (moreValuesInList2){
			while(moreValuesInList2 && count2 >= 0) {

				merged[countMerged] = list2[count2];
				countMerged--;
				count2--;

			}
		}

		System.out.println("Manually Merged Array Sorted in Descending Order (largest to smallest)");
		System.out.println("----------------------------------------------------------------------");

		for (int count = (merged.length-1); count >= 0; count--) {
			System.out.println("array[" + count + "] = " + merged[count]);
		}
	}
} //assignment 9
