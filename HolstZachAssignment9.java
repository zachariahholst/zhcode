/*
 * Zach Holst
 * CS1450 Sec 001
 * Assignment 9
 * April  23, 2020
 * The purpose of this assignment is to implement a custom
 * linked list with all needed methods to hold elephant objects.
 * An inner Node class is used within the linked list class
 * the nodes are used to move through the linked list to find
 * and interact with the different elements
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HolstZachAssignment9 {

	public static void main(String[] args) throws FileNotFoundException {

		//create an instance of an ElephantLinkedList Object
		ElephantLinkedList linkedList = new ElephantLinkedList();

		//create file and scanner for elephants file
		File elephants = new File("elephants.txt");
		Scanner elephantScanner = new Scanner(elephants);

		//read through file and create new elephant objects
		//add each elephant object to the linked list
		String name = "";
		int weight = 0;

		while(elephantScanner.hasNext()) {

			name = elephantScanner.next();
			weight = elephantScanner.nextInt();
			Elephant elephant = new Elephant(name, weight);		
			linkedList.add(elephant);

		}

		//call method to print the linked list
		System.out.print("Elephant\tWeight\n-------------------------------\n");
		linkedList.printList();

		//call method to find total weight of elephants and print
		System.out.println("\nTotal weight of all elephants is: "
				+ linkedList.getTotalWeight() + "lbs\n");

		//call method to find the current heaviest elephant in the
		//linked list, remove it, and print the details of that elephant
		//remove elephant from linked list with remove() command
		while(linkedList.isEmpty()) {

			Elephant temp = linkedList.findHeaviest();
			System.out.print("Removing: ");
			temp.print();
			linkedList.remove(temp);
		}

		//close Scanner, end program
		elephantScanner.close();
	}
}

//class to define Elephant objects
class Elephant {

	private String name;
	private int weight;

	public Elephant(String name, int weight) {

		this.name=name;
		this.weight=weight;
	}

	public String getName() {

		return name;
	}

	public int getWeight() {

		return weight;
	}

	public void print() {

		System.out.print(name + " weighing in at " + weight + " lbs\n");	
	}
}

//class represents a linked list that holds elephant objects
//has a node subclass to move through the linked list
//has all needed functions like add(), remove(), and printList()
class ElephantLinkedList {

	private Node head;
	private Node tail;

	public ElephantLinkedList() {

		this.head = null;
		this.tail = null;
	}

	public boolean isEmpty() {

		if (head != null) {

			return true;
		}

		else {

			return false;
		}
	}

	public int getTotalWeight() {

		Node current = head;
		int totalWeight = 0;
		while(current != null) {

			totalWeight = totalWeight + current.elephant.getWeight();
			current = current.next;

		}

		return totalWeight;
	}

	public void add(Elephant elephant) {
		Node newNode = new Node(elephant);

		if(head == null) {

			head = tail = newNode;
		}

		else {
			tail.next = newNode;
			tail = newNode;
		}	
	}

	public Elephant findHeaviest() {

		Node current = head;
		Node heaviest = head;

		if(tail == null) {
			
			return null;
		}
		
		else {

			while(current.next != null) {

				current = current.next;

				if(heaviest.elephant.getWeight() < current.elephant.getWeight()) {

					heaviest = current;
				}
			}

			return heaviest.elephant;
		}
	}

	public void remove(Elephant elephant) {

		Node previous = null;
		Node current = head;
		boolean found = false;

		while(current != null && !found) {

			if(current.elephant == elephant) {

				found = true;
			}

			else {

				previous = current;
				current = current.next;
			}
		}

		if(found) {

			if(previous == null) {

				head = current.next;
			}

			else {

				previous.next = current.next;
			}
		}
	}

	public void printList() {

		Node current = head;

		while(current != null) {

			System.out.println(current.elephant.getName() + "\t\t" + current.elephant.getWeight());
			current=current.next;
		}
	}

	//private node class, within ElephantLinkedList class
	private class Node {

		private Elephant elephant;
		private Node next;

		public Node(Elephant elephant) {

			this.elephant=elephant;
			this.next=null;
		}

	}
}