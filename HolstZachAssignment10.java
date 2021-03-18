/*
 * Zach Holst
 * CS1450 Sec 001
 * Assignment 10
 * April  30, 2020
 * The purpose of this assignment is to create a binary tree,
 * and manipulate the tree by adding parrot objects and 
 * traversing through the binary tree both in order to create 
 * the birds song and from left to right to display all the names 
 * the bird objects.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HolstZachAssignment10 {

	public static void main(String[] args) throws FileNotFoundException {

		//create file and scanner for parrots file
		File parrotFile = new File("parrots.txt");
		Scanner parrots = new Scanner(parrotFile);

		//create one binary tree object
		BinaryTree binaryTree = new BinaryTree();

		//go through the file, create parrot objects, and add them to the binary tree
		int id = 0;
		String name = "";
		String song =  "";

		while(parrots.hasNext()) {

			id = parrots.nextInt();
			name = parrots.next();
			song = parrots.next();

			Parrot parrot = new Parrot(id, name, song);

			binaryTree.insertParrot(parrot);	
		}

		//call methods to go through binary tree and print bird song
		//and bird names
		System.out.println("Parrot's Song\n--------------------");
		binaryTree.levelOrder();
		
		System.out.println("\n\nLeaf Node Parrots\n--------------------");
		binaryTree.visitLeaves();

		parrots.close();
	}
}

//this class represents one parrot object
class Parrot {

	private int id;
	private String name;
	private String songWord;

	public Parrot(int id, String name, String songWord) {

		this.id = id;
		this.name = name;
		this.songWord = songWord;
	}

	public String getName() {

		return name;
	}

	public String sing() {

		return songWord;	
	}

	public int compareTo(Parrot otherParrot) {

		if(otherParrot.id > this.id) {

			return -1;
		}

		else if(otherParrot.id < this.id) {

			return 1;
		}

		else {

			return 0;
		}
	}
}

//this class represents one binary tree that holds bird objects
//has an inner private node class
//has methods to insert, level order, and visit the leaf nodes
//the visit leaves method is an example of a recursive method
class BinaryTree {

	private TreeNode root;

	public BinaryTree() {

		this.root = null;
	}

	public boolean insertParrot(Parrot parrotToAdd) {

		if(root == null) {

			root = new TreeNode(parrotToAdd);
		}

		else {

			TreeNode parent = null;
			TreeNode current = root;

			while(current != null) {

				if(parrotToAdd.compareTo(current.parrot) < 0) {

					parent = current;
					current = current.left;
				}

				else if(parrotToAdd.compareTo(current.parrot) > 0) {

					parent = current;
					current = current.right;
				}

				else {

					return false;
				}	
			}

			if(parrotToAdd.compareTo(parent.parrot) < 0) {

				parent.left = new TreeNode(parrotToAdd);
			}

			else {

				parent.right = new TreeNode(parrotToAdd);
			}
		}

		return true;
	}

	public void levelOrder() {

		if(root != null) {

			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
		
			while(!(queue.isEmpty())) {
				
				TreeNode tempNode = queue.remove();
				
				System.out.print(tempNode.parrot.sing() + " ");

				if(tempNode.left != null) {

					queue.offer(tempNode.left);
				}

				if(tempNode.right != null) {

					queue.offer(tempNode.right);
				}
			}
		}
	}

	//recursive method, prints names of parrot objects that are leaf nodes
	//within the binary tree
	public void visitLeaves() {

		TreeNode aNode = root;
		
		visitLeaves(aNode);
	}

	private void visitLeaves(TreeNode aNode) {
		
		if(aNode != null) {
		
			if(aNode.right == null && aNode.left == null) {
				
				System.out.println(aNode.parrot.getName());
				
			}
			
			if(aNode.left != null) {
				
				visitLeaves(aNode.left);
			}

			if(aNode.right != null) {
				
				visitLeaves(aNode.right);
			}
		}
	}

	//private node class
	//can move the node left and right
	private class TreeNode {

		private Parrot parrot;
		private TreeNode right;
		private TreeNode left;

		public TreeNode(Parrot parrot) {

			this.parrot = parrot;
			this.right = null;
			this.left = null;
		}
	}
}