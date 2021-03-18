/*
 * Zach Holst
 * CS 1450 Sec 001
 * Assignment 7
 * April 9, 2020
 * This program simulates an escape room game.  It makes use of
 * queues, a custom implemented priority queue, objects within objects
 * and a hash key to create a score for a player object based off of 
 * their name and their ranking.  It provides practice for nested classes,
 * as the majority of the program is using classes and objects 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HolstZachAssignment7 {

	public static void main(String[] args) throws FileNotFoundException {

		final int NUM_SEATS = 25;

		//create an array that hold players at different "seat" locations
		//this players are added to the escape room
		Player seats[] = new Player[NUM_SEATS];

		//create file and scanner to add players to the seats array
		//using a while loop until file is empty
		File playerFile = new File("players.txt");

		Scanner playersScanner = new Scanner(playerFile);

		while(playersScanner.hasNext()) {

			Player player = new Player(playersScanner.next(), 
					playersScanner.nextInt(), playersScanner.nextInt());

			seats[player.getSeat()] = player;

		}

		//create an escape game and escape game controller object

		EscapeGame escapeGame = new EscapeGame();
		EscapeGameController escapeGameController = new EscapeGameController();

		//move players from waiting seats into the escape game with
		//method within escape game controller class
		escapeGameController.movePlayerIntoEscapeGame(seats, escapeGame);

		//simulate game with simulateEscapeGame method
		escapeGameController.simulateGame(escapeGame);

		//display results with displayResults method
		escapeGameController.displayResults(escapeGame);

		playersScanner.close();
	}
} //main and class

//this class represents a player object
//implements comparable for priority queue
//overrides compare to method, comparing the score of two player objects
class Player implements Comparable<Player>{

	private String name;
	private int ranking;
	private int seat;
	private int score;

	public Player(String name, int ranking, int seat) {

		this.name = name;
		this.ranking = ranking;
		this.seat = seat;
		score = 0;

	}

	public String getName() {

		return name;

	}

	public int getRanking() {

		return ranking;

	}

	public int getSeat() {

		return seat;

	}

	public int getScore() {

		return score;

	}

	public void setScore(int score) {

		this.score = score;

	}

	@Override
	public int compareTo(Player otherPlayer) {

		if(score < otherPlayer.getScore()) {

			return -1;

		}

		else if(score > otherPlayer.getScore()) {

			return 1;

		}
		else {

			return 0;

		}
	}
}

//Represents an escape room.  uses a hash key to create a score
//for the players that enter the escape room by concatenating
//their name and rank
class EscapeRoom {

	private int hash(String key) {

		int hash = 0;
		for(int i = 0; i<key.length(); i++) {

			hash += key.charAt(i);
			hash += (hash << 10);
			hash ^=(hash >>6);
		}
		hash += (hash << 3);
		hash ^= (hash >> 11);
		hash += (hash << 15);

		return Math.abs(hash);

	}

	public int tryToEscape(String playerName, int playerRanking) {

		String key = playerName + playerRanking;

		int score = hash(key)%(101);

		return score;	
	}
}

//Represents an escape game, with a queue for players waiting to play
//and a priority queue with players who have played 
//has method to call tryToEscape from escape room class
class EscapeGame {

	private Queue<Player> playersWaiting;
	private PriorityQueue resultsQueue;
	private EscapeRoom escapeRoom;

	public EscapeGame() {

		playersWaiting = new LinkedList<>();
		resultsQueue = new PriorityQueue();
		escapeRoom = new EscapeRoom();

	}

	public boolean isWaitingQueueEmpty() {

		if(playersWaiting.size() == 0) {
			return false;
		}
		else {
			return true;
		}

	}

	public void addPlayerToWaitingQueue(Player player) {

		playersWaiting.offer(player);

	}

	public Player removePlayerFromWaitingQueue() {

		return playersWaiting.remove();

	}

	public boolean isResultsQueueEmpty() {

		return resultsQueue.isEmpty();

	}

	public void addPlayerToResultsQueue(Player player) {

		resultsQueue.offer(player);

	}

	public Player removePlayerFromResultsQueue() {

		return resultsQueue.remove();

	}

	public Player peekResultsQueue() {

		return resultsQueue.peek();

	}

	public int tryToEscape(String playerName, int playerRanking) {

		return escapeRoom.tryToEscape(playerName, playerRanking);

	}
}

//this class is used to move players out of the waiting queue to 
//play the game.  simulates an escape game, applies a score to each
//player object after calling the tryToEscape method.  then has a method
//to display results of all player objects
class EscapeGameController {

	public void movePlayerIntoEscapeGame(Player[] seats, EscapeGame escapeGame) {

		System.out.println("Controller: Moving players from outside seats into escape game");
		System.out.println("-------------------------------------------------------------");

		for(int i = 0; i < seats.length; i++) {
			if(seats[i] != null ) {

				System.out.println("Moved into escape game: " + seats[i].getName() + " from outside seat " + seats[i].getSeat());
				escapeGame.addPlayerToWaitingQueue(seats[i]);
			}
		}
	}

	public void simulateGame (EscapeGame escapeGame) {

		System.out.println("\n\nController: Starting Escape Game - moving players waiting in line to escape room");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("Player\tScore\tCurrent Leader");
		System.out.println("--------------------------------------------------------------------------------------");

		while(escapeGame.isWaitingQueueEmpty()) {

			Player player = escapeGame.removePlayerFromWaitingQueue();

			player.setScore(escapeGame.tryToEscape(player.getName(), player.getRanking()));

			escapeGame.addPlayerToResultsQueue(player);

			System.out.printf("%-8s%-8d%s\n", player.getName(), player.getScore(), escapeGame.peekResultsQueue().getName());	
		}
	}

	public void displayResults(EscapeGame escapeGame) {

		System.out.println("\n\nController: Escape Room Results");
		System.out.println("-------------------------------");
		System.out.println("Player\tScore");
		System.out.println("-------------------------------");

		while(escapeGame.isResultsQueueEmpty()) {

			Player player = escapeGame.removePlayerFromResultsQueue();

			System.out.printf("%-8s%d\n", player.getName(), player.getScore());
		}
	}
}

//this class creates the methods needed for a priority queue
//of player objects.  uses the selection sort algorithm to 
//sort the priority queue
class PriorityQueue {

	private Player list[];
	private int numPlayers;

	public PriorityQueue() {

		list = new Player[30];
		numPlayers = 0;

	}

	public boolean isEmpty() {

		if(numPlayers == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	public Player peek() {

		if(numPlayers == 0) {
			return null;
		}
		else {
			return list[numPlayers-1];
		}
	}

	public boolean offer(Player player) {

		if(numPlayers == list.length-1) {
			return false;
		}
		else {
			list[numPlayers] = player;
			numPlayers++;
			selectionSort(list, numPlayers);
			return true;
		}
	}

	public Player remove() {

		Player player = peek();
		list[numPlayers] = null;
		numPlayers--;
		return player;

	}

	//Uses selection sort algorithm to sory the queue
	private void selectionSort (Player[] list, int numPlayers) {

		//move through the array
		for (int i = 0; i < numPlayers-1; i++) 
		{ 
			//find player with lowest score value 
			int smallestValueIndex = i; 
			Player lastPlace = list[i];

			for (int j = i+1; j < numPlayers; j++) 

				if (list[j].getScore() < list[smallestValueIndex].getScore())  {
					smallestValueIndex = j; 
					lastPlace = list[j];
				}
			if(smallestValueIndex != i) {
				list[smallestValueIndex] = list[i];
				list[i] = lastPlace;
			}
		}
	}
}
